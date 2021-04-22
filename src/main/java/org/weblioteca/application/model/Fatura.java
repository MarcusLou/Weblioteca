package org.weblioteca.application.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.time.LocalDate;

@Entity
@Table(name = "FATURA")
public class Fatura {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long faturaId;
	
	
	private Long clienteId;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataFatura;
	private double valorFatura;
	private Long idEmprestimo;
	
	public Long getIdEmprestimo() {
		return idEmprestimo;
	}

	public void setIdEmprestimo(Long idEmprestimo) {
		this.idEmprestimo = idEmprestimo;
	}

	private int diasAtraso;

	public Fatura() {
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((clienteId == null) ? 0 : clienteId.hashCode());
		result = prime * result + ((dataFatura == null) ? 0 : dataFatura.hashCode());
		result = prime * result + diasAtraso;
		result = prime * result + ((faturaId == null) ? 0 : faturaId.hashCode());
		result = prime * result + ((idEmprestimo == null) ? 0 : idEmprestimo.hashCode());
		long temp;
		temp = Double.doubleToLongBits(valorFatura);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Fatura other = (Fatura) obj;
		if (clienteId == null) {
			if (other.clienteId != null)
				return false;
		} else if (!clienteId.equals(other.clienteId))
			return false;
		if (dataFatura == null) {
			if (other.dataFatura != null)
				return false;
		} else if (!dataFatura.equals(other.dataFatura))
			return false;
		if (diasAtraso != other.diasAtraso)
			return false;
		if (faturaId == null) {
			if (other.faturaId != null)
				return false;
		} else if (!faturaId.equals(other.faturaId))
			return false;
		if (idEmprestimo == null) {
			if (other.idEmprestimo != null)
				return false;
		} else if (!idEmprestimo.equals(other.idEmprestimo))
			return false;
		if (Double.doubleToLongBits(valorFatura) != Double.doubleToLongBits(other.valorFatura))
			return false;
		return true;
	}

	public Long getFaturaId() {
		return faturaId;
	}

	public void setFaturaId(Long faturaId) {
		this.faturaId = faturaId;
	}

	public LocalDate getDataFatura() {
		return dataFatura;
	}

	public void setDataFatura(LocalDate dataFatura) {
		this.dataFatura = dataFatura;
	}

	public double getValorFatura() {
		return valorFatura;
	}

	public void setValorFatura(double valorFatura) {
		this.valorFatura = valorFatura;
	}

	public int getDiasAtraso() {
		return diasAtraso;
	}

	public void setDiasAtraso(int diasAtraso) {
		this.diasAtraso = diasAtraso;
	}

	public Long getClienteId() {
		return clienteId;
	}

	public void setClienteId(Long clienteId) {
		this.clienteId = clienteId;
	}

}
