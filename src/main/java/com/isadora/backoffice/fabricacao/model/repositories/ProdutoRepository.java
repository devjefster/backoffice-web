package com.isadora.backoffice.fabricacao.model.repositories;

import com.isadora.backoffice.produto.model.ProdutoAcabado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoAcabado, Long> {
}
