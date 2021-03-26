package org.weblioteca.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.weblioteca.application.repository.AutorRepository;
import org.weblioteca.application.repository.ClienteRepository;
import org.weblioteca.application.repository.EditoraRepository;
import org.weblioteca.application.repository.EmprestimoRepository;
import org.weblioteca.application.repository.FaturaRepository;
import org.weblioteca.application.repository.FuncionarioRepository;
import org.weblioteca.application.repository.LivroRepository;
import org.weblioteca.application.repository.ReservaRepository;
import org.weblioteca.application.service.ClienteService;

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
	private ClienteService clienteService;

	@Autowired
	private EditoraRepository editoraRepository;

	@Autowired
	private AutorRepository autorRepository;

	@Autowired
	private LivroRepository livroRepository;

	@Autowired
	private EmprestimoRepository emprestimoRepository;

	@Autowired
	private FaturaRepository faturaRepository;
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	@Autowired
	private ReservaRepository reservaRepository;

	@Override
	public void run(String... args) throws Exception {

		
	}
}
