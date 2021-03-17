package org.weblioteca.application.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Table(name = "FATURA")
public class Fatura {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long faturaId;
	private Date dataFatura;
	private float valorFatura;
	@OneToOne
	private Emprestimo emprestimo;
	private int diasAtraso;

	public Fatura() {
	}

	public Long getFaturaId() {
		return faturaId;
	}

	public void setFaturaId(Long faturaId) {
		this.faturaId = faturaId;
	}

	public Date getDataFatura() {
		return dataFatura;
	}

	public void setDataFatura(Date dataFatura) {
		this.dataFatura = dataFatura;
	}

	public Emprestimo getEmprestimo() {
		return emprestimo;
	}

	public void setEmprestimo(Emprestimo emprestimo) {
		this.emprestimo = emprestimo;
	}

	public float getValorFatura() {
		return valorFatura;
	}

	public void setValorFatura(float valorFatura) {
		this.valorFatura = valorFatura;
	}

	public int getDiasAtraso() {
		return diasAtraso;
	}

	public void setDiasAtraso(int diasAtraso) {
		this.diasAtraso = diasAtraso;
	}

}
