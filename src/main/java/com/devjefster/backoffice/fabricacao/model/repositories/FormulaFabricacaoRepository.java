package com.devjefster.backoffice.fabricacao.model.repositories;

import com.devjefster.backoffice.processo_fabricacao.model.entidades.FormulaFabricacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface FormulaFabricacaoRepository extends JpaRepository<FormulaFabricacao, Long>, JpaSpecificationExecutor<FormulaFabricacao> {

}
