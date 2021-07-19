package com.zupedu.gabriel.mercadolivre.dtos;

import java.io.Serializable;
import java.time.Instant;

import javax.validation.constraints.NotBlank;

import com.zupedu.gabriel.mercadolivre.entities.Usuario;

public class UsuarioDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	@NotBlank(message = "Campo Obrigatório")
	private String nome;
	@NotBlank(message = "Campo Obrigatório")
	private String email;
	private Instant dataCadastro;
	
	public UsuarioDTO () {
		
	}

	public UsuarioDTO(Long id, String nome, String email, Instant dataCadastro) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.dataCadastro = dataCadastro;
	}
	
	public UsuarioDTO (Usuario entity) {
		id = entity.getId();
		this.nome = entity.getNome();
		this.email = entity.getEmail();
		this.dataCadastro = entity.getDataCadastro();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Instant getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Instant dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
}
