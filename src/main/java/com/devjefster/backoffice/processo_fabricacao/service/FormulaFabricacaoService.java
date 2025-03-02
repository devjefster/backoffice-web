package com.devjefster.backoffice.processo_fabricacao.service;

import com.devjefster.backoffice.fabricacao.model.repositories.FormulaFabricacaoRepository;
import com.devjefster.backoffice.insumos.model.enums.TipoInsumo;
import com.devjefster.backoffice.processo_fabricacao.model.entidades.FormulaFabricacao;
import com.devjefster.backoffice.processo_fabricacao.model.entidades.ProcessoFabricacao;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class FormulaFabricacaoService {
    private final FormulaFabricacaoRepository repository;
    private final ValidacaoFabricacao validacao;

    public FormulaFabricacao buscarPeloId(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Fabricacao não encontrado"));
    }

    public FormulaFabricacao salvar(FormulaFabricacao formula) {
        validacao.validarDuplicidadeDeInsumos(formula.getProcessosFabricacao());
        validacao.validarPorcentagens(formula.getProcessosFabricacao());
        if (formula.getProcessosFabricacao().isEmpty()) {
            throw new IllegalArgumentException("Uma fórmula deve conter pelo menos um processo de fabricação.");
        }

        validarPorcentagens(formula.getProcessosFabricacao());

        return repository.save(formula);
    }

    private void validarPorcentagens(List<ProcessoFabricacao> processos) {
        double somaPorcentagens = processos.stream()
                .filter(processo -> processo.getInsumo().getTipo().equals(TipoInsumo.MATERIA_PRIMA))
                .mapToDouble(ProcessoFabricacao::getPorcentagem)
                .sum();

        if (somaPorcentagens > 100.0) {
            throw new IllegalArgumentException("A soma das porcentagens dos processos de matéria-prima não pode exceder 100%.");
        }
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }


    public Page<FormulaFabricacao> listarComFiltros(Specification<FormulaFabricacao> spec, Pageable pageable) {
        return repository.findAll(spec, pageable);
    }

}
