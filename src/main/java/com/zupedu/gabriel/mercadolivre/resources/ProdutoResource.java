package com.zupedu.gabriel.mercadolivre.resources;

import java.net.URI;
import java.time.Instant;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.zupedu.gabriel.mercadolivre.dtos.ProdutoDTO;
import com.zupedu.gabriel.mercadolivre.entities.Categoria;
import com.zupedu.gabriel.mercadolivre.entities.Produto;
import com.zupedu.gabriel.mercadolivre.repositories.CategoriaRepository;
import com.zupedu.gabriel.mercadolivre.repositories.ProdutoRepository;

@RestController
@RequestMapping("/produtos")
public class ProdutoResource {

	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private CategoriaRepository categoriaRepository;

	@PostMapping
	public ResponseEntity<ProdutoDTO> save(@Valid @RequestBody ProdutoDTO dto) throws MethodArgumentNotValidException {
		Produto entity = new Produto();
		toEntity(dto, entity);
		entity = produtoRepository.save(entity);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.status(HttpStatus.OK).location(uri).build();

	}

	private void toEntity(ProdutoDTO dto, Produto entity) {
		var catToEntity = categoriaRepository.findById(dto.getCategoria());
		entity.setId(dto.getId());
		entity.setNome(dto.getNome());
		entity.setDescricao(dto.getDescricao());
		entity.setCategoria(catToEntity.get());
		
		entity.getCaracteristicas().clear();
		entity.setCaracteristicas(dto.getCaracteristicas());
		entity.setInstanteDoCadastro(Instant.now());
		entity.setQuantidade(dto.getQuantidade());
		entity.setValor(dto.getValor());
	}
}
