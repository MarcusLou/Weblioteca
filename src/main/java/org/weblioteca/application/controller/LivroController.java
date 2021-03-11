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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.weblioteca.application.model.Editora;
import org.weblioteca.application.model.Autor;
import org.weblioteca.application.model.Livro;
import org.weblioteca.application.service.EditoraService;
import org.weblioteca.application.service.LivroService;
import org.weblioteca.application.service.AutorService;

@Controller
public class LivroController {
	@Autowired
	LivroService livroService;
	@Autowired
	EditoraService editoraService;
	@Autowired
	AutorService autorService;
	
	@GetMapping("/livro")
	public String viewHomePage(Model model) {
		return livrosPaginacao(1, "tituloLivro", "asc", model);
	}
	
	@GetMapping("/novoLivro") 
	public String novoLivro(Model model) {
		Livro livro = new Livro();
		model.addAttribute("livro", livro);
		return "salvarLivro";
	}
	
	@PostMapping("/salvarLivro")
	public String salvarLivro(@ModelAttribute("livro") Livro livro) {
		livroService.salvarLivro(livro);
		return "redirect:/livro";
	}	
	
	@GetMapping("/atualizarLivro/{id}")
	public String atualizarLivro(@PathVariable ( value = "id") Long id, Model model) {
		Livro livro = livroService.getLivroById(id);
		model.addAttribute("livro", livro);
		return "atualizarLivro";
	}
	
	@GetMapping("/deletarLivro/{id}")
	public String deletarLivro(@PathVariable (value = "id") Long id) {
		livroService.deletarLivroById(id);
		return "redirect:/livro";
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
		return "livro";
	}
	
//	@RequestMapping(value = { "/listarEditoras" }, method = RequestMethod.GET)
//	public String ListarEditoras(Model model) { 
//	    List<Editora> listaEditoras = editoraService.getAllEditoras();
//	    model.addAttribute("listaEditoras", listaEditoras);
//	    return "editoras";
//	}
	
	
	
	@ModelAttribute("listaEditoras")
	public List<Editora> getlistaEditoras() {
	      List<Editora> listaEditoras = editoraService.getAllEditoras();
	      return listaEditoras;
	}
	
	@ModelAttribute("listaAutores")
	public List<Autor> getlistaAutores() {
	      List<Autor> listaAutores = autorService.getAllAutores();
	      return listaAutores;
	}
	
//	@ModelAttribute("retornaEditora")
//	public Editora retornaNomeEditora(@RequestParam("idLivro") Long idLivro) {
//		Editora editora;
//		Livro livro;
//		livro = livroService.getLivroById(idLivro);
//		editora = livro.getEditora();
//		return editora;
//	}
}
