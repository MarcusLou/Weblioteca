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
	
	@GetMapping("/")
	public String viewHomePage(Model model) {
		return clientesPaginacao(1, "nome", "asc", model);
	}
	
	@GetMapping("/novoCliente") 
	public String novoCliente(Model clienteModel) {
		Cliente cliente = new Cliente();
		clienteModel.addAttribute("clienteView", cliente);
		return "salvarCliente";
	}
	
	@PostMapping("/salvarCliente")
	public String salvarCliente(@ModelAttribute("cliente") Cliente cliente) {
		clienteService.salvarCliente(cliente);
		return "redirect:/";
	}
	
	@GetMapping("/atualizarCliente/{id}")
	public String atualizarCliente(@PathVariable(value = "id") Long id, Model model) {
		Cliente cliente = clienteService.getClienteById(id);
		model.addAttribute("cliente", cliente);
		return "atualizarCliente";
	}
	
	@GetMapping("/pesquisarCliente/{nome}")
	public String pesquisarCliente(@PathVariable (value = "nome") String nome, Model model) {
		List<Cliente> cliente  = clienteService.findByNomeContainingIgnoreCase(nome);
		model.addAttribute("pesquisarCliente", cliente);
		return "redirect:/";
	}
	
	@GetMapping("/deletarCliente/{id}")
	public String deletarCliente(@PathVariable(value = "id") Long id) {
		clienteService.deletarClienteById(id);
		return "redirect:/";
	}
	
	@GetMapping("/page/{pageNo}")
	public String clientesPaginacao(@PathVariable(value = "pageNo") int pageNo, 
			                        @RequestParam("sortField") String sortField,
		                        	@RequestParam("sortDir") String sortDir,
		                         	Model model) {
		int pageSize = 5;
		
		Page<Cliente> page = clienteService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<Cliente> listaClientes = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("listaClientes", listaClientes);
		return "indexCliente";
	}
}
