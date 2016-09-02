package com.gabriel.xclientapp.repository;

import com.gabriel.xclientapp.domain.Fornecedor;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Fornecedor entity.
 */
@SuppressWarnings("unused")
public interface FornecedorRepository extends JpaRepository<Fornecedor,Long> {

    @Query("select fornecedor from Fornecedor fornecedor where fornecedor.user.login = ?#{principal.username}")
    List<Fornecedor> findByUserIsCurrentUser();

}
