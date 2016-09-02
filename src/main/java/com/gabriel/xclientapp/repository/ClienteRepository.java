package com.gabriel.xclientapp.repository;

import com.gabriel.xclientapp.domain.Cliente;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Cliente entity.
 */
@SuppressWarnings("unused")
public interface ClienteRepository extends JpaRepository<Cliente,Long> {

    @Query("select cliente from Cliente cliente where cliente.user.login = ?#{principal.username}")
    List<Cliente> findByUserIsCurrentUser();

}
