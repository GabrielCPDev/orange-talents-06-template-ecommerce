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

import com.zupedu.gabriel.mercadolivre.dtos.PerguntaDTO;
import com.zupedu.gabriel.mercadolivre.dtos.ProdutoDTO;
import com.zupedu.gabriel.mercadolivre.entities.Pergunta;
import com.zupedu.gabriel.mercadolivre.entities.Produto;
import com.zupedu.gabriel.mercadolivre.repositories.AvaliacaoRepository;
import com.zupedu.gabriel.mercadolivre.repositories.CategoriaRepository;
import com.zupedu.gabriel.mercadolivre.repositories.PerguntaRepository;
import com.zupedu.gabriel.mercadolivre.repositories.ProdutoRepository;
import com.zupedu.gabriel.mercadolivre.repositories.UsuarioRepository;

@RestController
@RequestMapping("/perguntas")
public class PerguntaResource {

	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private PerguntaRepository perguntaRepository;

	@PostMapping
	public ResponseEntity<PerguntaDTO> save(@Valid @RequestBody PerguntaDTO dto) throws MethodArgumentNotValidException {
		dto.setCriacao(Instant.now());
		
		Pergunta entity = new Pergunta();
		toEntity(dto, entity);
		entity = perguntaRepository.save(entity);
		System.out.println(entity.toString());
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.status(HttpStatus.OK).location(uri).build();

	}

	private void toEntity(PerguntaDTO dto, Pergunta entity) {
		var usuarioToEntity = usuarioRepository.findById(dto.getUsuario());
		var produtoToEntity = produtoRepository.findById(dto.getProduto());
		
		entity.setId(dto.getId());
		entity.setCriacao(dto.getCriacao());
		entity.setTitulo(dto.getTitulo());
		entity.setUsuario(usuarioToEntity.get());
		entity.setProduto(produtoToEntity.get());		
	}
}
