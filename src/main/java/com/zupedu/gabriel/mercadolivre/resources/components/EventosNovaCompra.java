package com.zupedu.gabriel.mercadolivre.resources.components;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zupedu.gabriel.mercadolivre.entities.NovaCompra;

@Service
public class EventosNovaCompra {

	@Autowired
	private Set<EventoCompraSucesso> eventosCompraSucesso;

	public void processa(NovaCompra compra) {
		if (compra.processadaComSucesso()) {
			eventosCompraSucesso.forEach(evento -> evento.processa(compra));
			System.out.println("Email: Sua Compra:" + compra + "foi processada com sucesso!");
		}

		System.out.println("Email: Desculpe, mas sua Compra:" + compra + "não foi processada com sucesso!");
	}

}
