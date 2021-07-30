package com.zupedu.gabriel.mercadolivre.resources;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.zupedu.gabriel.mercadolivre.entities.NovaCompraNF;
import com.zupedu.gabriel.mercadolivre.entities.Ranking;

@RestController
public class OutrosSistemasResource {

	@PostMapping(value = "/notas-fiscais")
	public void criaNota (@Valid @RequestBody NovaCompraNF obj) throws InterruptedException{
		System.out.println("Criando nota para: " + obj);
		Thread.sleep(150);
	}
	
	@PostMapping(value = "/ranking")
	public void ranking (@Valid @RequestBody Ranking obj) throws InterruptedException{
		System.out.println("Criando nota para: " + obj.getIdCompra() + " do dono: " + obj.getIdDonoDoProduto());
		Thread.sleep(150);
	}
}
