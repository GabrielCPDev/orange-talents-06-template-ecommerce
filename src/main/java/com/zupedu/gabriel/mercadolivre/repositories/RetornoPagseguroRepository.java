package com.zupedu.gabriel.mercadolivre.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zupedu.gabriel.mercadolivre.entities.RetornoPagseguro;

@Repository
public interface RetornoPagseguroRepository extends JpaRepository<RetornoPagseguro, Long>{


}
