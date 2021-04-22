package org.weblioteca.application.builder;

import java.time.LocalDate;

import org.weblioteca.application.model.Fatura;

public class FaturaBuilder {
	private Fatura fatura;
	
    public FaturaBuilder() {
        this.fatura = new Fatura();
    }
    
    public static FaturaBuilder builder() {
        return new FaturaBuilder();
    }
    
    public FaturaBuilder addClienteId(Long clienteId) {
        this.fatura.setClienteId(clienteId);
        return this;
    }
    
    public FaturaBuilder addDataFatura(LocalDate dataFatura) {
        this.fatura.setDataFatura(dataFatura);
        return this;
    }
    
    public FaturaBuilder addDiasAtraso(int dias) {
        this.fatura.setDiasAtraso(dias);
        return this;
    }
    
    public FaturaBuilder addValorFatura(double valorFatura) {
        this.fatura.setValorFatura(valorFatura);
        return this;
    }
    
    public FaturaBuilder addIdEmprestimo(Long idEmprestimo) {
        this.fatura.setIdEmprestimo(idEmprestimo);
        return this;
    }
    
    public Fatura get() {
      //  this.fatura.setEndereco(this.endereco);
        return this.fatura;
    }
}
