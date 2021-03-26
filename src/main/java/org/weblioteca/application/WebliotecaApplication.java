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
import org.weblioteca.application.model.Fatura;
import org.weblioteca.application.model.Funcionario;
import org.weblioteca.application.model.Livro;
import org.weblioteca.application.model.Reserva;
import org.weblioteca.application.repository.AutorRepository;
import org.weblioteca.application.repository.ClienteRepository;
import org.weblioteca.application.repository.EditoraRepository;
import org.weblioteca.application.repository.EmprestimoRepository;
import org.weblioteca.application.repository.FaturaRepository;
import org.weblioteca.application.repository.FuncionarioRepository;
import org.weblioteca.application.repository.ReservaRepository;
import org.weblioteca.application.service.ClienteService;
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
		/*
		////////Processo até reservar ///////////////
		//Funcionario
		Funcionario funcionario = new Funcionario();
		funcionario.setNome("Severino Pedreira");
		funcionario.setFuncao("Atendente");
		funcionario.setDataAdmissao(java.sql.Date.valueOf("2019-03-22"));
		funcionario.setSexo("Masculino");
		funcionario.setSalario(2000.00);
		funcionario.setTelefone("44-3269-6542");
		funcionario.setLogin("Severino");
		funcionario.setSenha("123456");
		funcionario.setCpf("254.653.548-65");
		funcionarioRepository.save(funcionario);
		
		// Cliente1
		Cliente cliente1 = new Cliente();
		cliente1.setNome("Pedro Amancio");
		cliente1.setCpf("056.546.556-55");
		cliente1.setLogradouro("Rua das Laranjas");
		cliente1.setNumero("19B");
		cliente1.setTelefone("44-99988-5544");
		cliente1.setComplemento("Próximo ao parque do Jabuti");
		cliente1.setDataNascimento(java.sql.Date.valueOf("1989-11-01"));
		cliente1.setFoto("./img/fotos/126.jpg");
		cliente1.setSexo("Masculino");
		clienteRepository.save(cliente1);
		
		// Autor1
		Autor autor1 = new Autor();
		autor1.setNome("Robert C Martin");			
		autor1.setNascimento(java.sql.Date.valueOf("1999-01-01"));
		autor1.setOrigem("Paquistão");
		
		// Editora1
		Editora editora1 = new Editora();
		editora1.setNome("Atica");
		editora1.setCnpj("22.123.554/0001-56");
		
		// Livro1 (Tem Autor, Editora)
		Livro livro1 = new Livro();
		livro1.setTituloLivro("Clean Code");
		livro1.setAutor(autor1);
		livro1.setDisponivelEmprestimo(true);
		livro1.setEditora(editora1);
		livro1.setLocalizacao("Maringá");
		livro1.setQuantidade(10);
		livro1.setDataDeCompra(java.sql.Date.valueOf("2020-01-01"));
		livro1.setEdicao("1ª Edição");
		livroRepository.save(livro1);
		
		// Reserva
		Reserva reserva = new Reserva();
		reserva.setClienteId(cliente1.getClienteId());		
		reserva.setLivroId(livro1.getLivroId());		
		reserva.setDataReserva(java.sql.Date.valueOf("2021-03-23"));
		reservaRepository.save(reserva);
		
		////////Processo até Emprestar ///////////////
		// Cliente2
		Cliente cliente2 = new Cliente();
		cliente2.setNome("Amanda Salamandra");
		cliente2.setCpf("125.546.417-87");
		cliente2.setLogradouro("Rua das Peras");
		cliente2.setNumero("2.564");
		cliente2.setTelefone("44-98846-5656");
		cliente2.setComplemento("Próximo ao Balão do João");
		cliente2.setDataNascimento(java.sql.Date.valueOf("2002-06-10"));
		cliente2.setFoto("./img/fotos/127.jpg");
		cliente2.setSexo("Feminino");

		// Autor2
		Autor autor2 = new Autor();
		autor2.setNome("Waldomiro Santiago");			
		autor2.setNascimento(java.sql.Date.valueOf("1980-11-01"));
		autor2.setOrigem("EUA");

		// Editora2
		Editora editora2 = new Editora();
		editora2.setNome("Pearson");
		editora2.setCnpj("23.778.954/0001-65");
		
		// Livro2
		Livro livro2 = new Livro();
		livro2.setTituloLivro("O triste olhar de um pobre cego");
		livro2.setAutor(autor2);
		livro2.setDisponivelEmprestimo(true);
		livro2.setEditora(editora2);
		livro2.setLocalizacao("Sarandi");
		livro2.setQuantidade(2);
		livro2.setDataDeCompra(java.sql.Date.valueOf("2018-11-21"));
		livro2.setEdicao("2ª Edição");
		
		// Autor3
		Autor autor3 = new Autor();
		autor3.setNome("Pafuncio Dos Santos");			
		autor3.setNascimento(java.sql.Date.valueOf("1905-12-31"));
		autor3.setOrigem("Paraguay");
		
		// Editora3
		Editora editora3 = new Editora();
		editora3.setNome("Veja Mais");
		editora3.setCnpj("43.758.664/0001-89");
		
		// Livro3
		Livro livro3 = new Livro();
		livro3.setTituloLivro("V de Vigarista");
		livro3.setAutor(autor3);
		livro3.setDisponivelEmprestimo(true);
		livro3.setEditora(editora3);
		livro3.setLocalizacao("Paiçandu");
		livro3.setQuantidade(4);
		livro3.setDataDeCompra(java.sql.Date.valueOf("2019-01-17"));
		livro3.setEdicao("1ª Edição");
		
		// Lista de exemplares para emprestimo
		List<Livro> livrosList1 = new ArrayList<>();
		livrosList1.add(livro2);
		livrosList1.add(livro3);
		
		// Emprestimo (tem Cliente e Livro(s)
		Emprestimo emprestimo1 = new Emprestimo();
		emprestimo1.setCliente(cliente2);
		emprestimo1.setDataDevolucao(java.sql.Date.valueOf("2021-04-23"));
		emprestimo1.setDataDevolvido(null);
		emprestimo1.setDataEmprestimo(java.sql.Date.valueOf("2021-03-23"));
		emprestimo1.setExemplar(livrosList1);
		emprestimo1.setValorTotal(0);	
		emprestimoRepository.save(emprestimo1);
		
		////////Processo criação de novo empréstimo até Devolução ///////////////		
		// Cliente3
		Cliente cliente3 = new Cliente();
		cliente3.setNome("Fernanda Pires");
		cliente3.setCpf("675.566.427-44");
		cliente3.setLogradouro("Rua das Bananas");
		cliente3.setNumero("15.564");
		cliente3.setTelefone("44-3266-6677");
		cliente3.setComplemento("Ap 201");
		cliente3.setDataNascimento(java.sql.Date.valueOf("2000-08-02"));
		cliente3.setFoto("./img/fotos/122.jpg");
		cliente3.setSexo("Feminino");
		
		// Autor4
		Autor autor4 = new Autor();
		autor4.setNome("Mascarenhas de Moraes");			
		autor4.setNascimento(java.sql.Date.valueOf("1917-03-15"));
		autor4.setOrigem("Brasil");
		
		// Editora4
		Editora editora4 = new Editora();
		editora4.setNome("Portifólio");
		editora4.setCnpj("11.564.547/0001-41");
		
		// Livro4
		Livro livro4 = new Livro();
		livro4.setTituloLivro("Navegando em águas razas");
		livro4.setAutor(autor4);
		livro4.setDisponivelEmprestimo(true);
		livro4.setEditora(editora4);
		livro4.setLocalizacao("São Paulo");
		livro4.setQuantidade(1);
		livro4.setDataDeCompra(java.sql.Date.valueOf("2021-01-22"));
		livro4.setEdicao("6ª Edição");
		
		// Lista de exemplares para emprestimo
		List<Livro> livrosList2 = new ArrayList<>();
		livrosList2.add(livro4);

		// Emprestimo (tem Cliente e Livro(s)
		Emprestimo emprestimo2 = new Emprestimo();
		emprestimo2.setCliente(cliente3);
		emprestimo2.setDataDevolucao(java.sql.Date.valueOf("2021-03-22"));
		emprestimo2.setDataDevolvido(null);
		emprestimo2.setDataEmprestimo(java.sql.Date.valueOf("2021-02-22"));
		emprestimo2.setExemplar(livrosList2);
		emprestimo2.setValorTotal(1.89);	
		emprestimoRepository.save(emprestimo2);
		

		// Devolucao
		emprestimo2.setDataDevolvido(java.sql.Date.valueOf("2021-03-23"));		
		emprestimoRepository.save(emprestimo2);
		
		//////Processo do faturamento ///////////////
		// Fatura1
		Fatura fatura1 = new Fatura();
		fatura1.setClienteId(emprestimo2.getCliente().getClienteId());
		fatura1.setDataFatura(java.sql.Date.valueOf("2021-03-28"));
		fatura1.setDiasAtraso(5);
		fatura1.setValorFatura(8.00);
		faturaRepository.save(fatura1);
		*/
	}
}
