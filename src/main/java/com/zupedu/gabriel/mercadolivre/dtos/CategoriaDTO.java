package com.zupedu.gabriel.mercadolivre.dtos;

import java.io.Serializable;
import java.time.Instant;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.zupedu.gabriel.mercadolivre.entities.Categoria;
import com.zupedu.gabriel.mercadolivre.entities.Usuario;
import com.zupedu.gabriel.mercadolivre.resources.validations.CategoriaInsert;

@CategoriaInsert
public class CategoriaDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	@NotBlank(message = "Campo Obrigatório")
	private String nome;
	private Long categoriaMae;
	
	public CategoriaDTO () {
		
	}	
	
	public CategoriaDTO(Long id, String nome, Long categoriaMae) {
		super();
		this.id = id;
		this.nome = nome;
		this.categoriaMae = categoriaMae;
	}



	public CategoriaDTO (Categoria entity) {
		id = entity.getId();
		this.nome = entity.getNome();
		categoriaMae = entity.getCategoriaMae().getId();
		}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getCategoriaMae() {
		return categoriaMae;
	}

	public void setCategoriaMae(Long categoriaMaeDto) {
		categoriaMae = categoriaMaeDto;
	}
	
}
