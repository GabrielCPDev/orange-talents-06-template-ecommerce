package com.zupedu.gabriel.mercadolivre.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.zupedu.gabriel.mercadolivre.entities.enums.StatusTransacao;

@Entity
@Table(name = "tb_transacoes")
public class Transacao implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	private StatusTransacao status;
	@NotNull
	private String idTransacaoGateway;
	private LocalDateTime instante;
	@NotNull
	@Valid
	@ManyToOne
	private NovaCompra compra;

	public Transacao() {

	}

	public Transacao(StatusTransacao status, String idTransacaoGateway, NovaCompra compra) {
		this.status = status;
		this.idTransacaoGateway = idTransacaoGateway;
		this.instante = LocalDateTime.now();
		this.compra = compra;
	}
	
	public boolean concluidaComSucesso () {
		return this.status.equals(StatusTransacao.SUCESSO);
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
		Transacao other = (Transacao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Transacao [id=" + id + ", status=" + status + ", idTransacaoGateway=" + idTransacaoGateway
				+ ", instante=" + instante + ", compra=" + compra + "]";
	}
}
