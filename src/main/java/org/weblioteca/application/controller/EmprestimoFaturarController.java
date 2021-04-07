package org.weblioteca.application.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.weblioteca.application.model.Emprestimo;
import org.weblioteca.application.model.Fatura;
import org.weblioteca.application.service.EmprestimoFaturarService;
import org.weblioteca.application.service.FaturaService;

@Controller
public class EmprestimoFaturarController {
	
	@Autowired
	EmprestimoFaturarService emprestimoFaturarService;
	
	@Autowired
	FaturaService faturaService;

	@GetMapping("/indexFaturarEmprestimos")
	public String viewHomePage(Model model) {
		return emprestimoFaturarPaginacao(1, "emprestimoId", "asc", model);
	}
	
	@GetMapping("/faturarEmprestimo/{id}")
	public String faturarEmprestimo(@PathVariable (value = "id") Long id) {
		Emprestimo emprestimo = emprestimoFaturarService.getEmprestimoById(id);
		Date date = new Date();
		Fatura fatura1 = new Fatura();
		fatura1.setClienteId(emprestimo.getCliente().getClienteId());
		fatura1.setDataFatura((java.sql.Date) date);
		fatura1.setDiasAtraso((int) ((emprestimo.getDataDevolvido().getTime() - emprestimo.getDataDevolucao().getTime()) / (1000*60*60*24)));
		fatura1.setValorFatura(emprestimo.getValorTotal());
		faturaService.salvarFatura(fatura1);
		return "redirect:/indexFaturar";
	}
	
	@GetMapping("/pageEmprestimoFaturar/{pageNo}")
	public String emprestimoFaturarPaginacao(@PathVariable (value = "pageNo") int pageNoEmprestimoFaturar, 
			                        @RequestParam("sortField") String sortFieldEmprestimoFaturar,
		                        	@RequestParam("sortDir") String sortDirEmprestimoFaturar,
		                         	Model model) {
		int pageSizeEmprestimoFaturar = 5;
		
		Page<Emprestimo> pageEmprestimoFaturar = emprestimoFaturarService.findPaginated(pageNoEmprestimoFaturar, pageSizeEmprestimoFaturar, sortFieldEmprestimoFaturar, sortDirEmprestimoFaturar);
		List<Emprestimo> listaEmprestimoFaturar = pageEmprestimoFaturar.getContent();
		
		model.addAttribute("currentPage", pageNoEmprestimoFaturar);
		model.addAttribute("totalPages", pageEmprestimoFaturar.getTotalPages());
		model.addAttribute("totalItems", pageEmprestimoFaturar.getTotalElements());
		
		model.addAttribute("sortField", sortFieldEmprestimoFaturar);
		model.addAttribute("sortDir", sortDirEmprestimoFaturar);
		model.addAttribute("reverseSortDir", sortDirEmprestimoFaturar.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("listaEmprestimoFaturar", listaEmprestimoFaturar);
		return "indexFaturarEmprestimos";
	}
}
