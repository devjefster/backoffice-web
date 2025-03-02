package com.devjefster.backoffice.fabricacao.model.repositories;

import com.devjefster.backoffice.processo_fabricacao.model.entidades.ProcessoFabricacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProcessoFabricacaoRepository extends JpaRepository<ProcessoFabricacao, Long> {

    List<ProcessoFabricacao> findByFormulaFabricacaoId(Long formulaId);
}
