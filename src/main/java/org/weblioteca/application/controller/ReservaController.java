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
import org.weblioteca.application.model.Cliente;
import org.weblioteca.application.model.Reserva;
import org.weblioteca.application.model.Livro;
import org.weblioteca.application.repository.ClienteRepository;
import org.weblioteca.application.repository.LivroRepository;
import org.weblioteca.application.service.ReservaService;

@Controller
public class ReservaController {	

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private LivroRepository livroRepository;
	
	@Autowired
	ReservaService reservaService;
	
	@GetMapping("/indexReserva")
	public String viewHomePage(Model model) {
		
		List<Cliente> listCliente = clienteRepository.findAll();
	    model.addAttribute("listCliente", listCliente);
	    
		List<Livro> listLivro = livroRepository.findAll();
	    model.addAttribute("listLivro", listLivro);
	    
		return reservaPaginacao(1, "clienteId", "asc", model);
	}
	
	@GetMapping("/novaReserva") 
	public String novaReserva(Model model) {
		Reserva reserva = new Reserva();
		model.addAttribute("reserva", reserva);
		return "salvarReserva";
	}
	
	@PostMapping("/salvarReserva")
	public String salvarReserva(@ModelAttribute("reserva") Reserva reserva) {
		reservaService.salvarReserva(reserva);
		return "redirect:/indexReserva";
	}
	
	@GetMapping("/atualizarReserva/{id}")
	public String atualizarReserva(@PathVariable ( value = "id") Long id, Model model) {
		Reserva reserva = reservaService.getReservaById(id);
		model.addAttribute("reserva", reserva);
		return "atualizarReserva";
	}
	
	@GetMapping("/deletarReserva/{id}")
	public String deletarReserva(@PathVariable (value = "id") Long id) {
		reservaService.deletarReservaById(id);
		return "redirect:/indexReserva";
	}
	
	@GetMapping("/pageReserva/{pageNo}")
	public String reservaPaginacao(@PathVariable (value = "pageNo") int pageNo, 
			                           @RequestParam("sortField") String sortField,
		                        	   @RequestParam("sortDir") String sortDir,
		                         	   Model model) {
		int pageSize = 5;
		
		Page<Reserva> page = reservaService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<Reserva> listaReservas = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("listaReservas", listaReservas);
		
		return "indexReserva";
	}
}
