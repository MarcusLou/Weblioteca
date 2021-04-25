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
import org.weblioteca.application.model.Cliente;
import org.weblioteca.application.model.Reserva;
import org.weblioteca.application.model.Livro;
import org.weblioteca.application.repository.ClienteRepository;
import org.weblioteca.application.repository.LivroRepository;
import org.weblioteca.application.service.ClienteService;
import org.weblioteca.application.service.LivroService;
import org.weblioteca.application.service.ReservaService;

@Controller
public class ReservaController {	

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private LivroRepository livroRepository;
	
	@Autowired
	ClienteService clienteService;
	
	@Autowired
	LivroService livroService;
	
	@Autowired
	ReservaService reservaService;
	
	@GetMapping("/indexReserva")
	public String viewHomePage(Model model) {		
		List<Cliente> listCliente = clienteRepository.findAll();
	    model.addAttribute("listCliente", listCliente);	    
		List<Livro> listLivro = livroRepository.findAll();
	    model.addAttribute("listLivro", listLivro);	    
		Integer ativo = 1;
		return reservasPaginacao(1, "clienteId", "asc", model, ativo);
	}
	
	@GetMapping("/realizarEmprestimo") 
	public String realizarEmprestimo(Model model) {
		Reserva reserva = new Reserva();
		model.addAttribute("reserva", reserva);
		List<Cliente> listCliente = clienteRepository.findAll();
	    model.addAttribute("listCliente", listCliente);
		List<Livro> listLivro = livroRepository.findAll();
	    model.addAttribute("listLivro", listLivro);
	    LocalDate now = LocalDate.now();
	    model.addAttribute("now", now);
		return "salvarReserva";
	}
	
	@PostMapping("/salvarReserva")
	public String salvarReserva(@ModelAttribute("reserva") Reserva reserva) {
		reservaService.salvarReserva(reserva);
		return "redirect:/indexReserva";
	}
	
	/*
	@GetMapping("/atualizarReserva/{id}")
	public String atualizarReserva(@PathVariable ( value = "id") Long id, Model model) {
		Reserva reserva = reservaService.getReservaById(id);
		model.addAttribute("reserva", reserva);
		List<Cliente> listCliente = clienteRepository.findAll();
	    model.addAttribute("listCliente", listCliente);
		List<Livro> listLivro = livroRepository.findAll();
	    model.addAttribute("listLivro", listLivro);
	    LocalDate now = LocalDate.now();
	    model.addAttribute("now", now);
		return "atualizarReserva";
	}
	*/
	
	
	@GetMapping("/deletarReserva/{id}")
	public String deletarReserva(@PathVariable (value = "id") Long id) {
		try {
			Reserva reserva = reservaService.getReservaById(id);
			reserva.setAtivo(0);
			reservaService.salvarReserva(reserva);
			return "redirect:/indexReserva";
		}catch (Exception $e)  {			
			return "redirect:/mensagemReserva";	
		}
	}
	
	@GetMapping("/ativarReserva/{id}")
	public String ativarReserva(@PathVariable (value = "id") Long id) {		
		Reserva reserva = reservaService.getReservaById(id);
		reserva.setAtivo(1);
		reservaService.salvarReserva(reserva);
		return "redirect:/indexReserva";		
	}
	
	@RequestMapping("/indexReserva/{pesquisa}")
    public String pesquisar(Model model, @Param("ativo") Integer ativo, @Param("pesquisa") String pesquisa) {
        List<Reserva> listaReservas = reservaService.pesquisar(ativo, pesquisa);
        model.addAttribute("listaReservas", listaReservas);
		return "indexReserva";
    }

	@GetMapping("/mensagemReserva") 
	public String mensagemReserva(Model model) {
		return "mensagemReserva";	
	}
	
	@GetMapping("/pageReserva/{pageNo}")
	public String reservasPaginacao(@PathVariable (value = "pageNo") int pageNoReserva, 
			                           @RequestParam("sortField") String sortFieldReserva,
		                        	   @RequestParam("sortDir") String sortDirReserva,
		                         	   Model model,
		                         	   @Param("ativo") Integer ativo) {
		int pageSizeReserva = 7;
		if (ativo == null) {
			ativo = 1;
		}
		
		Page<Reserva> pageReserva = reservaService.findPaginated(pageNoReserva, pageSizeReserva, sortFieldReserva, sortDirReserva, ativo);
		List<Reserva> listaReservas = pageReserva.getContent();
		
		model.addAttribute("currentPage", pageNoReserva);
		model.addAttribute("totalPages", pageReserva.getTotalPages());
		model.addAttribute("totalItems", pageReserva.getTotalElements());
		
		model.addAttribute("sortField", sortFieldReserva);
		model.addAttribute("sortDir", sortDirReserva);
		model.addAttribute("reverseSortDir", sortDirReserva.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("listaReservas", listaReservas);
		
		return "indexReserva";
	}
}
