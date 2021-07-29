package com.zupedu.gabriel.mercadolivre.dtos;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.zupedu.gabriel.mercadolivre.entities.NovaCompra;
import com.zupedu.gabriel.mercadolivre.entities.enums.GatewayPagamento;

public class NovaCompraDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	@Positive(message = "a quantidade dev ser maior que 0!")
	private Integer quantidade;
	@NotNull(message = "Campo obrigatório")
	private Long produto;
	@NotNull(message = "Campo obrigatorio!")
	private GatewayPagamento gatewayPagamento;

	public NovaCompraDTO() {

	}

	public NovaCompraDTO(Long id, Integer quantidade, Long produto, GatewayPagamento gatewayPagamento) {
		super();
		this.id = id;
		this.quantidade = quantidade;
		this.produto = produto;
		this.gatewayPagamento = gatewayPagamento;
	}
	
	public NovaCompraDTO(NovaCompra entity) {
		super();
		id = entity.getId();
		quantidade = entity.getQuantidade();
		produto = entity.getProduto().getId();
		gatewayPagamento = entity.getGatewayPagamento();
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

	public Long getProduto() {
		return produto;
	}

	public void setProduto(Long produto) {
		this.produto = produto;
	}

	public GatewayPagamento getGatewayPagamento() {
		return gatewayPagamento;
	}

	public void setGatewayPagamento(GatewayPagamento gatewayPagamento) {
		this.gatewayPagamento = gatewayPagamento;
	}

}
