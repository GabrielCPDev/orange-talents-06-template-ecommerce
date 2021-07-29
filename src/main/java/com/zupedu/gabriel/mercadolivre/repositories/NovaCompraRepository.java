package com.zupedu.gabriel.mercadolivre.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zupedu.gabriel.mercadolivre.entities.Avaliacao;
import com.zupedu.gabriel.mercadolivre.entities.NovaCompra;

@Repository
public interface NovaCompraRepository extends JpaRepository<NovaCompra, Long>{


}
