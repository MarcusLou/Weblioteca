package org.weblioteca.application.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MULTA")
public class Multa {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long multaId;

	public Multa() {}

	public Long getMultaId() {
		return multaId;
	}

	public void setMultaId(Long multaId) {
		this.multaId = multaId;
	}

}
