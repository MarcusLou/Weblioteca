package org.weblioteca.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
public class CancelarReservaController {	

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
	
	@GetMapping("/cancelarReserva")
	public String viewHomePage(Model model) {		
		List<Cliente> listCliente = clienteRepository.findAll();
	    model.addAttribute("listCliente", listCliente);	    
		List<Livro> listLivro = livroRepository.findAll();
	    model.addAttribute("listLivro", listLivro);	    
		return cancelarReservasPaginacao(1, "clienteId", "asc", model);
	}
		
	@GetMapping("/cancelarReserva/{id}")
	public String cancelarReserva(@PathVariable (value = "id") Long id) {
		try {
			reservaService.cancelarReservaById(id);
			return "redirect:/cancelarReserva";
		}catch (Exception $e)  {			
			return "redirect:/mensagemCancelarReserva";	
		}
	}
		
	@RequestMapping("/cancelarReserva/{pesquisa}")
    public String pesquisar(Model model, @Param("ativo") Integer ativo, @Param("pesquisa") String pesquisa) {
		List<Cliente> listCliente = clienteRepository.findAll();
	    model.addAttribute("listCliente", listCliente);	    
		List<Livro> listLivro = livroRepository.findAll();
	    model.addAttribute("listLivro", listLivro);	    
        List<Reserva> listaReservas = reservaService.pesquisar(ativo, pesquisa);
        model.addAttribute("listaReservas", listaReservas);
		return "cancelarReserva";
    }
/*
	@GetMapping("/mensagemCancelarReserva") 
	public String mensagemReserva(Model model) {
		return "mensagemCancelarReserva";	
	}*/
	
	@GetMapping("/pageCancelarReserva/{pageNo}")
	public String cancelarReservasPaginacao(@PathVariable (value = "pageNo") int pageNoReserva, 
			                           @RequestParam("sortField") String sortFieldReserva,
		                        	   @RequestParam("sortDir") String sortDirReserva,
		                         	   Model model){
		int pageSizeReserva = 7;
		Page<Reserva> pageReserva = reservaService.findPaginatedC(pageNoReserva, pageSizeReserva, sortFieldReserva, sortDirReserva);
		List<Reserva> listaReservas = pageReserva.getContent();
		
		model.addAttribute("currentPage", pageNoReserva);
		model.addAttribute("totalPages", pageReserva.getTotalPages());
		model.addAttribute("totalItems", pageReserva.getTotalElements());
		
		model.addAttribute("sortField", sortFieldReserva);
		model.addAttribute("sortDir", sortDirReserva);
		model.addAttribute("reverseSortDir", sortDirReserva.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("listaReservas", listaReservas);
		
		return "cancelarReserva";
	}
}
