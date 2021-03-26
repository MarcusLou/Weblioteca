package org.weblioteca.application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EmprestarLivroController {
	@GetMapping("/emprestarlivros")
	public String HomePage(Model model) {
		return "emprestarLivros";
	}
}
