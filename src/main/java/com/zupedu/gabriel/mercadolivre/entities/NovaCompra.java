package com.zupedu.gabriel.mercadolivre.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.util.Assert;

import com.zupedu.gabriel.mercadolivre.dtos.RetornoPagseguroDTO;
import com.zupedu.gabriel.mercadolivre.entities.enums.GatewayPagamento;

@Entity
@Table(name = "tb_nova_compra")
public class NovaCompra implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Positive(message = "a quantidade dev ser maior que 0!")
	private Integer quantidade;
	@NotNull(message = "Campo obrigatório")
	@ManyToOne
	private Produto produto;
	@ManyToOne
	private Usuario comprador;
	@Enumerated
	@NotNull(message = "Campo obrigatorio!")
	private GatewayPagamento gatewayPagamento;
	@OneToMany(mappedBy = "compra", cascade = CascadeType.MERGE)
	private Set<Transacao> transacoes = new HashSet<>();

	public NovaCompra() {

	}

	public NovaCompra(Long id, Integer quantidade, Produto produto, Usuario comprador,
			GatewayPagamento gatewayPagamento) {
		super();
		this.id = id;
		this.quantidade = quantidade;
		this.produto = produto;
		this.comprador = comprador;
		this.gatewayPagamento = gatewayPagamento;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Usuario getComprador() {
		return comprador;
	}

	public void setComprador(Usuario comprador) {
		this.comprador = comprador;
	}

	public GatewayPagamento getGatewayPagamento() {
		return gatewayPagamento;
	}

	public void setGatewayPagamento(GatewayPagamento gatewayPagamento) {
		this.gatewayPagamento = gatewayPagamento;
	}

	public Set<Transacao> getTransacoes() {
		return transacoes;
	}

	public void setTransacoes(Set<Transacao> transacoes) {
		this.transacoes = transacoes;
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
		NovaCompra other = (NovaCompra) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public void adicionaTransacao(RetornoPagseguroDTO dto) {
		Transacao novaTransacao = dto.toTransacao(this);
		Assert.isTrue(!this.transacoes.contains(novaTransacao),
				"Uma transação igual a essa já está sendo processada: " + novaTransacao);

		Assert.isTrue(transacoesConcluidasComSucesso().isEmpty(), "Essa compra já foi concluida com sucesso!");
		Assert.isTrue(transacoesConcluidasComSucesso().size() <= 1, "Existe mais de uma transação dentro da compra");
		this.transacoes.add(novaTransacao);

	}

	private Set<Transacao> transacoesConcluidasComSucesso() {
		Set<Transacao> transacoesConcluidasComSucesso = this.transacoes.stream().filter(Transacao::concluidaComSucesso)
				.collect(Collectors.toSet());
		return transacoesConcluidasComSucesso;
	}

	@Override
	public String toString() {
		return "NovaCompra [id=" + id + ", quantidade=" + quantidade + ", produto=" + produto + ", comprador="
				+ comprador + ", gatewayPagamento=" + gatewayPagamento + ", transacoes=" + transacoes + "]";
	}

	public boolean processadaComSucesso() {
		return !transacoesConcluidasComSucesso().isEmpty();
	}

}
