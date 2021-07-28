package com.zupedu.gabriel.mercadolivre.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tb_produtos")
public class Produto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	@Column(unique = true)
	private String nome;
	@Size(max =1000, message = "O número máximo de caracteres é 1000!")
	private String descricao;
	@Positive(message = "O valor não pode ser menor ou igual a zero!")
	private Double valor;
	@Size(min = 3, message = " O produto deve conter ao menos 3 características")
	@ElementCollection
	@CollectionTable(name = "tb_caracteristicas")
	private List<String> caracteristicas = new ArrayList<String>();
	@ElementCollection
	@CollectionTable(name = "tb_imagens")
	private List<String> imagens = new ArrayList<String>();
	@PositiveOrZero(message = "O valor não pode ser menor que 0!")
	private Integer quantidade;
	private Instant instanteDoCadastro;
	@NotNull
	@ManyToOne
	private Categoria categoria;
	@ManyToOne
	private Usuario usuario;
	@OneToOne
	private Avaliacao avaliacao;
	
	public Produto() {
		
	}
	
	public Produto(Long id, String nome, String descricao, Double valor, Integer quantidade, Instant instanteDoCadastro, Usuario usuario, Categoria categoria, Avaliacao avaliacao) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.valor = valor;
		this.quantidade = quantidade;
		this.instanteDoCadastro = instanteDoCadastro;
		this.categoria = categoria;
		this.usuario = usuario;
		this.avaliacao = avaliacao;
	}

	public Produto(Long id, String nome, String descricao, Double valor, List<String> caracteristicas,List<String> imagens, Integer quantidade, Instant instanteDoCadastro, Categoria categoria, Avaliacao avaliacao) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.valor = valor;
		this.caracteristicas = caracteristicas;
		this.imagens = imagens;
		this.quantidade = quantidade;
		this.instanteDoCadastro = instanteDoCadastro;
		this.categoria = categoria;
		this.avaliacao = avaliacao;
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public List<String> getCaracteristicas() {
		return caracteristicas;
	}

	public void setCaracteristicas(List<String> caracteristicas) {
		this.caracteristicas = caracteristicas;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Instant getInstanteDoCadastro() {
		return instanteDoCadastro;
	}

	public void setInstanteDoCadastro(Instant instanteDoCadastro) {
		this.instanteDoCadastro = instanteDoCadastro;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<String> getImagens() {
		return imagens;
	}

	public void setImagens(List<String> imagens) {
		this.imagens = imagens;
	}

	public Avaliacao getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(Avaliacao avaliacao) {
		this.avaliacao = avaliacao;
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
		Produto other = (Produto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	

	
}
