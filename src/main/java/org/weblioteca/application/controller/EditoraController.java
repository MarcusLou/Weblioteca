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
import org.weblioteca.application.model.Editora;
import org.weblioteca.application.service.EditoraService;

@Controller
public class EditoraController {
	@Autowired
	EditoraService editoraService;
	
	@GetMapping("/indexEditora")
	public String viewHomePage(Model model) {
		return editorasPaginacao(1, "nome", "asc", model);
	}
	
	@GetMapping("/novaEditora") 
	public String novaEditora(Model model) {
		Editora editora = new Editora();
		model.addAttribute("editora", editora);
		return "salvarEditora";
	}
	
	@PostMapping("/salvarEditora")
	public String salvarEditora(@ModelAttribute("editora") Editora editora) {
		editoraService.salvarEditora(editora);
		return "redirect:/indexEditora";
	}
	
	@GetMapping("/atualizarEditora/{id}")
	public String atualizarEditora(@PathVariable ( value = "id") Long id, Model model) {
		Editora editora = editoraService.getEditoraById(id);
		model.addAttribute("editora", editora);
		return "atualizarEditora";
	}
	
	@GetMapping("/deletarEditora/{id}")
	public String deletarEditora(@PathVariable (value = "id") Long id) {
		try {
			editoraService.deletarEditoraById(id);
			return "redirect:/indexEditora";
		}catch (Exception $e)  {			
			return "redirect:/mensagemEditora";	
		}
	}
	
	@GetMapping("/mensagemEditora") 
	public String mensagemEditora(Model model) {
		return "mensagemEditora";	
	}
	
	@GetMapping("/pageEditora/{pageNo}")
	public String editorasPaginacao(@PathVariable (value = "pageNo") int pageNoEditora, 
			                        @RequestParam("sortField") String sortFieldEditora,
		                        	@RequestParam("sortDir") String sortDirEditora,
		                         	Model model) {
		int pageSizeEditora = 5;
		
		Page<Editora> pageEditora = editoraService.findPaginated(pageNoEditora, pageSizeEditora, sortFieldEditora, sortDirEditora);
		List<Editora> listaEditoras = pageEditora.getContent();
		
		model.addAttribute("currentPage", pageNoEditora);
		model.addAttribute("totalPages", pageEditora.getTotalPages());
		model.addAttribute("totalItems", pageEditora.getTotalElements());
		
		model.addAttribute("sortField", sortFieldEditora);
		model.addAttribute("sortDir", sortDirEditora);
		model.addAttribute("reverseSortDir", sortDirEditora.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("listaEditoras", listaEditoras);
		return "indexEditora";
	}
}
