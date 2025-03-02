package com.devjefster.backoffice.estoque.model.repositories;

import com.devjefster.backoffice.estoque.model.entidades.MovimentacaoEstoque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MovimentacaoEstoqueRepository extends JpaRepository<MovimentacaoEstoque, Long>, JpaSpecificationExecutor<MovimentacaoEstoque> {
}

