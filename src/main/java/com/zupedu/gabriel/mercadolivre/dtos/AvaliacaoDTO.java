package com.zupedu.gabriel.mercadolivre.dtos;

import java.io.Serializable;

import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.zupedu.gabriel.mercadolivre.entities.Avaliacao;

public class AvaliacaoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	@Min(value = 1, message = "O valor mínimo da nota é 1")
	@Max(value = 5, message = "O valor máximo da nota é 5")
	private Integer nota;
	@NotBlank(message = "Campo obrigatório!")
	private String titulo;
	@NotBlank(message = "Campo obrigatório!")
	@Size(max = 500, message = "O número máximo de caractéres é 500!")
	private String descricao;
	@NotNull(message = "Campo obrigatório!")
	@ManyToOne
	private UsuarioDTO usuario;
	@NotNull(message = "Campo obrigatório!")
	private ProdutoDTO produto;

	public AvaliacaoDTO() {

	}

	public AvaliacaoDTO(Long id, Integer nota, String titulo, String descricao, UsuarioDTO usuario,
			ProdutoDTO produto) {
		this.id = id;
		this.nota = nota;
		this.titulo = titulo;
		this.descricao = descricao;
		this.usuario = usuario;
		this.produto = produto;
	}
	
	public AvaliacaoDTO (Avaliacao entity) {
		id = entity.getId();
		nota = entity.getNota();
		titulo = entity.getTitulo();
		descricao = entity.getDescricao();
		usuario = new UsuarioDTO(entity.getUsuario());
		produto = new ProdutoDTO(entity.getProduto());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getNota() {
		return nota;
	}

	public void setNota(Integer nota) {
		this.nota = nota;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public UsuarioDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}

	public ProdutoDTO getProduto() {
		return produto;
	}

	public void setProduto(ProdutoDTO produto) {
		this.produto = produto;
	}

}
