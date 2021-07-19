package com.zupedu.gabriel.mercadolivre.dtos;

import javax.validation.constraints.NotBlank;

public class UsuarioNewDTO extends UsuarioDTO {
	private static final long serialVersionUID = 1L;
	
	@NotBlank
	private String senha;
	
	
	public UsuarioNewDTO () {
		super();
	}


	public String getSenha() {
		return senha;
	}


	public void setSenha(String senha) {
		this.senha = senha;
	}

	
	

}
