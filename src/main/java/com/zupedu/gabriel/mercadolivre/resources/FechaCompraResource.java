package com.zupedu.gabriel.mercadolivre.resources;

import java.util.Iterator;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zupedu.gabriel.mercadolivre.dtos.RetornoPagseguroDTO;
import com.zupedu.gabriel.mercadolivre.entities.NovaCompra;
import com.zupedu.gabriel.mercadolivre.entities.RetornoPagseguro;
import com.zupedu.gabriel.mercadolivre.entities.Transacao;
import com.zupedu.gabriel.mercadolivre.repositories.NovaCompraRepository;
import com.zupedu.gabriel.mercadolivre.repositories.RetornoPagseguroRepository;
import com.zupedu.gabriel.mercadolivre.repositories.TransacaoRepository;
import com.zupedu.gabriel.mercadolivre.resources.components.EventoCompraSucesso;
import com.zupedu.gabriel.mercadolivre.resources.components.EventosNovaCompra;
import com.zupedu.gabriel.mercadolivre.resources.components.NotaFiscalComponent;
import com.zupedu.gabriel.mercadolivre.resources.components.RankingComponent;

@RestController
@RequestMapping("/retorno-pagseguro")
public class FechaCompraResource {

	@Autowired
	private RetornoPagseguroRepository pagseguroRepository;
	@Autowired
	private NovaCompraRepository compraRepository;
	@Autowired
	private TransacaoRepository transacaoRepository;
	@Autowired
	private EventosNovaCompra eventosNovaCompra;

	@PostMapping(value = "/{id}")
	public ResponseEntity<RetornoPagseguroDTO> processamentoPagseguro(@PathVariable("id") Long idCompra,
			@RequestBody @Valid RetornoPagseguroDTO dto) {
		var entity = new RetornoPagseguro();
		var compra = compraRepository.findById(idCompra);
		compra.get().adicionaTransacao(dto);
		toEntity(dto, entity);
		pagseguroRepository.save(entity);
		eventosNovaCompra.processa(compra.get());
		gerenciaTransacoes(compra);
		return ResponseEntity.ok().build();
	}

	private void gerenciaTransacoes(Optional<NovaCompra> compra) {
		Iterator<Transacao> transacoes = compra.get().getTransacoes().iterator();
		while (transacoes.hasNext()) {
			Transacao t = transacoes.next();
			transacaoRepository.save(t);
		}
	}

	private void toEntity(RetornoPagseguroDTO dto, RetornoPagseguro entity) {
		entity.setIdTransacao(dto.getIdTransacao());
		entity.setStatus(dto.getStatus());
	}

}
