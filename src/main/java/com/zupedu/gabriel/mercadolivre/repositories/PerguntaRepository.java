package com.zupedu.gabriel.mercadolivre.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.zupedu.gabriel.mercadolivre.entities.Pergunta;

@Repository
public interface PerguntaRepository extends JpaRepository<Pergunta, Long>{

	@Query("select p from Pergunta p where p.produto.id = :id")
	List<Pergunta> findAllByProduto(Long id);
}
