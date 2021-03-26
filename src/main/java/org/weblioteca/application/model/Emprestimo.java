	package org.weblioteca.application.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "EMPRESTIMO")
public class Emprestimo {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long emprestimoId;
	
	@OneToOne(cascade=CascadeType.PERSIST)
	private Cliente cliente;
	
	@OneToMany(cascade=CascadeType.PERSIST)
	private List<Livro> exemplar;
	
	private LocalDate dataEmprestimo;
	private LocalDate dataDevolucao;
	private LocalDate dataDevolvido;
	private double valorTotal;
	
	public Emprestimo() {}

	public Long getEmprestimoId() {
		return emprestimoId;
	}

	public void setEmprestimoId(Long emprestimoId) {
		this.emprestimoId = emprestimoId;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Livro> getExemplar() {
		return exemplar;
	}

	public void setExemplar(List<Livro> exemplar) {
		this.exemplar = exemplar;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public LocalDate getDataEmprestimo() {
		return dataEmprestimo;
	}

	public void setDataEmprestimo(LocalDate dataEmprestimo) {
		this.dataEmprestimo = dataEmprestimo;
	}

	public LocalDate getDataDevolucao() {
		return dataDevolucao;
	}

	public void setDataDevolucao(LocalDate dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}

	public LocalDate getDataDevolvido() {
		return dataDevolvido;
	}

	public void setDataDevolvido(LocalDate dataDevolvido) {
		this.dataDevolvido = dataDevolvido;
	}
	
}
