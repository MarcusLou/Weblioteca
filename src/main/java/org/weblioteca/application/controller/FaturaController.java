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
import org.weblioteca.application.service.FaturaService;
import org.weblioteca.application.model.Fatura;

@Controller
public class FaturaController {
	@Autowired
	FaturaService faturaService;

	@GetMapping("/fatura")
	public String viewHomePage(Model model) {
		return faturaPaginacao(1, "faturaId", "asc", model);
	}
	
	@GetMapping("/novoFatura") 
	public String novoFatura(Model model) {
		Fatura fatura = new Fatura();
		model.addAttribute("fatura", fatura);
		return "salvarFatura";
	}
	
	@PostMapping("/salvarFatura")
	public String salvarFatura(@ModelAttribute("fatura") Fatura fatura) {
		faturaService.salvarFatura(fatura);
		return "redirect:/fatura";
	}
	
	@GetMapping("/atualizarFatura/{id}")
	public String atualizarFatura(@PathVariable ( value = "id") Long id, Model model) {
		Fatura fatura = faturaService.getFaturaById(id);
		model.addAttribute("fatura", fatura);
		return "atualizarFatura";
	}
	
	@GetMapping("/deletarFatura/{id}")
	public String deletarFatura(@PathVariable (value = "id") Long id) {
		faturaService.deletarFaturaById(id);
		return "redirect:/fatura";
	}
	
	@GetMapping("/pageFatura/{pageNo}")
	public String faturaPaginacao(@PathVariable (value = "pageNo") int pageNoFatura, 
			                        @RequestParam("sortField") String sortFieldFatura,
		                        	@RequestParam("sortDir") String sortDirFatura,
		                         	Model model) {
		int pageSizeFatura = 5;
		
		Page<Fatura> pageFatura = faturaService.findPaginated(pageNoFatura, pageSizeFatura, sortFieldFatura, sortDirFatura);
		List<Fatura> listaFatura = pageFatura.getContent();
		
		model.addAttribute("currentPage", pageNoFatura);
		model.addAttribute("totalPages", pageFatura.getTotalPages());
		model.addAttribute("totalItems", pageFatura.getTotalElements());
		
		model.addAttribute("sortField", sortFieldFatura);
		model.addAttribute("sortDir", sortDirFatura);
		model.addAttribute("reverseSortDir", sortDirFatura.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("listaFatura", listaFatura);
		return "fatura";
	}
}
