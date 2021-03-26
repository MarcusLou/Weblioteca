package org.weblioteca.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.weblioteca.application.model.Autor;
import org.weblioteca.application.service.AutorService;

@Controller
public class AutorController {
	@Autowired
	AutorService autorService;
	
	@GetMapping("/indexAutor")
	public String viewHomePage(Model model) {
		return autoresPaginacao(1, "nome", "asc", model);
	}
	
	@GetMapping("/novoAutor") 
	public String novoAutor(Model model) {
		Autor autor = new Autor();
		model.addAttribute("autor", autor);
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
		return "atualizarAutor";
	}
	
	@SuppressWarnings("finally")
	@GetMapping("/deletarAutor/{id}")
	public String deletarAutor(@PathVariable (value = "id") Long id) {
		try {
			autorService.deletarAutorById(id);	
			return "redirect:/indexAutor";	
		}catch (Exception $e)  {			
			return "redirect:/erroAutor";	
		}
	}
	
	@GetMapping("/erroAutor") 
	public String erroAutor(Model model) {
		return "erroAutor";	
	}
	
	@GetMapping("/pageAutor/{pageNo}")
	public String autoresPaginacao(@PathVariable (value = "pageNo") int pageNoAutor, 
			                        @RequestParam("sortField") String sortFieldAutor,
		                        	@RequestParam("sortDir") String sortDirAutor,
		                         	Model model) {
		int pageSizeAutor = 5;
		
		Page<Autor> pageAutor = autorService.findPaginated(pageNoAutor, pageSizeAutor, sortFieldAutor, sortDirAutor);
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
