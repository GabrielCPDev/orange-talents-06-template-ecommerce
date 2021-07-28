package com.zupedu.gabriel.mercadolivre.entities;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tb_perguntas")
public class Pergunta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank(message = "Campo obrigatório!")
	private String titulo;
	private Instant  criacao;
	@NotNull(message = "Campo obrigatório!")
	@ManyToOne
	private Usuario usuario;
	@NotNull(message = "Campo obrigatório!")
	@ManyToOne
	private Produto produto;
	
	public Pergunta () {
		
	}

	public Pergunta(Long id, String titulo, Instant criacao, Usuario usuario, Produto produto) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.criacao = criacao;
		this.usuario = usuario;
		this.produto = produto;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pergunta other = (Pergunta) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return " Usuario = " + produto.getUsuario().getNome() + ". Você tem uma nova pergunta sobre seu produto: " + " [id=" + id + ", titulo=" + titulo + ", criacao=" + criacao + ", usuario=" + usuario
				+ ", produto=" + produto.getId() + "de nome" + produto.getNome() +  "]";
	}
	
}
