package org.weblioteca.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.weblioteca.application.model.Cliente;
import org.weblioteca.application.repository.AutorRepository;
import org.weblioteca.application.repository.ClienteRepository;
import org.weblioteca.application.repository.EditoraRepository;
import org.weblioteca.application.repository.LivroRepository;

@SpringBootApplication
public class WebliotecaApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebliotecaApplication.class, args);
	}
}

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

	@Override
	public void run(String... args) throws Exception {
		
//		Cliente cliente = new Cliente();
//		cliente.setNome("Alan Turing");
//		cliente.setCpf("04663439988");
//		cliente.setSexo("Masculino");
//
//		clienteRepository.save(cliente);
	}
}
