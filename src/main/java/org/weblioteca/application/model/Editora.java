package org.weblioteca.application.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.validation.constraints.Min;

import com.sun.istack.NotNull;

@Entity
@Table(name = "EDITORA")
public class Editora {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long editoraId;
	
	private String nome;
	@NotNull
    @Size(min = 18, max = 18)
	@Min(18)
	private String cnpj;
	private Integer ativo = 1;

	public Editora() {
	}

	public Long getEditoraId() {
		return editoraId;
	}

	public void setEditoraId(Long editoraId) {
		this.editoraId = editoraId;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public Integer getAtivo() {
		return ativo;
	}

	public void setAtivo(Integer ativo) {
		this.ativo = ativo;
	}

}
