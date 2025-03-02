package com.devjefster.backoffice.estoque.model.repositories;

import com.devjefster.backoffice.estoque.model.entidades.Estoque;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EstoqueRepository extends CrudRepository<Estoque, Long>, JpaSpecificationExecutor<Estoque> {
    Optional<Estoque> findEstoqueByProduto_Id(Long produtoAcabadoId);

    Optional<Estoque> findByInsumo_Id(Long id);
}
