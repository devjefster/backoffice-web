package com.devjefster.backoffice.processo_fabricacao.service;

import com.devjefster.backoffice.processo_fabricacao.model.entidades.ProcessoFabricacao;
import com.devjefster.backoffice.fabricacao.model.repositories.ProcessoFabricacaoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProcessoFabricacaoService {

    private final ProcessoFabricacaoRepository repository;

    public ProcessoFabricacao criar(ProcessoFabricacao processo) {
        validateProcesso(processo);
        return repository.save(processo);
    }

    public ProcessoFabricacao atualizar(Long id, ProcessoFabricacao processoAtualizado) {
        ProcessoFabricacao existente = buscarPeloId(id);
        existente.setInsumo(processoAtualizado.getInsumo());
        existente.setPorcentagem(processoAtualizado.getPorcentagem());
        return repository.save(existente);
    }

    private void validateProcesso(ProcessoFabricacao processo) {
        if (processo.getInsumo() == null) {
            throw new IllegalArgumentException("O insumo é obrigatório no processo de fabricação.");
        }
        if (processo.getPorcentagem() == 0.0 || processo.getPorcentagem() <= 0) {
            throw new IllegalArgumentException("A porcentagem do processo deve ser maior que zero.");
        }
    }

    public ProcessoFabricacao buscarPeloId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Processo não encontrado com ID: " + id));
    }

    public List<ProcessoFabricacao> listar() {
        return repository.findAll();
    }


    public void deletar(Long id) {
        repository.deleteById(id);
    }

    public BigDecimal calcularCusto(ProcessoFabricacao processo, BigDecimal custoMateriaPrimaTotal) {
        double porcentagem = processo.getPorcentagem() / 100.0;
        return custoMateriaPrimaTotal.multiply(BigDecimal.valueOf(porcentagem));
    }

    public List<ProcessoFabricacao> buscarPorFormula(Long formulaId) {
        return repository.findByFormulaFabricacaoId(formulaId);
    }
}