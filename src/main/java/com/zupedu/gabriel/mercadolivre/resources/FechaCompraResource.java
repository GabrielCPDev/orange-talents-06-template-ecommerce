package com.zupedu.gabriel.mercadolivre.resources;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zupedu.gabriel.mercadolivre.dtos.RetornoPagseguroDTO;
import com.zupedu.gabriel.mercadolivre.entities.RetornoPagseguro;
import com.zupedu.gabriel.mercadolivre.repositories.NovaCompraRepository;
import com.zupedu.gabriel.mercadolivre.repositories.RetornoPagseguroRepository;

@RestController
@RequestMapping("/retorno-pagseguro")
public class FechaCompraResource {

	@Autowired
	private RetornoPagseguroRepository pagseguroRepository;
	@Autowired
	private NovaCompraRepository compraRepository;

	@PostMapping(value = "/{id}")
	public ResponseEntity<RetornoPagseguroDTO> processamentoPagseguro(@PathVariable("id") Long idCompra,
			@RequestBody @Valid RetornoPagseguroDTO dto) {
		var entity = new RetornoPagseguro();
		var compra = compraRepository.getOne(idCompra);
		compra.adicionaTransacao(dto);
		toEntity(dto, entity);
		pagseguroRepository.save(entity);

		return ResponseEntity.ok().build();
	}

	private void toEntity(RetornoPagseguroDTO dto, RetornoPagseguro entity) {
		entity.setIdTransacao(dto.getIdTransacao());
		entity.setStatus(dto.getStatus());
	}

}
