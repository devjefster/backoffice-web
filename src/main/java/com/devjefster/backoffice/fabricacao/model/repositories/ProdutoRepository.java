package com.devjefster.backoffice.fabricacao.model.repositories;

import com.devjefster.backoffice.produto.model.ProdutoAcabado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoAcabado, Long> {
}
