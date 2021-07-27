package com.zupedu.gabriel.mercadolivre.resources;

import java.net.URI;
import java.time.Instant;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
public class UsuarioResource implements UserDetailsService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private BCryptPasswordEncoder senhaEncoder;
	
	@PostMapping
	public ResponseEntity<UsuarioDTO> save (@Valid @RequestBody UsuarioNewDTO dto) throws MethodArgumentNotValidException{
		Usuario entity = toEntity(dto); 
		entity.setSenha(senhaEncoder.encode(dto.getSenha()));
		entity.setDataCadastro(Instant.now());
		entity = usuarioRepository.save(entity);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).build();
		
	}
	
	protected Usuario toEntity(UsuarioDTO dto) {
		var obj = new Usuario();
		obj.setNome(dto.getNome());
		obj.setEmail(dto.getEmail());
		obj.setDataCadastro(dto.getDataCadastro());
		return obj;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		var user = usuarioRepository.findByEmail(username);
		if (user == null) {
			throw new UsernameNotFoundException("Usuario não encontrado!");
		}
		return user;
	}
}
