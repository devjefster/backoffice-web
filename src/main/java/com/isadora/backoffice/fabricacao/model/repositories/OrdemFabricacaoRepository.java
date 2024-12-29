package com.isadora.backoffice.fabricacao.model.repositories;

import com.isadora.backoffice.fabricacao.model.entidades.OrdemFabricacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OrdemFabricacaoRepository extends JpaRepository<OrdemFabricacao, Long> , JpaSpecificationExecutor<OrdemFabricacao> {
}
