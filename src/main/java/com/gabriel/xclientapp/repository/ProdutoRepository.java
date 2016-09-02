package com.gabriel.xclientapp.repository;

import com.gabriel.xclientapp.domain.Produto;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Produto entity.
 */
@SuppressWarnings("unused")
public interface ProdutoRepository extends JpaRepository<Produto,Long> {

    @Query("select produto from Produto produto where produto.user.login = ?#{principal.username}")
    List<Produto> findByUserIsCurrentUser();

}
