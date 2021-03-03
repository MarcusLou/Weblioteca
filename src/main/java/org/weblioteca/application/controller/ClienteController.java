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
import org.weblioteca.application.service.ClienteService;

@Controller
public class ClienteController {
	@Autowired
	ClienteService clienteService;
	
	@GetMapping("/cliente")
	public String viewHomePage(Model model) {
		return clientesPaginacao(1, "nome", "asc", model);
	}
	
	@GetMapping("/novoCliente") 
	public String novoCliente(Model model) {
		Cliente cliente = new Cliente();
		model.addAttribute("cliente", cliente);
		return "salvarCliente";
	}
	
	@PostMapping("/salvarCliente")
	public String salvarCliente(@ModelAttribute("cliente") Cliente cliente) {
		clienteService.salvarCliente(cliente);
		return "redirect:/cliente";
	}
	
	@GetMapping("/atualizarCliente/{id}")
	public String atualizarCliente(@PathVariable ( value = "id") Long id, Model model) {
		Cliente cliente = clienteService.getClienteById(id);
		model.addAttribute("cliente", cliente);
		return "atualizarCliente";
	}
	
	@GetMapping("/deletarCliente/{id}")
	public String deletarCliente(@PathVariable (value = "id") Long id) {
		clienteService.deletarClienteById(id);
		return "redirect:/cliente";
	}
	
	@GetMapping("/pageCliente/{pageNo}")
	public String clientesPaginacao(@PathVariable (value = "pageNo") int pageNoCliente, 
			                        @RequestParam("sortField") String sortFieldCliente,
		                        	@RequestParam("sortDir") String sortDirCliente,
		                         	Model model) {
		int pageSizeCliente = 5;
		
		Page<Cliente> pageCliente = clienteService.findPaginated(pageNoCliente, pageSizeCliente, sortFieldCliente, sortDirCliente);
		List<Cliente> listaClientes = pageCliente.getContent();
		
		model.addAttribute("currentPage", pageNoCliente);
		model.addAttribute("totalPages", pageCliente.getTotalPages());
		model.addAttribute("totalItems", pageCliente.getTotalElements());
		
		model.addAttribute("sortField", sortFieldCliente);
		model.addAttribute("sortDir", sortDirCliente);
		model.addAttribute("reverseSortDir", sortDirCliente.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("listaClientes", listaClientes);
		return "cliente";
	}
}
