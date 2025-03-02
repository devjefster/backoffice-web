package com.devjefster.backoffice.pessoa.model.repositories;

import com.devjefster.backoffice.pessoa.model.entidades.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}
