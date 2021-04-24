package org.weblioteca.application.controller;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.weblioteca.application.Test.EmprestimoFaturaControllerTest;
import org.weblioteca.application.builder.FaturaBuilder;
import org.weblioteca.application.model.Emprestimo;
import org.weblioteca.application.model.Fatura;
import org.weblioteca.application.model.Livro;
import org.weblioteca.application.model.Reserva;
import org.weblioteca.application.service.EmprestimoFaturarService;
import org.weblioteca.application.service.FaturaService;
import org.weblioteca.application.service.LivroService;
import org.weblioteca.application.service.ReservaService;

@Controller
public class EmprestimoFaturarController {
	
	@Autowired
	EmprestimoFaturarService emprestimoFaturarService;
	
	@Autowired
	ReservaService reservaService;
	
	@Autowired
	LivroService livroService;
	
	@Autowired
	FaturaService faturaService;

	@GetMapping("/indexFaturarEmprestimos")
	public String viewHomePage(Model model){
		JUnitCore junit = new JUnitCore();
		junit.addListener(new TextListener(System.out));
		junit.run(EmprestimoFaturaControllerTest.class);
		return emprestimoFaturarPaginacao(1, "emprestimoId", "asc", model);
	}
	
	@GetMapping("/indexProlongarEmprestimo")
	public String viewHomePage2(Model model) {
		return emprestimoProlongarPaginacao(1, "emprestimoId", "asc", model);
	}
	
	public Fatura criarFatura(Emprestimo emprestimo) {
		LocalDate date = LocalDate.now();
		Fatura fatura1 = FaturaBuilder.builder()
				.addClienteId(emprestimo.getCliente().getClienteId())
				.addDataFatura(date)
				.addDiasAtraso((int) ((emprestimo.getDataDevolvido().getTime() - emprestimo.getDataDevolucao().getTime()) / (1000*60*60*24)))
				.addIdEmprestimo(emprestimo.getEmprestimoId())
				.addValorFatura(emprestimo.getValorTotal())
				.get();
		return fatura1;
	}
	
	@GetMapping("/faturarEmprestimo/{id}")
	public String faturarEmprestimo(@PathVariable (value = "id") Long id) {
		Emprestimo emprestimo = emprestimoFaturarService.getEmprestimoById(id);
		//java.util.Date utilDate = new java.util.Date();
//		LocalDate date = LocalDate.now();
		Fatura fatura1 = new Fatura();
		fatura1 = criarFatura(emprestimo);
		faturaService.salvarFatura(fatura1);
		emprestimo.setFaturado(true);
		emprestimoFaturarService.salvarEmprestimo(emprestimo);
		return "redirect:/indexFaturar";
	}
	
	@GetMapping("/prolongarEmprestimo/{id}")
	public String prolongarEmprestimo(@PathVariable (value = "id") Long id) {
		boolean PodeProlongar=true;
		Emprestimo emprestimo = emprestimoFaturarService.getEmprestimoById(id);
		List<Reserva> listaReservas = reservaService.getAllReservas();
		List<Livro> listaLivros = emprestimo.getExemplar();
		Livro livro = new Livro();  
		Calendar cal = Calendar.getInstance();
		cal.setTime(emprestimo.getDataDevolucao());
		cal.add(Calendar.DAY_OF_MONTH, 7);
		java.util.Date data = new java.util.Date();
		data = cal.getTime();
		Instant instant = data.toInstant();
		ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());
		LocalDate date = zdt.toLocalDate();
		java.sql.Date dataSql = new java.sql.Date(data.getTime());
		for (Reserva r: listaReservas) {
			livro = livroService.getLivroById(r.getLivroId());
			if ((listaLivros.contains(livro)) && (r.getDataReserva().isBefore(date))) {
				PodeProlongar = false;
			}
		}
		if (PodeProlongar == true) {
			emprestimo.setDataDevolucao(dataSql);
			emprestimoFaturarService.salvarEmprestimo(emprestimo);
			return "redirect:/indexProlongarEmprestimo";
		} else {
			return "redirect:/mensagemProlongarEmprestimo";
		}
	}
	
	@GetMapping("/mensagemProlongarEmprestimo") 
	public String mensagemProlongarEmprestimo(Model model) {
		return "mensagemProlongarEmprestimo";	
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
	
	@GetMapping("/pageEmprestimoProlongar/{pageNo}")
	public String emprestimoProlongarPaginacao(@PathVariable (value = "pageNo") int pageNoEmprestimoFaturar, 
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
		return "indexProlongarEmprestimo";
	}
}
