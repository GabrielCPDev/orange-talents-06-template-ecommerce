package com.zupedu.gabriel.mercadolivre.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zupedu.gabriel.mercadolivre.entities.Transacao;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Long>{

}
