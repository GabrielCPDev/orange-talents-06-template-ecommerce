package com.zupedu.gabriel.mercadolivre.resources.components;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import com.zupedu.gabriel.mercadolivre.entities.NovaCompra;

@Service
public class RankingComponent implements EventoCompraSucesso{

	@Override
	public void processa(NovaCompra novaCompra) {
		Assert.isTrue(novaCompra.processadaComSucesso(), "Compra não processada com sucesso!" + novaCompra);
		RestTemplate restTemplate = new RestTemplate();
		Map<String, Object> request = Map.of("idCompra", novaCompra.getId(),"idVendedor",novaCompra.getProduto().getUsuario().getId());
		restTemplate.postForEntity("http://localhost:8080/ranking", request, String.class);
		
	}

}
