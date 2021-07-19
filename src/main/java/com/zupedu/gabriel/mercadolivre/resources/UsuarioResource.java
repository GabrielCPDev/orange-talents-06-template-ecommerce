package com.zupedu.gabriel.mercadolivre.resources;

import java.net.URI;
import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.zupedu.gabriel.mercadolivre.dtos.UsuarioDTO;
import com.zupedu.gabriel.mercadolivre.dtos.UsuarioNewDTO;
import com.zupedu.gabriel.mercadolivre.entities.Usuario;
import com.zupedu.gabriel.mercadolivre.repositories.UsuarioRepository;

@RestController
@RequestMapping("/usuarios")
public class UsuarioResource {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private BCryptPasswordEncoder senhaEncoder;
	
	@PostMapping
	public ResponseEntity<UsuarioDTO> save (@RequestBody UsuarioNewDTO dto){
		Usuario entity = toEntity(dto);
		entity.setSenha(senhaEncoder.encode(dto.getSenha()));
		entity.setDataCadastro(Instant.now());
		entity = usuarioRepository.save(entity);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
		
	}
	
	protected Usuario toEntity(UsuarioDTO dto) {
		var obj = new Usuario();
		obj.setNome(dto.getNome());
		obj.setEmail(dto.getEmail());
		obj.setDataCadastro(dto.getDataCadastro());
		return obj;
	}
}
