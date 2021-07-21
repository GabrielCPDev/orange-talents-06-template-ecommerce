package com.zupedu.gabriel.mercadolivre.resources.validations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.zupedu.gabriel.mercadolivre.dtos.CategoriaDTO;
import com.zupedu.gabriel.mercadolivre.entities.Categoria;
import com.zupedu.gabriel.mercadolivre.repositories.CategoriaRepository;
import com.zupedu.gabriel.mercadolivre.resources.exceptions.FieldMessage;

public class CategoriaInsertValidator implements ConstraintValidator<CategoriaInsert, CategoriaDTO> {

	@Autowired
	private 	CategoriaRepository categoriaRepository;
	
	public void initialize(UsuarioInsert ui) {}

	@Override
	public boolean isValid(CategoriaDTO dto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		
		var entity = categoriaRepository.findByNome(dto.getNome());
		var catEmpity = new Categoria();
		if(!dto.equals(null)) {
			Categoria cat = entity.orElse(catEmpity);
			if (cat.getNome() != null) {						
					list.add(new FieldMessage("Nome", "Nome " + dto.getNome() + " já existe"));
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
