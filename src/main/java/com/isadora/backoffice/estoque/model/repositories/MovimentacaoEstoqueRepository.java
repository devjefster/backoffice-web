package com.isadora.backoffice.estoque.model.repositories;

import com.isadora.backoffice.estoque.model.entidades.LoteEstoque;
import com.isadora.backoffice.estoque.model.entidades.MovimentacaoEstoque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MovimentacaoEstoqueRepository extends JpaRepository<MovimentacaoEstoque, Long>, JpaSpecificationExecutor<MovimentacaoEstoque> {
}

