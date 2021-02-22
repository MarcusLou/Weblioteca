package org.weblioteca.application.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "RELATORIO")
public class Relatorio {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long relatorioId;

	public Relatorio() {
	}

	public Long getRelatorioId() {
		return relatorioId;
	}

	public void setRelatorioId(Long relatorioId) {
		this.relatorioId = relatorioId;
	}

}
