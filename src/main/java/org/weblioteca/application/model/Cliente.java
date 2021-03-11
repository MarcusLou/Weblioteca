package org.weblioteca.application.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "CLIENTE")
public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long clienteId;
	@NotBlank(message = "Nome deve ser preenchido.")
	private String nome;
	@NotBlank(message = "Sexo deve ser preenchido.")
	private String sexo;
	@Size(min = 11, max = 11, message = "Cpf deve ser preenchido.")
	private String cpf;
	@Lob
	@Column(columnDefinition = "MEDIUMBLOB")
	private String foto;
	private LocalDate dataNascimento;
	
	public Cliente() {}
	
	public Long getClienteId() {
		return clienteId;
	}
	
	public void setClienteId(Long clienteId) {
		this.clienteId = clienteId;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

}
