package com.devjefster.backoffice.seguranca.model.repository;

import com.devjefster.backoffice.seguranca.model.entidades.Login;
import com.devjefster.backoffice.seguranca.model.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoginRepository extends JpaRepository<Login, Long> {

    Optional<Login> findByUsername(String username);
    Optional<Login> findByUsuario(Usuario usuario);


}
