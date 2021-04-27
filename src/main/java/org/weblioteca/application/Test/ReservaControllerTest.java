package org.weblioteca.application.Test;

import java.time.LocalDate;

import org.weblioteca.application.controller.ReservaController;
import org.weblioteca.application.model.Autor;
import org.weblioteca.application.model.Cliente;
import org.weblioteca.application.model.Editora;
import org.weblioteca.application.model.Livro;
import org.weblioteca.application.model.Reserva;

import junit.framework.*;

public class ReservaControllerTest extends TestCase {
	
	public void testeNovaReserva() {
		Cliente cliente = new Cliente();
		cliente.setNome("Pedro Amancio");	
		
		Autor autor = new Autor();
		autor.setNome("Robert C Martin");			
		autor.setNascimento(LocalDate.of(1950, 11, 10));
		autor.setOrigem("Paquistão");		
		
		Editora editora = new Editora();
		editora.setNome("Atica");
		
		Livro livro = new Livro();
		livro.setTituloLivro("Clean Code");
		livro.setAutor(autor);
		livro.setDisponivelEmprestimo(true);
		livro.setQuantidade(1);
		
		Reserva reserva = new Reserva();
		ReservaController reservaController = new ReservaController();

		Long idCliente = cliente.getClienteId();
		Long idLivro = livro.getLivroId();
		reserva.setClienteId(idCliente);		
		reserva.setLivroId(idLivro);		
		reserva.setDataReserva(LocalDate.of(2021, 03, 21));
		Long id = reserva.getReservaId();
		String resultado = reservaController.salvarReserva(reserva);
		String esperado = "redirect:/indexReserva";
		assertEquals (esperado, resultado);
	}
	
	
	
	
	

}
