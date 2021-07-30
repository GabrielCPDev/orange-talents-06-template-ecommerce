package com.zupedu.gabriel.mercadolivre.resources.components;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import com.zupedu.gabriel.mercadolivre.entities.NovaCompra;

@Service
public class NotaFiscalComponent implements EventoCompraSucesso {

	@Override
	public void processa(NovaCompra novaCompra) {
		Assert.isTrue(novaCompra.processadaComSucesso(), "Compra não concluída com sucesso!" + novaCompra);
		RestTemplate restTemplate = new RestTemplate();
		Map<String, Object> request = Map.of("idCompra", novaCompra.getId(),"idComprador",novaCompra.getComprador().getId());
		restTemplate.postForEntity("http://localhost:8080/notas-fiscais", request, String.class);
		
	}

}
