package com.zupedu.gabriel.mercadolivre.dtos;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import com.zupedu.gabriel.mercadolivre.entities.Produto;
import com.zupedu.gabriel.mercadolivre.resources.validations.ProdutoInsert;

@ProdutoInsert
public class ProdutoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	@Column(unique = true)
	private String nome;
	@Size(max = 1000, message = "O número máximo de caracteres é 1000!")
	private String descricao;
	@Positive(message = "O valor não pode ser menor ou igual a zero!")
	private Double valor;
	@Size(min = 3, message = " O produto deve conter ao menos 3 características")
	private List<String> caracteristicas = new ArrayList<>();
	@Size(min = 1, message = " O produto deve conter ao menos 1 imagem")
	private List<String> imagens = new ArrayList<>();
	@PositiveOrZero(message = "O valor não pode ser menor que 0!")
	private Integer quantidade;
	private Instant instanteDoCadastro;
	@NotNull
	private Long categoria;
	private Long usuario;
	private Long avaliacao;

	public ProdutoDTO() {

	}

	public ProdutoDTO(Long id, String nome, String descricao, Double valor, List<String> caracteristicas,
			List<String> imagens, Integer quantidade, Instant instanteDoCadastro, Long categoria, Long usuario, Long avaliacao) {
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
		this.usuario = usuario;
		this.avaliacao = avaliacao;
	}

	public ProdutoDTO(Long id, String nome, String descricao, Double valor, Integer quantidade,
			Instant instanteDoCadastro, Long categoria, Long usuario, Long avaliacao) {
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

	public ProdutoDTO(Produto entity) {
		id = entity.getId();
		nome = entity.getNome();
		descricao = entity.getDescricao();
		valor = entity.getValor();
		caracteristicas = entity.getCaracteristicas();
		quantidade = entity.getQuantidade();
		instanteDoCadastro = entity.getInstanteDoCadastro();
		categoria = entity.getCategoria().getId();
		usuario = entity.getUsuario().getId();
		avaliacao = entity.getAvaliacao().getId();
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

	public Long getCategoria() {
		return categoria;
	}

	public void setCategoria(Long categoria) {
		this.categoria = categoria;
	}

	public Long getUsuario() {
		return usuario;
	}

	public void setUsuario(Long usuario) {
		this.usuario = usuario;
	}

	public List<String> getImagens() {
		return imagens;
	}

	public void setImagens(List<String> imagens) {
		this.imagens = imagens;
	}

	public Long getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(Long avaliacao) {
		this.avaliacao = avaliacao;
	}

}
