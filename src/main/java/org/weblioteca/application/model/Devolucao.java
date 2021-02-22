package org.weblioteca.application.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "DEVOLUCAO")
public class Devolucao {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long devolucaoId;

	public Devolucao() {
	}

	public Long getDevolucaoId() {
		return devolucaoId;
	}

	public void setDevolucaoId(Long devolucaoId) {
		this.devolucaoId = devolucaoId;
	}

}
