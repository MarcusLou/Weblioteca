package org.weblioteca.application.model;


import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "AUTOR")
public class Autor {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long autorId;
	
	private String nome;
	private String origem;
	private Date nascimento;
	

	public Autor() {
	}

	public Long getAutorId() {
		return autorId;
	}

	public void setAutorId(Long autorId) {
		this.autorId = autorId;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getOrigem() {
		return origem;
	}

	public void setOrigem(String origem) {
		this.origem = origem;
	}
	
	public Date getNascimento() {
		return nascimento;
	}

	public void setNascimento(Date nascimento) {
		this.nascimento = nascimento;
	}

}
