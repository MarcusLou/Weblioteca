package org.weblioteca.application.controllerTest;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.weblioteca.application.controller.EmprestimoFaturarController;
import org.weblioteca.application.model.Autor;
import org.weblioteca.application.model.Cliente;
import org.weblioteca.application.model.Editora;
import org.weblioteca.application.model.Emprestimo;
import org.weblioteca.application.model.Fatura;
import org.weblioteca.application.model.Livro;

import antlr.collections.List;

public class EmprestimoFaturaControllerTest {

	@Test
	public void testCriarFatura() {
		Cliente cliente1 = new Cliente();
		cliente1.setClienteId(Long.valueOf(69));
		cliente1.setNome("Pedro Amancio");
		cliente1.setCpf("056.546.556-55");
		cliente1.setLogradouro("Rua das Tangerinas");
		cliente1.setNumero("19B");
		cliente1.setTelefone("44-99988-5544");
		cliente1.setComplemento("Próximo ao parque do Jabuti");
		cliente1.setDataNascimento(java.sql.Date.valueOf("1989-11-01"));
		cliente1.setFoto("./img/fotos/126.jpg");
		cliente1.setSexo("Masculino");	
		
		Autor autor1 = new Autor();	
		autor1.setNome("Robert C Martin");			
		autor1.setOrigem("Paquistão");
		
		Editora editora1 = new Editora();
		editora1.setNome("Atica");
		editora1.setCnpj("22.123.554/0001-56");
		
		Livro livro1 = new Livro();
		livro1.setTituloLivro("Clean Code");
		livro1.setAutor(autor1);
		livro1.setDisponivelEmprestimo(true);
		livro1.setEditora(editora1);
		livro1.setLocalizacao("Maringá");
		livro1.setQuantidade(10);
		livro1.setDataDeCompra(java.sql.Date.valueOf("2020-01-01"));
		livro1.setEdicao("1ª Edição");

		Emprestimo emprestimo = new Emprestimo();
		emprestimo.setCliente(cliente1);
		emprestimo.setEmprestimoId(Long.valueOf(99));
		emprestimo.setDataDevolucao(java.sql.Date.valueOf("2021-04-18"));
		emprestimo.setDataDevolvido(java.sql.Date.valueOf("2021-04-19"));
		emprestimo.setDataEmprestimo(java.sql.Date.valueOf("2021-04-11"));
		emprestimo.setValorTotal(10);
		EmprestimoFaturarController controller = new EmprestimoFaturarController();
		
		Fatura fatura = new Fatura();
		fatura = controller.criarFatura(emprestimo);
		LocalDate date = LocalDate.now();
		Fatura faturaOutro = new Fatura();
		faturaOutro.setClienteId(Long.valueOf(69));
		faturaOutro.setDataFatura(date);
		faturaOutro.setDiasAtraso(1);
		faturaOutro.setValorFatura(10);
		faturaOutro.setIdEmprestimo(Long.valueOf(99));
		faturaOutro.setFaturaId(fatura.getFaturaId());
		assertEquals(fatura, faturaOutro);
		//assertEquals(fatura.equals(faturaOutro), true);
	}
}
