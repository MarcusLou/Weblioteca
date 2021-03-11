package org.weblioteca.application;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import org.weblioteca.application.model.Cliente;
import org.weblioteca.application.repository.ClienteRepository;
import org.weblioteca.application.service.ClienteServiceImpl;

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
	ClienteServiceImpl clienteServiceImpl;

	@Override
	public void run(String... args) throws Exception {
//		Teste metódo para pesquisar um clinte
//		List<Cliente> cliente = new ArrayList<>();
//		cliente = clienteServiceImpl.findByNomeContainingIgnoreCase("miu");
//		for (Cliente x : cliente) {
//			 System.out.println(x.getNome() + " : " + x.getSexo());
//		}
		        
	}
}
