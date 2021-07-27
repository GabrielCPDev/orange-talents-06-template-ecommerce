package com.zupedu.gabriel.mercadolivre.resources.utils;

import org.springframework.security.core.context.SecurityContextHolder;

public class UsuarioUtil {

	public static UsuarioSS authenticated() {
		try {
			var usuarioLodago = new UsuarioSS();
			usuarioLodago.setEmail(SecurityContextHolder.getContext().getAuthentication().getName());
			return usuarioLodago;
		} catch (Exception e) {
			System.out.println("Deu merda");
			return null;
		}
	}
}
