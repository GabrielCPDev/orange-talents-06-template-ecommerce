package com.zupedu.gabriel.mercadolivre.resources;

import java.net.URI;

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

import com.zupedu.gabriel.mercadolivre.dtos.AvaliacaoDTO;
import com.zupedu.gabriel.mercadolivre.dtos.ProdutoDTO;
import com.zupedu.gabriel.mercadolivre.entities.Avaliacao;
import com.zupedu.gabriel.mercadolivre.repositories.AvaliacaoRepository;
import com.zupedu.gabriel.mercadolivre.repositories.ProdutoRepository;
import com.zupedu.gabriel.mercadolivre.repositories.UsuarioRepository;

@RestController
@RequestMapping("/avaliacoes")
public class AvaliacaoResource {

	@Autowired
	private ProdutoRepository produtoRepository;	
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private AvaliacaoRepository avaliacaoRepository;

	@PostMapping
	public ResponseEntity<AvaliacaoDTO> save(@Valid @RequestBody AvaliacaoDTO dto) throws MethodArgumentNotValidException {		
		
		Avaliacao entity = new Avaliacao();
		toEntity(dto, entity);
		entity = avaliacaoRepository.save(entity);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.status(HttpStatus.OK).location(uri).build();

	}

	private void toEntity(AvaliacaoDTO dto, Avaliacao entity) {
		var usuarioToEntity = usuarioRepository.findById(dto.getUsuario().getId());
		var produtoToEntity = produtoRepository.findById(dto.getProduto().getId());
		
		entity.setId(dto.getId());
		entity.setTitulo(dto.getTitulo());
		entity.setDescricao(dto.getDescricao());
		entity.setNota(dto.getNota());
		entity.setUsuario(usuarioToEntity.get());
		entity.setProduto(produtoToEntity.get());
		
	}
}
