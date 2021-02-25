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
import org.weblioteca.application.model.Funcionario;
import org.weblioteca.application.service.FuncionarioService;

@Controller
public class FuncionarioController {
	@Autowired
	FuncionarioService funcionarioService;
	
	@GetMapping("/funcionario")
	public String viewHomePage(Model model) {
		return funcionarioPaginacao(1, "nome", "asc", model);
	}
	
	@GetMapping("/novoFuncionario") 
	public String novoFuncionario(Model model) {
		Funcionario funcionario = new Funcionario();
		model.addAttribute("funcionario", funcionario);
		return "salvarFuncionario";
	}
	
	@PostMapping("/salvarFuncionario")
	public String salvarFuncionario(@ModelAttribute("funcionario") Funcionario funcionario) {
		funcionarioService.salvarFuncionario(funcionario);
		return "redirect:/";
	}
	
	@GetMapping("/atualizarFuncionario/{id}")
	public String atualizarFuncionario(@PathVariable ( value = "id") Long id, Model model) {
		Funcionario funcionario = funcionarioService.getFuncionarioById(id);
		model.addAttribute("funcionario", funcionario);
		return "atualizarFuncionario";
	}
	
	@GetMapping("/deletarFuncionario/{id}")
	public String deletarFuncionario(@PathVariable (value = "id") Long id) {
		funcionarioService.deletarFuncionarioById(id);
		return "redirect:/";
	}
	
	@GetMapping("/pageFuncionario/{pageNo}")
	public String funcionarioPaginacao(@PathVariable (value = "pageNo") int pageNo, 
			                           @RequestParam("sortField") String sortField,
		                        	   @RequestParam("sortDir") String sortDir,
		                         	   Model model) {
		int pageSize = 5;
		
		Page<Funcionario> page = funcionarioService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<Funcionario> listaFuncionarios = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("listaFuncionarios", listaFuncionarios);
		return "index";
	}
}
