package com.zupedu.gabriel.mercadolivre.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.zupedu.gabriel.mercadolivre.entities.enums.StatusPagseguro;

@Entity
@Table(name = "tb_retorno_pagseguro")
public class RetornoPagseguro implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String idTransacao;
	@NotNull
	@Enumerated
	private StatusPagseguro status;

	public RetornoPagseguro() {
	}

	public RetornoPagseguro(Long id, String idTransacao, StatusPagseguro status) {
		super();
		this.id = id;
		this.idTransacao = idTransacao;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public StatusPagseguro getStatus() {
		return status;
	}

	public void setStatus(StatusPagseguro status) {
		this.status = status;
	}

	public String getIdTransacao() {
		return idTransacao;
	}

	public void setIdTransacao(String idTransacao) {
		this.idTransacao = idTransacao;
	}

}