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

import com.zupedu.gabriel.mercadolivre.dtos.CategoriaDTO;
import com.zupedu.gabriel.mercadolivre.entities.Categoria;
import com.zupedu.gabriel.mercadolivre.repositories.CategoriaRepository;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@PostMapping
	public ResponseEntity<CategoriaDTO> save(@Valid @RequestBody CategoriaDTO dto) throws MethodArgumentNotValidException {
		Categoria entity = toEntity(dto);
		entity = categoriaRepository.save(entity);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.status(HttpStatus.OK).location(uri).build();

	}

	protected Categoria toEntity(CategoriaDTO dto) {
		var obj = new Categoria();
		var entity = new Categoria(dto.getId(), dto.getNome());

		if(dto.getCategoriaMae() != null) {
			obj = categoriaRepository.getOne(dto.getCategoriaMae());
			entity.setCategoriaMae(obj);
		}
		return entity;
	}
}
