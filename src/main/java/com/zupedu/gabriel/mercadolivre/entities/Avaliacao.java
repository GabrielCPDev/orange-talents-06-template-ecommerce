package com.zupedu.gabriel.mercadolivre.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tb_avaliacoes")
public class Avaliacao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	private Usuario usuario;
	@NotNull(message = "Campo obrigatório!")
	@OneToOne
	private Produto produto;

	public Avaliacao() {

	}

	public Avaliacao(Long id, Integer nota, String titulo, String descricao, Usuario usuario, Produto produto) {
		super();
		this.id = id;
		this.nota = nota;
		this.titulo = titulo;
		this.descricao = descricao;
		this.usuario = usuario;
		this.produto = produto;
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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Avaliacao(Long id) {
		super();
		this.id = id;
	}

}
