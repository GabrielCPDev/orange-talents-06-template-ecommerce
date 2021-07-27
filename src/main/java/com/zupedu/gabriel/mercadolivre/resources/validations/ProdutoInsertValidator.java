package com.zupedu.gabriel.mercadolivre.resources.validations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.zupedu.gabriel.mercadolivre.dtos.CategoriaDTO;
import com.zupedu.gabriel.mercadolivre.dtos.ProdutoDTO;
import com.zupedu.gabriel.mercadolivre.entities.Categoria;
import com.zupedu.gabriel.mercadolivre.repositories.CategoriaRepository;
import com.zupedu.gabriel.mercadolivre.repositories.ProdutoRepository;
import com.zupedu.gabriel.mercadolivre.repositories.UsuarioRepository;
import com.zupedu.gabriel.mercadolivre.resources.exceptions.FieldMessage;
import com.zupedu.gabriel.mercadolivre.resources.utils.UsuarioUtil;

public class ProdutoInsertValidator implements ConstraintValidator<ProdutoInsert, ProdutoDTO> {

	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private ProdutoRepository produRepository;
	@Autowired
	private CategoriaRepository categoriaRepository;

	public void initialize(UsuarioInsert ui) {
	}

	@Override
	public boolean isValid(ProdutoDTO dto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		
		var usuario = usuarioRepository.findByEmail(UsuarioUtil.authenticated().getEmail());
		var produto = produRepository.findByNome(dto.getNome());
		var categoria = categoriaRepository.findById(dto.getCategoria());
		if (!dto.equals(null)) {
			if (dto.getUsuario() != usuario.getId()) {
				list.add(new FieldMessage("Usuario", "Usuario de id " + dto.getUsuario() + " não está logado!"));
			}
			if (!categoria.isPresent()) {
				list.add(new FieldMessage("Categoria", "Categoria de id " + dto.getUsuario() + " não existe!"));
			}
			if (produto.isPresent()) {
				list.add(new FieldMessage("Nome", "Produto de nome " + dto.getNome() + " já existe!"));
			}
		}

		for (FieldMessage f : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(f.getMessage()).addPropertyNode(f.getFieldName())
					.addConstraintViolation();

		}
		return list.isEmpty();
	}

}
