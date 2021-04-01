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
	
	@GetMapping("/indexFaturar")
	public String viewHomePage(Model model) {	 
		return faturaPaginacao(1, "faturaId", "asc", model);
	}
	
	@GetMapping("/novoFatura") 
	public String novoFatura(Model model) {
//		Fatura fatura = new Fatura();
//		model.addAttribute("fatura", fatura);
//		List<Emprestimo> listEmprestimo = emprestimoRepository.findAll();
//	    model.addAttribute("listEmprestimo", listEmprestimo);
		return "salvarFatura";
	}
	
	@PostMapping("/salvarFatura")
	public String salvarFatura(@ModelAttribute("fatura") Fatura fatura) {
		faturaService.salvarFatura(fatura);
		return "redirect:/indexFaturar";
	}
	
	@GetMapping("/atualizarFatura/{id}")
	public String atualizarFatura(@PathVariable ( value = "id") Long id, Model model) {
//		Fatura fatura = faturaService.getFaturaById(id);
//		model.addAttribute("fatura", fatura);
//		List<Emprestimo> listEmprestimo = emprestimoRepository.findAll();
//	    model.addAttribute("listEmprestimo", listEmprestimo);	
		return "atualizarFatura";
	}
	
	@GetMapping("/faturarEmprestimo/{id}")
	public String faturarEmprestimo(@PathVariable (value = "id") Long id) {
	//	Emprestimo emprestimo = emprestimoRepository.findById(id);
		Fatura fatura1 = new Fatura();
		fatura1.setClienteId((long) 1);
		fatura1.setDataFatura(java.sql.Date.valueOf("2021-03-28"));
		fatura1.setDiasAtraso(5);
		fatura1.setValorFatura(8.00);
		faturaService.salvarFatura(fatura1);
		return "redirect:/indexFaturar";
	}
	
	@GetMapping("/deletarFatura/{id}")
	public String deletarFatura(@PathVariable (value = "id") Long id) {
		try {
			faturaService.deletarFaturaById(id);
			return "redirect:/indexFaturar";
		}catch (Exception $e) {
			return "redirect:/mensagemFatura";
		}
		
	}
	
	@GetMapping("/mensagemFatura") 
	public String mensagemFatura(Model model) {
		return "mensagemFatura";	
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
		return "indexFaturar";
	}
}
