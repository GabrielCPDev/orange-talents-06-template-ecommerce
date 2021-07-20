package com.zupedu.gabriel.mercadolivre.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zupedu.gabriel.mercadolivre.entities.Categoria;
import com.zupedu.gabriel.mercadolivre.entities.Usuario;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long>{

	Categoria findByNome(String nome);

}
