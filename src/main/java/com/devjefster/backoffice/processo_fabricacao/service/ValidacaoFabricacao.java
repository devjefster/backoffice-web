package com.devjefster.backoffice.processo_fabricacao.service;

import com.devjefster.backoffice.fabricacao.model.enums.UnidadeMedida;
import com.devjefster.backoffice.insumos.model.entidades.Insumo;
import com.devjefster.backoffice.insumos.model.enums.TipoInsumo;
import com.devjefster.backoffice.processo_fabricacao.model.entidades.ProcessoFabricacao;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class ValidacaoFabricacao {
    public void validarDuplicidadeDeInsumos(List<ProcessoFabricacao> processos) {
        Set<Long> insumosIds = new HashSet<>();
        for (ProcessoFabricacao processo : processos) {
            if (!insumosIds.add(processo.getInsumo().getId())) {
                throw new IllegalArgumentException("Não é permitido repetir o mesmo insumo em um processo de fabricação.");
            }
        }
    }

    public void validarPorcentagens(List<ProcessoFabricacao> processos) {
        double somaPorcentagens = processos.stream()
                .filter(processo -> processo.getInsumo().getTipo().equals(TipoInsumo.MATERIA_PRIMA))
                .mapToDouble(ProcessoFabricacao::getPorcentagem)
                .sum();

        if (somaPorcentagens > 100.0) {
            throw new IllegalArgumentException("A soma das porcentagens dos processos de matéria-prima não pode exceder 100%.");
        }
    }

    public void validarUnidadeDeMedida(Insumo insumo, UnidadeMedida unidadeEsperada) {
        if (insumo.getUnidadeMedida() != unidadeEsperada) {
            throw new IllegalArgumentException(String.format(
                    "A unidade de medida para o insumo %s deve ser %s.",
                    insumo.getNome(), unidadeEsperada
            ));
        }
    }
}
