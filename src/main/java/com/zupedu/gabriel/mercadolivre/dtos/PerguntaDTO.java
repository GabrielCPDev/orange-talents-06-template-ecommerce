package com.zupedu.gabriel.mercadolivre.dtos;

import java.io.Serializable;
import java.time.Instant;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.zupedu.gabriel.mercadolivre.entities.Pergunta;

public class PerguntaDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	@NotBlank(message = "Campo obrigatório!")
	private String titulo;
	private Instant criacao;
	@NotNull(message = "Campo obrigatório!")
	private Long usuario;
	@NotNull(message = "Campo obrigatório!")
	private Long produto;

	public PerguntaDTO() {

	}

	public PerguntaDTO(Long id, String titulo, Instant criacao, Long usuario, Long produto) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.criacao = criacao;
		this.usuario = usuario;
		this.produto = produto;
	}
	
	public PerguntaDTO (Pergunta entity) {
		id = entity.getId();
		titulo = entity.getTitulo();
		criacao = entity.getCriacao();
		usuario = entity.getUsuario().getId();
		produto = entity.getProduto().getId();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Instant getCriacao() {
		return criacao;
	}

	public void setCriacao(Instant criacao) {
		this.criacao = criacao;
	}

	public Long getUsuario() {
		return usuario;
	}

	public void setUsuario(Long usuario) {
		this.usuario = usuario;
	}

	public Long getProduto() {
		return produto;
	}

	public void setProduto(Long produto) {
		this.produto = produto;
	}
	

}
