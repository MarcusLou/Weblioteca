package org.weblioteca.application.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.weblioteca.application.model.Autor;
import org.weblioteca.application.service.AutorService;

@Controller
public class AutorController {
	@Autowired
	AutorService autorService;
	
	@GetMapping("/indexAutor")
	public String viewHomePage(Model model) {
		Integer ativo = 1;
		return autoresPaginacao(1, "nome", "asc", model, ativo);
	}
	
	@GetMapping("/novoAutor") 
	public String novoAutor(Model model) {
		Autor autor = new Autor();
		model.addAttribute("autor", autor);
	    LocalDate nowMinus18 = LocalDate.now().minusYears(18);
		model.addAttribute("nowMinus18", nowMinus18);
		return "salvarAutor";
	}

	@PostMapping("/salvarAutor")
	public String salvarAutor(@ModelAttribute("autor") Autor autor) {
		autorService.salvarAutor(autor);
		return "redirect:/indexAutor";
	}
	
	@GetMapping("/atualizarAutor/{id}")
	public String atualizarAutor(@PathVariable ( value = "id") Long id, Model model) {
		Autor autor = autorService.getAutorById(id);
		model.addAttribute("autor", autor);
	    LocalDate nowMinus18 = LocalDate.now().minusYears(18);
		model.addAttribute("nowMinus18", nowMinus18);
		return "atualizarAutor";
	}
	/*
	@GetMapping("/deletarAutor/{id}")
	public String deletarAutor(@PathVariable (value = "id") Long id) {
		try {
			autorService.deletarAutorById(id);	
			return "redirect:/indexAutor";	
		}catch (Exception $e)  {			
			return "redirect:/mensagemAutor";	
		}
	}*/
	
	
	@GetMapping("/deletarAutor/{id}")
	public String deletarAutor(@PathVariable (value = "id") Long id) {
		try {
			Autor autor = autorService.getAutorById(id);
			autor.setAtivo(0);
			autorService.salvarAutor(autor);
			return "redirect:/indexAutor";
		}catch (Exception $e)  {			
			return "redirect:/mensagemAutor";	
		}
	}
	
	@GetMapping("/ativarAutor/{id}")
	public String ativarAutor(@PathVariable (value = "id") Long id) {
		try {
			Autor autor = autorService.getAutorById(id);
			autor.setAtivo(1);
			autorService.salvarAutor(autor);
			return "redirect:/indexAutor";
		}catch (Exception $e)  {			
			return "redirect:/mensagemAutor";	
		}
	}
	
	@RequestMapping("/indexAutor/{pesquisa}")
    public String pesquisar(Model model, @Param("ativo") Integer ativo, @Param("pesquisa") String pesquisa) {
        List<Autor> listaAutores = autorService.pesquisar(ativo, pesquisa);
        model.addAttribute("listaAutores", listaAutores);
        return "indexAutor";
    }
	
/*
	@GetMapping("/mensagemAutor") 
	public String mensagemAutor(Model model) {
		return "mensagemAutor";	
	}*/
	
	@GetMapping("/pageAutor/{pageNo}")
	public String autoresPaginacao(@PathVariable (value = "pageNo") int pageNoAutor, 
			                        @RequestParam("sortField") String sortFieldAutor,
		                        	@RequestParam("sortDir") String sortDirAutor,
		                        	Model model,
		                         	@Param("ativo") Integer ativo) {
		int pageSizeAutor = 7;
		if (ativo == null) {
			ativo = 1;
		}
			Page<Autor> pageAutor = autorService.findPaginated(pageNoAutor, pageSizeAutor, sortFieldAutor, sortDirAutor, ativo);
			List<Autor> listaAutores = pageAutor.getContent();			
			model.addAttribute("currentPage", pageNoAutor);
			model.addAttribute("totalPages", pageAutor.getTotalPages());
			model.addAttribute("totalItems", pageAutor.getTotalElements());
			model.addAttribute("sortField", sortFieldAutor);
			model.addAttribute("sortDir", sortDirAutor);
			model.addAttribute("reverseSortDir", sortDirAutor.equals("asc") ? "desc" : "asc");
			model.addAttribute("listaAutores", listaAutores);		
		return "indexAutor";
	}
}
