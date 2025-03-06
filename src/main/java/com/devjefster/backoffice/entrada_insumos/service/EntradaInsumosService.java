package com.devjefster.backoffice.entrada_insumos.service;

import com.devjefster.backoffice.entrada_insumos.model.entidades.EntradaInsumoItem;
import com.devjefster.backoffice.entrada_insumos.model.entidades.EntradaInsumos;
import com.devjefster.backoffice.entrada_insumos.model.entidades.LoteEntrada;
import com.devjefster.backoffice.entrada_insumos.model.repositories.EntradaInsumosRepository;
import com.devjefster.backoffice.entrada_insumos.model.repositories.LoteEntradaRepository;
import com.devjefster.backoffice.entrada_insumos.model.specifications.EntradaInsumosSpecifications;
import com.devjefster.backoffice.util.conversor.UnidadeMedidaConverter;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Slf4j
public class EntradaInsumosService {

    private final EntradaInsumosRepository repository;
    private final LoteEntradaRepository loteEntradaRepoository;
    private final UnidadeMedidaConverter unidadeMedidaConverter;

    @Transactional
    public EntradaInsumos criarEntrada(EntradaInsumos entrada) {
        log.info("Criando Entrada {}", entrada);
        validarEntrada(entrada);
        distribuirCustosAdicionais(entrada);

        // Primeiro salva a entrada e seus itens SEM os lotes
        entrada.getItens().forEach(item -> item.setEntradaInsumos(entrada));
        EntradaInsumos saved = salvar(entrada);

        // Agora, com os itens persistidos, cria os lotes corretamente
        saved.getItens().forEach(item -> {
            BigDecimal quantidadeConvertida = unidadeMedidaConverter.converterParaPadrao(item.getUnidadeMedidaEntrada(), item.getQuantidade());

            LoteEntrada lote = new LoteEntrada();
            lote.setEntradaItem(item);
            lote.setUnidadeMedida(item.getUnidadeMedidaEntrada());
            lote.setQuantidadeConvertida(quantidadeConvertida);
            lote.setValidade(item.getValidade());

            loteEntradaRepoository.save(lote);
        });

        log.info("Entrada criada com ID: {}", saved.getId());
        return saved;
    }


    private EntradaInsumos salvar(EntradaInsumos entrada) {
        entrada.setId(null);
        entrada.calcularCusto();
        return repository.save(entrada);
    }


    public void distribuirCustosAdicionais(EntradaInsumos entrada) {
        BigDecimal totalQuantidade = entrada.getItens().stream()
                .map(EntradaInsumoItem::getQuantidade)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        entrada.getItens().forEach(item -> {
            BigDecimal proporcao = item.getQuantidade().divide(totalQuantidade, RoundingMode.HALF_UP);
            BigDecimal custoRateado = proporcao.multiply(entrada.getCustoFrete().add(entrada.getCustoOutros()));
            item.setPrecoUnitario(item.getPrecoUnitario().add(custoRateado.divide(item.getQuantidade(), RoundingMode.HALF_UP)));
        });
    }

    public void validarEntrada(EntradaInsumos entrada) {
        entrada.getItens().forEach(item -> {
            if (item.getQuantidade().compareTo(BigDecimal.ZERO) <= 0) {
                throw new IllegalArgumentException("Quantidade deve ser maior que zero.");
            }
            if (item.getPrecoUnitario().compareTo(BigDecimal.ZERO) <= 0) {
                throw new IllegalArgumentException("Preço unitário deve ser maior que zero.");
            }
            if (item.getValidade() != null && item.getValidade().isBefore(LocalDate.now())) {
                throw new IllegalArgumentException("Validade não pode ser uma data passada.");
            }
        });
    }

    public EntradaInsumos buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entrada não encontrada com o ID: " + id));
    }


    public Page<EntradaInsumos> listar(String textoBusca, Long fornecedorId, LocalDate dataInicio, LocalDate dataFim, Pageable pageable) {
        log.info("Listando entradas de insumos com filtros - Texto: {}, Fornecedor: {}, DataInicio: {}, DataFim: {}", textoBusca, fornecedorId, dataInicio, dataFim);
        Specification<EntradaInsumos> filtros = EntradaInsumosSpecifications.filtrosDinamicos(textoBusca, fornecedorId, dataInicio, dataFim);
        return repository.findAll(filtros, pageable);
    }

    public void atualizarEntrada(Long id, EntradaInsumos entradaAtualizada) {
        EntradaInsumos entrada = buscarPorId(id);
        entrada.setFornecedor(entradaAtualizada.getFornecedor());
        entrada.setDataEntrada(entradaAtualizada.getDataEntrada());
        entrada.setCustoFrete(entradaAtualizada.getCustoFrete());
        entrada.setCustoOutros(entradaAtualizada.getCustoOutros());
        entrada.setItens(entradaAtualizada.getItens());
        salvar(entrada);
    }

    public void deletarEntrada(Long id) {
        EntradaInsumos entrada = buscarPorId(id);
        repository.delete(entrada);
    }


}
