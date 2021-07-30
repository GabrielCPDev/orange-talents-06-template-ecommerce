package com.zupedu.gabriel.mercadolivre.dtos;

import com.zupedu.gabriel.mercadolivre.entities.NovaCompra;
import com.zupedu.gabriel.mercadolivre.entities.RetornoPagseguro;
import com.zupedu.gabriel.mercadolivre.entities.Transacao;
import com.zupedu.gabriel.mercadolivre.entities.enums.StatusPagseguro;

public class RetornoPagseguroDTO {
	
	private String idTransacao;
	private StatusPagseguro status;
	
	public RetornoPagseguroDTO(){		
	}
	
	
	public RetornoPagseguroDTO(String idTransacao, StatusPagseguro status) {
		super();
		this.idTransacao = idTransacao;
		this.status = status;
	}


	public RetornoPagseguroDTO(RetornoPagseguro entity) {
		idTransacao = entity.getIdTransacao();
		status = entity.getStatus();
	}
	

	public String getIdTransacao() {
		return idTransacao;
	}

	public void setIdTransacao(String idTransacao) {
		this.idTransacao = idTransacao;
	}

	public StatusPagseguro getStatus() {
		return status;
	}

	public void setStatus(StatusPagseguro status) {
		this.status = status;
	}


	public Transacao toTransacao(NovaCompra compra) {
		return new Transacao(status.normaliza(), idTransacao, compra);
	}

}
