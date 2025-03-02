package com.devjefster.backoffice.entrada_insumos.facade;

import com.devjefster.backoffice.entrada_insumos.controller.dto.EntradaInsumosDTO;
import com.devjefster.backoffice.entrada_insumos.model.entidades.EntradaInsumoItem;
import com.devjefster.backoffice.entrada_insumos.model.entidades.EntradaInsumos;
import com.devjefster.backoffice.entrada_insumos.model.mapper.EntradaInsumosMapper;
import com.devjefster.backoffice.entrada_insumos.service.EntradaInsumosService;
import com.devjefster.backoffice.estoque.service.EstoqueService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class EntradaInsumosFacade {

    private final EntradaInsumosService entradaService;
    private final EstoqueService estoqueService;
    private final EntradaInsumosMapper mapper;

    @Transactional
    public EntradaInsumosDTO criarEntrada(EntradaInsumosDTO dto) {
        EntradaInsumos entrada = mapper.toEntity(dto);
        EntradaInsumos entradaInsumos = entradaService.criarEntrada(entrada);
        buscarOuCriarEstoqueInsumo(entradaInsumos.getItens());
        return mapper.toDto(entradaInsumos);
    }

    public EntradaInsumosDTO buscarPorId(Long id) {
        EntradaInsumos entrada = entradaService.buscarPorId(id);
        return mapper.toDto(entrada);
    }


    public PagedModel<EntradaInsumosDTO> listar(String textoBusca, Long fornecedorId, LocalDate dataInicio, LocalDate dataFim, Pageable pageable) {
        Page<EntradaInsumos> entradas = entradaService.listar(textoBusca, fornecedorId, dataInicio, dataFim, pageable);
        return mapper.toDto(entradas);
    }

    @Transactional
    public void atualizarEntrada(Long id, EntradaInsumosDTO dto) {
        EntradaInsumos novaEntrada = mapper.toEntity(dto);
        EntradaInsumos entradaExistente = entradaService.buscarPorId(id);

        reverterEntradaInsumos(entradaExistente);

        // Atualiza os dados da entrada existente
        entradaExistente.setFornecedor(novaEntrada.getFornecedor());
        entradaExistente.setDataEntrada(novaEntrada.getDataEntrada());
        entradaExistente.setCustoFrete(novaEntrada.getCustoFrete());
        entradaExistente.setCustoOutros(novaEntrada.getCustoOutros());
        entradaExistente.setItens(novaEntrada.getItens());

        entradaService.atualizarEntrada(id, entradaExistente);

        buscarOuCriarEstoqueInsumo(entradaExistente.getItens());
    }


    @Transactional

    public void deletarEntrada(Long id) {
        EntradaInsumos entrada = entradaService.buscarPorId(id);

        reverterEntradaInsumos(entrada);

        entradaService.deletarEntrada(id);
    }

    private void reverterEntradaInsumos(EntradaInsumos entrada) {
        entrada.getItens().forEach(item -> {
            if (item.getLote() != null) {
                estoqueService.consumirEstoque(item.getLote().getId(), item.getQuantidade());
            }
        });
    }

    private void buscarOuCriarEstoqueInsumo(List<EntradaInsumoItem> entradaInsumos) {
        entradaInsumos.forEach(entradaInsumoItem -> {
            estoqueService.buscarOuCriarEstoqueInsumo(entradaInsumoItem.getInsumo(), entradaInsumoItem.getFabricante(), entradaInsumoItem.getQuantidade(), entradaInsumoItem.getUnidadeMedidaEntrada(), entradaInsumoItem.getValidade(), entradaInsumoItem.getGrades(), entradaInsumoItem.getPrecoUnitario());
        });

    }
}
