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
		Optional<Categoria> obj = Optional.ofNullable(new Categoria());
		
		if(!dto.equals(null)) {
			if (dto.getNome() != null) {
				obj = Optional.ofNullable(categoriaRepository.findByNome(dto.getNome()));
				if (obj.get().getNome() != null) {
					list.add(new FieldMessage("Nome", "Nome " + dto.getNome() + " já existe"));
				}
			}else if (dto.getCategoriaMae() != null) {
				obj = categoriaRepository.findById(dto.getCategoriaMae().longValue());
				if (obj.get().getId() == null) {
					list.add(new FieldMessage("Id ", "Categoria de id: " + dto.getCategoriaMae() + " Não existe"));
				}
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
