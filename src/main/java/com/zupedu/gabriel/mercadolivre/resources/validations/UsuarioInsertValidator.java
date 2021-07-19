package com.zupedu.gabriel.mercadolivre.resources.validations;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.zupedu.gabriel.mercadolivre.dtos.UsuarioNewDTO;
import com.zupedu.gabriel.mercadolivre.repositories.UsuarioRepository;
import com.zupedu.gabriel.mercadolivre.resources.exceptions.FieldMessage;

public class UsuarioInsertValidator implements ConstraintValidator<UsuarioInsert, UsuarioNewDTO> {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public void initialize(UsuarioInsert ui) {}

	@Override
	public boolean isValid(UsuarioNewDTO dto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		var obj = usuarioRepository.findByEmail(dto.getEmail());
		if (obj != null) {
			list.add(new FieldMessage("email", "Email " + dto.getEmail() + " já eciste"));
		}
		
		for (FieldMessage f : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(f.getMessage()).addPropertyNode(f.getFieldName())
			.addConstraintViolation();
			
		}
		return list.isEmpty();
	}
	
}
