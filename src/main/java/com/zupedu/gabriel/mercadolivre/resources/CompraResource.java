package com.zupedu.gabriel.mercadolivre.resources;

import java.net.URI;
import java.net.URISyntaxException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.zupedu.gabriel.mercadolivre.dtos.NovaCompraDTO;
import com.zupedu.gabriel.mercadolivre.entities.NovaCompra;
import com.zupedu.gabriel.mercadolivre.entities.enums.GatewayPagamento;
import com.zupedu.gabriel.mercadolivre.repositories.NovaCompraRepository;
import com.zupedu.gabriel.mercadolivre.repositories.ProdutoRepository;
import com.zupedu.gabriel.mercadolivre.repositories.UsuarioRepository;
import com.zupedu.gabriel.mercadolivre.resources.exceptions.InvalidArgumentRequestException;
import com.zupedu.gabriel.mercadolivre.resources.utils.UsuarioUtil;

@RestController
@RequestMapping("/comprar")
public class CompraResource {

	@Autowired
	private NovaCompraRepository compraRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private UsuarioRepository usuarioRepository;

	private static final UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.newInstance();

	@PostMapping
	public ResponseEntity<NovaCompraDTO> comprar(@RequestBody @Valid NovaCompraDTO dto)
			throws InvalidArgumentRequestException, URISyntaxException {
		abateDoEstoque(dto.getProduto(), dto.getQuantidade());
		var entity = new NovaCompra();
		toEntity(dto, entity);

		var usuarioLogado = UsuarioUtil.authenticated();
		var comprador = usuarioRepository.findByEmail(usuarioLogado.getEmail());
		entity.setComprador(comprador);
		var novaCompra = compraRepository.save(entity);
		var uri = uriGenerator(entity, uriComponentsBuilder);
		var finalUri = new URI(uri);

		return ResponseEntity.status(HttpStatus.OK).location(finalUri).build();

	}

	protected String uriGenerator(NovaCompra novaCompra, UriComponentsBuilder uriComponentsBuilder) {

		if (novaCompra.getGatewayPagamento().equals(GatewayPagamento.PAYPALL)) {
			var urlRetornoPaypall = uriComponentsBuilder.path("/retorno-paypall/{id}").buildAndExpand(novaCompra.getId().toString());
			return "paypall.com/" + novaCompra.getId() + "?redirectUrl=" + urlRetornoPaypall;
		}

		var urlRetornoPagueseguro = uriComponentsBuilder.path("/retorno-pagueseguro/{id}").buildAndExpand(novaCompra.getId().toString());
		return "pagueseguro.com/" + novaCompra.getId() + "?redirectUrl=" + urlRetornoPagueseguro;

	}

	public void abateDoEstoque(Long idProduto, Integer quantidade) {
		var produto = produtoRepository.getOne(idProduto);

		var qtdAnterior = produto.getQuantidade();
		if (qtdAnterior > quantidade) {
			var qtdAtual = qtdAnterior -= quantidade;
			produto.setQuantidade(qtdAtual);
			produtoRepository.save(produto);

		} else {
			throw new InvalidArgumentRequestException("Compra não efetuada. Quantidade indisponível em estoque!");
		}

	}

	private void toEntity(NovaCompraDTO dto, NovaCompra entity) {

		var produtoToEntity = produtoRepository.findById(dto.getProduto());
		entity.setId(dto.getId());
		entity.setProduto(produtoToEntity.get());
		entity.setQuantidade(dto.getQuantidade());
		entity.setGatewayPagamento(dto.getGatewayPagamento());
	}

}
