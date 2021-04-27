package org.weblioteca.application.builder;

import java.time.LocalDate;

import org.weblioteca.application.model.Cliente;
import org.weblioteca.application.model.Livro;
import org.weblioteca.application.model.Reserva;


public class ReservaBuilder {
	private Reserva reserva;
	
    public ReservaBuilder() {
        this.reserva = new Reserva();
    }
    
    public static ReservaBuilder builder() {
        return new ReservaBuilder();
    }
    
    public ReservaBuilder ClienteId(Long clienteId) {
        this.reserva.setClienteId(clienteId);
        return this;
    }
    
    public ReservaBuilder LivroId(Long livroId) {
        this.reserva.setLivroId(livroId);
        return this;
    }
    
    public ReservaBuilder DataReserva(LocalDate dataReserva) {
        this.reserva.setDataReserva(dataReserva);
        return this;
    }
    
    public ReservaBuilder Ativo(Integer ativo) {
        this.reserva.setAtivo(ativo);
        return this;
    }    
    
    public Reserva get() {
        return this.reserva;
    }
    
    public Reserva criarReserva(Cliente cliente, Livro livro) {    	
    	Reserva reserva = ReservaBuilder.builder()
    			.ClienteId(cliente.getClienteId())
    			.LivroId(livro.getLivroId())
    			.DataReserva(LocalDate.now())
    			.Ativo(1)
    			.get();    	
    	return reserva;
    }

}
