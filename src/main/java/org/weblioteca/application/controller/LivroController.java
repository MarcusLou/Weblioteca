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
import org.weblioteca.application.model.Livro;
import org.weblioteca.application.service.LivroService;

@Controller
public class LivroController {
	@Autowired
	LivroService livroService;
	
	@GetMapping("/livro")
	public String viewHomePage(Model model) {
		return livrosPaginacao(1, "tituloLivro", "asc", model);
	}
	
	@GetMapping("/novoLivro") 
	public String novoLivro(Model model) {
		Livro livro = new Livro();
		model.addAttribute("livro", livro);
		return "salvarLivro";
	}
	
	@PostMapping("/salvarLivro")
	public String salvarLivro(@ModelAttribute("livro") Livro livro) {
		livroService.salvarLivro(livro);
		return "redirect:/livro";
	}	
	
	@GetMapping("/atualizarLivro/{id}")
	public String atualizarLivro(@PathVariable ( value = "id") Long id, Model model) {
		Livro livro = livroService.getLivroById(id);
		model.addAttribute("livro", livro);
		return "atualizarLivro";
	}
	
	@GetMapping("/deletarLivro/{id}")
	public String deletarLivro(@PathVariable (value = "id") Long id) {
		livroService.deletarLivroById(id);
		return "redirect:/livro";
	}
	
	@GetMapping("/pageLivro/{pageNo}")
	public String livrosPaginacao(@PathVariable (value = "pageNo") int pageNoLivro, 
			                        @RequestParam("sortField") String sortFieldLivro,
		                        	@RequestParam("sortDir") String sortDirLivro,
		                         	Model model) {
		int pageSizeLivro = 5;
		
		Page<Livro> pageLivro = livroService.findPaginated(pageNoLivro, pageSizeLivro, sortFieldLivro, sortDirLivro);
		List<Livro> listaLivros = pageLivro.getContent();
		
		model.addAttribute("currentPage", pageNoLivro);
		model.addAttribute("totalPages", pageLivro.getTotalPages());
		model.addAttribute("totalItems", pageLivro.getTotalElements());
		
		model.addAttribute("sortField", sortFieldLivro);
		model.addAttribute("sortDir", sortDirLivro);
		model.addAttribute("reverseSortDir", sortDirLivro.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("listaLivros", listaLivros);
		return "livro";
	}
}
