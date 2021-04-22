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
import org.weblioteca.application.model.Editora;
import org.weblioteca.application.model.Autor;
import org.weblioteca.application.model.Livro;
import org.weblioteca.application.repository.AutorRepository;
import org.weblioteca.application.repository.EditoraRepository;
import org.weblioteca.application.service.EditoraService;
import org.weblioteca.application.service.LivroService;
import org.weblioteca.application.service.AutorService;

@Controller
public class LivroController {
	

	@Autowired
	private AutorRepository autorRepository;
	@Autowired
	private EditoraRepository editoraRepository;
	@Autowired
	LivroService livroService;
	@Autowired
	EditoraService editoraService;
	@Autowired
	AutorService autorService;
	
	@GetMapping("/indexLivro")
	public String viewHomePage(Model model) {
		return livrosPaginacao(1, "tituloLivro", "asc", model);
	}
	
	@GetMapping("/novoLivro") 
	public String novoLivro(Model model) {
		Livro livro = new Livro();
		model.addAttribute("livro", livro);
		List<Autor> listAutor = autorRepository.findAll();
	    model.addAttribute("listAutor", listAutor);
		List<Editora> listEditora = editoraRepository.findAll();
	    model.addAttribute("listEditora", listEditora);
		
		return "salvarLivro";
	}
	
	@PostMapping("/salvarLivro")
	public String salvarLivro(@ModelAttribute("livro") Livro livro) {
			livroService.salvarLivro(livro);
			return "redirect:/indexLivro";
	}	
	
	@GetMapping("/atualizarLivro/{id}")
	public String atualizarLivro(@PathVariable ( value = "id") Long id, Model model) {
		Livro livro = livroService.getLivroById(id);
		model.addAttribute("livro", livro);
		List<Autor> listAutor = autorRepository.findAll();
	    model.addAttribute("listAutor", listAutor);
		List<Editora> listEditora = editoraRepository.findAll();
	    model.addAttribute("listEditora", listEditora);
	    
		return "atualizarLivro";
	}
	
	@GetMapping("/deletarLivro/{id}")
	public String deletarLivro(@PathVariable (value = "id") Long id) {
		try {
			Livro livro = livroService.getLivroById(id);
			livro.setExcluido(true);
			livroService.salvarLivro(livro);
			return "redirect:/indexLivro";
		}catch (Exception $e)  {			
			return "redirect:/mensagemLivro";	
		}
	}
	
	@GetMapping("/ativarLivro/{id}")
	public String ativarLivro(@PathVariable (value = "id") Long id) {
		try {
			Livro livro = livroService.getLivroById(id);
			livro.setExcluido(false);
			livroService.salvarLivro(livro);
			return "redirect:/indexLivro";
		}catch (Exception $e)  {			
			return "redirect:/mensagemLivro";	
		}
	}
	
	@GetMapping("/mensagemLivro") 
	public String mensagemLivro(Model model) {
		return "mensagemLivro";	
	}
	
	@GetMapping("/pageLivro/{pageNo}")
	public String livrosPaginacao(@PathVariable (value = "pageNo") int pageNoLivro, 
			                        @RequestParam("sortField") String sortFieldLivro,
		                        	@RequestParam("sortDir") String sortDirLivro,
		                         	Model model) {
		int pageSizeLivro = 5;
		
		Page<Livro> pageLivro = livroService.findPaginated(pageNoLivro, pageSizeLivro, sortFieldLivro, sortDirLivro);
		List<Livro> listaLivros = pageLivro.getContent();
		
		model.addAttribute("currentPage", pageNoLivro);
		model.addAttribute("totalPages", pageLivro.getTotalPages());
		model.addAttribute("totalItems", pageLivro.getTotalElements());
		
		model.addAttribute("sortField", sortFieldLivro);
		model.addAttribute("sortDir", sortDirLivro);
		model.addAttribute("reverseSortDir", sortDirLivro.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("listaLivros", listaLivros);
		return "indexLivro";
	}
}
