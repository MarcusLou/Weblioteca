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
	
	@GetMapping("/autor")
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
		return "redirect:/";
	}
	
	@GetMapping("/atualizarAutor/{id}")
	public String atualizarAutor(@PathVariable ( value = "id") Long id, Model model) {
		Autor autor = autorService.getAutorById(id);
		model.addAttribute("autor", autor);
		return "atualizarAutor";
	}
	
	@GetMapping("/deletarAutor/{id}")
	public String deletarAutor(@PathVariable (value = "id") Long id) {
		autorService.deletarAutorById(id);
		return "redirect:/";
	}
	
	@GetMapping("/page/{pageNo}")
	public String autoresPaginacao(@PathVariable (value = "pageNo") int pageNo, 
			                        @RequestParam("sortField") String sortField,
		                        	@RequestParam("sortDir") String sortDir,
		                         	Model model) {
		int pageSize = 5;
		
		Page<Autor> page = autorService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<Autor> listaAutores = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("listaAutores", listaAutores);
		return "index";
	}
}
