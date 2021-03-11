package org.weblioteca.application.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "EMPRESTIMO")
public class Emprestimo {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long emprestimoId;
    
	public Emprestimo() {
	}

	public Long getEmprestimoId() {
		return emprestimoId;
	}

	public void setEmprestimoId(Long emprestimoId) {
		this.emprestimoId = emprestimoId;
	}

}
