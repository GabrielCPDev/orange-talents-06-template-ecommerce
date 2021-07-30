package com.zupedu.gabriel.mercadolivre.entities.enums;

public enum StatusPagseguro {

	SUCESSO, ERRO;
	
	public StatusTransacao normaliza() {
		if (this.equals(SUCESSO)) {
			return StatusTransacao.SUCESSO;
		}
		return StatusTransacao.ERRO;
	}
}
