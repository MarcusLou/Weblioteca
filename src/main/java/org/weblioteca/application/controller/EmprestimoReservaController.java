package org.weblioteca.application.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import org.weblioteca.application.model.Emprestimo;
import org.weblioteca.application.model.Reserva;
import org.weblioteca.application.model.Livro;
import org.weblioteca.application.repository.ClienteRepository;
import org.weblioteca.application.repository.LivroRepository;
import org.weblioteca.application.service.ClienteService;
import org.weblioteca.application.service.LivroService;
import org.weblioteca.application.service.ReservaService;

@Controller
public class EmprestimoReservaController {	

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
	
	@GetMapping("/emprestimoReserva")
	public String viewHomePage(Model model) {		
		List<Cliente> listCliente = clienteRepository.findAll();
	    model.addAttribute("listCliente", listCliente);	    
		List<Livro> listLivro = livroRepository.findAll();
	    model.addAttribute("listLivro", listLivro);	    
		Integer ativo = 1;
		return emprestimoReservasPaginacao(1, "clienteId", "asc", model, ativo);
	}
	
		
	@GetMapping("/emprestarReserva/{id}")
	public String emprestarReserva(@PathVariable (value = "id") Long id) {
		try {
			Reserva reserva = reservaService.getReservaById(id);
			reserva.setAtivo(0);
			reservaService.salvarReserva(reserva);
			Emprestimo emprestimo = new Emprestimo();
			emprestimo.setCliente(getClienteById(reserva.getClienteId()));
			List<Livro> listaExemplar = new ArrayList<>();
			listaExemplar.add(getLivroById(reserva.getLivroId()));
			emprestimo.setExemplar(listaExemplar);
			emprestimo.setDataEmprestimo(reserva.getDataReserva());
			emprestimo.setDataDevolucao(reserva.getDataReserva().plusDays(7));
			emprestimo.setDataDevolvido(null);
			emprestimo.setFaturado(null);
			reservaService.salvarEmprestimo(emprestimo);
			return "redirect:/emprestimoReserva";
		}catch (Exception $e)  {			
			return "redirect:/mensagemEmprestimoReserva";	
		}
	}
	
	private Cliente getClienteById(Long clienteId) {
		Optional<Cliente> optional = clienteRepository.findById(clienteId);
		Cliente cliente = null;
		if (optional.isPresent()) {
			cliente = optional.get();
		} else {
			Cliente clienteNull = null;
			return clienteNull;
		}
		return cliente;
	}
	
	private Livro getLivroById(Long livroId) {
		Optional<Livro> optional = livroRepository.findById(livroId);
		Livro livro = null;
		if (optional.isPresent()) {
			livro = optional.get();
		} else {
			Livro livroNull = null;
			return livroNull;
		}
		return livro;
	}

	@GetMapping("/mensagemEmprestimoReserva") 
	public String mensagemEmprestimoReserva(Model model) {
		return "mensagemEmprestimoReserva";	
	}
		
	@RequestMapping("/emprestimoReserva/{pesquisa}")
    public String pesquisar(Model model, @Param("ativo") Integer ativo, @Param("pesquisa") String pesquisa) {
		List<Cliente> listCliente = clienteRepository.findAll();
	    model.addAttribute("listCliente", listCliente);	    
		List<Livro> listLivro = livroRepository.findAll();
	    model.addAttribute("listLivro", listLivro);	    
        List<Reserva> listaReservas = reservaService.pesquisar(ativo, pesquisa);
        model.addAttribute("listaReservas", listaReservas);
        return "emprestimoReserva";
    }

	
	@GetMapping("/pageEmprestimoReserva/{pageNo}")
	public String emprestimoReservasPaginacao(@PathVariable (value = "pageNo") int pageNoReserva, 
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
		List<Cliente> listCliente = clienteRepository.findAll();
	    model.addAttribute("listCliente", listCliente);	    
		List<Livro> listLivro = livroRepository.findAll();
	    model.addAttribute("listLivro", listLivro);	  
		
		return "emprestimoReserva";
	}
}
