package org.weblioteca.application;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.weblioteca.application.model.Autor;
import org.weblioteca.application.model.Cliente;
import org.weblioteca.application.model.Editora;
import org.weblioteca.application.model.Emprestimo;
import org.weblioteca.application.model.Livro;
import org.weblioteca.application.repository.AutorRepository;
import org.weblioteca.application.repository.ClienteRepository;
import org.weblioteca.application.repository.EditoraRepository;
import org.weblioteca.application.repository.EmprestimoRepository;
import org.weblioteca.application.repository.LivroRepository;

@SpringBootApplication
public class WebliotecaApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebliotecaApplication.class, args);
	}
}

//      HIBERNATE TUTORIAIS
//		https://www.javatpoint.com/hibernate-tutorial
//      https://www.tutorialspoint.com/hibernate/index.htm
//      https://pt.stackoverflow.com/questions/234755/diferen%C3%A7as-onetomany-manytomany-manytoone-onetoone

@Component
class DemoCommandLineRunner implements CommandLineRunner {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private EditoraRepository editoraRepository;

	@Autowired
	private AutorRepository autorRepository;

	@Autowired
	private LivroRepository livroRepository;
	
	@Autowired
	private EmprestimoRepository emprestimoRepository;

	@Override
	public void run(String... args) throws Exception {
		// Autor
		Autor robertMartin = new Autor();
		robertMartin.setNome("Robert C Martin");
		
		// Editora
		Editora	 atica = new Editora();
		atica.setNome("Atica");
		
		// Livro (Tem Autor, Editora)
		Livro cleanCode = new Livro();
		cleanCode.setTituloLivro("Clean Code");
		cleanCode.setAutor(robertMartin);
		cleanCode.setDisponivelEmprestimo(true);
		cleanCode.setEditora(atica);
		
		// Cliente
		Cliente pedro = new Cliente();
		pedro.setNome("Pedro Amancio");
		
		List<Livro> lstLivro = new ArrayList<>();
		lstLivro.add(cleanCode);
		
		// Emprestimo (tem Cliente e Livro(s)
		Emprestimo emprestimo = new Emprestimo();
		emprestimo.setCliente(pedro);
		emprestimo.setLivros(lstLivro);
//		emprestimoRepository.save(emprestimo);
		
	}
}
