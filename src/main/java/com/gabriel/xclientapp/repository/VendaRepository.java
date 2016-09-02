package com.gabriel.xclientapp.repository;

import com.gabriel.xclientapp.domain.Venda;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Venda entity.
 */
@SuppressWarnings("unused")
public interface VendaRepository extends JpaRepository<Venda,Long> {

}
