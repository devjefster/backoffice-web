package com.isadora.backoffice.fabricacao.facade;

import com.isadora.backoffice.estoque.model.entidades.LoteEstoque;
import com.isadora.backoffice.estoque.service.EstoqueService;
import com.isadora.backoffice.fabricacao.controller.dto.OrdemFabricacaoDTO;
import com.isadora.backoffice.fabricacao.controller.dto.OrdemFabricacaoFiltros;
import com.isadora.backoffice.fabricacao.model.entidades.ItensConsumidosFabricacao;
import com.isadora.backoffice.fabricacao.model.entidades.OrdemFabricacao;
import com.isadora.backoffice.fabricacao.model.enums.StatusFabricacao;
import com.isadora.backoffice.fabricacao.model.mapper.OrdemFabricacaoMapper;
import com.isadora.backoffice.fabricacao.service.OrdemFabricacaoService;
import com.isadora.backoffice.notificacao.model.Notificacao;
import com.isadora.backoffice.notificacao.model.TipoNotificacao;
import com.isadora.backoffice.notificacao.service.NotificacaoService;
import com.isadora.backoffice.produto.model.Produto;
import com.isadora.backoffice.produto.model.ProdutoAcabado;
import com.isadora.backoffice.produto.model.ProdutoFinal;
import com.isadora.backoffice.produto.model.enums.TipoProduto;
import com.isadora.backoffice.produto.service.ProdutoService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrdemFabricacaoFacade {

    private final OrdemFabricacaoService ordemFabricacaoService;
    private final EstoqueService estoqueService;
    private final ProdutoService produtoService;
    private final NotificacaoService notificacaoService;
    private final OrdemFabricacaoMapper mapper;

    @Transactional
    public PagedModel<OrdemFabricacaoDTO> listarOrdens(OrdemFabricacaoFiltros fabricacaoFiltros, Pageable pageable) {
        return mapper.toDto(ordemFabricacaoService.listarOrdens(fabricacaoFiltros, pageable));
    }

    @Transactional
    public OrdemFabricacaoDTO buscarOrdemPorId(Long id) {
        return mapper.toDto(ordemFabricacaoService.buscarPeloId(id));
    }

    @Transactional
    public OrdemFabricacaoDTO finalizarOrdemFabricacao(Long id) {
        OrdemFabricacao ordemFabricacao = ordemFabricacaoService.buscarPeloId(id);

        if (ordemFabricacao.getStatus() != StatusFabricacao.EM_ANDAMENTO) {
            throw new IllegalStateException("A ordem de fabricação deve estar em andamento para ser finalizada.");
        }

        // Consumir os insumos do estoque
        ordemFabricacao.getItens().forEach(item -> {
            estoqueService.consumirEstoque(item.getLote().getId(), item.getQuantidade());
        });

        // Adicionar o produto fabricado ao estoque
        BigDecimal custoUnitario = calcularCustoUnitario(ordemFabricacao);
        if (ordemFabricacao.getProduto().getTipoProduto().equals(TipoProduto.PRODUTO_ACABADO)) {
            estoqueService.buscarOuCriarEstoqueProdutoAcabado(
                    (ProdutoAcabado) ordemFabricacao.getProduto(),
                    ordemFabricacao.getQuantidadeProduzida(),
                    ordemFabricacao.getProduto().getUnidadeMedida(),
                    ordemFabricacao.getValidade(),
                    ordemFabricacao.getGrades(),
                    custoUnitario
            );
        } else if (ordemFabricacao.getProduto().getTipoProduto().equals(TipoProduto.PRODUTO_FINAL)) {
            estoqueService.buscarOuCriarEstoqueProdutoFinal(
                    (ProdutoFinal) ordemFabricacao.getProduto(),
                    ordemFabricacao.getQuantidadeProduzida(),
                    ordemFabricacao.getProduto().getUnidadeMedida(),
                    ordemFabricacao.getValidade(),
                    ordemFabricacao.getGrades(),
                    custoUnitario
            );
        }

        // Atualizar o status da ordem para CONCLUIDO
        ordemFabricacao.setStatus(StatusFabricacao.CONCLUIDO);
        ordemFabricacao.setCustoTotal(custoUnitario.multiply(ordemFabricacao.getQuantidadeProduzida()));
        return mapper.toDto(ordemFabricacaoService.criar(ordemFabricacao));
    }

    @Transactional
    public OrdemFabricacaoDTO criarOrdemFabricacao(OrdemFabricacaoDTO dto) {
        if (dto.getId() != null) {
            return atualizarOrdemFabricacao(dto.getId(), dto);
        }

        OrdemFabricacao ordemFabricacao = mapper.toEntity(dto);
        Produto produto = produtoService.buscarPeloId(ordemFabricacao.getId());
        if (dto.getDataNotificacao() != null) {
            ordemFabricacao.setStatus(StatusFabricacao.EM_ANDAMENTO);
        }
        ordemFabricacaoService.criar(ordemFabricacao);
        if (ordemFabricacao.isRascunho()) {
            atualizarEstoque(ordemFabricacao, produto);
            criarNotificacao(dto.getDataNotificacao(), ordemFabricacao);
        }

        return mapper.toDto(ordemFabricacao);
    }

    private void atualizarEstoque(OrdemFabricacao ordemFabricacao, Produto produto) {
        ordemFabricacao.getItens().forEach(itensConsumidosFabricacao -> estoqueService.consumirEstoque(itensConsumidosFabricacao.getLote().getId(), itensConsumidosFabricacao.getQuantidade()));
        if (ordemFabricacao.getProduto().getTipoProduto().equals(TipoProduto.PRODUTO_ACABADO)) {
            BigDecimal custoUnitario = calcularCustoUnitario(ordemFabricacao);
            estoqueService.buscarOuCriarEstoqueProdutoAcabado((ProdutoAcabado) produto, ordemFabricacao.getQuantidadeProduzida(), produto.getUnidadeMedida(), ordemFabricacao.getValidade(), ordemFabricacao.getGrades(), custoUnitario);
        }
        if (ordemFabricacao.getProduto().getTipoProduto().equals(TipoProduto.PRODUTO_FINAL)) {
            BigDecimal custoUnitario = calcularCustoUnitario(ordemFabricacao);
            estoqueService.buscarOuCriarEstoqueProdutoFinal((ProdutoFinal) produto, ordemFabricacao.getQuantidadeProduzida(), produto.getUnidadeMedida(), ordemFabricacao.getValidade(), ordemFabricacao.getGrades(), custoUnitario);
        }
    }

    private void criarNotificacao(LocalDateTime dataNotificacao, OrdemFabricacao ordemFabricacao) {
        Notificacao notificacao = new Notificacao();
        notificacao.setDataNotificacao(dataNotificacao);
        notificacao.setTipo(TipoNotificacao.FABRICACAO);
        notificacao.setTitulo("Maceração do produto");
        notificacao.setDescricao("Maceração do produto: " + ordemFabricacao.getProduto().getNome() + " foi concluída");
        notificacaoService.criar(notificacao);
    }

    /**
     * Calcular o custo total da fabricacao a partir dos valores da ordem de fabricacao e dos lotes de estoque usados
     */
    private BigDecimal calcularCustoUnitario(OrdemFabricacao ordemFabricacao) {
        BigDecimal custoTotal = BigDecimal.ZERO;
        for (ItensConsumidosFabricacao item : ordemFabricacao.getItens()) {
            LoteEstoque lote = estoqueService.consultarLoteEstoque(item.getLote().getId());
            custoTotal = custoTotal.add(lote.getCustoUnitario().multiply(item.getQuantidade()));
        }
        return custoTotal.divide(ordemFabricacao.getQuantidadeProduzida(), RoundingMode.HALF_EVEN);
    }

    @Transactional
    public OrdemFabricacaoDTO atualizarOrdemFabricacao(Long id, OrdemFabricacaoDTO dto) {
        OrdemFabricacao ordemFabricacao = mapper.toEntity(dto);
        OrdemFabricacao ordemExistente = ordemFabricacaoService.buscarPeloId(dto.getId());
        reverterOrdemFabricacao(id);

        // Atualizar os dados da ordem existente
        ordemExistente.setQuantidadeProduzida(dto.getQuantidadeProduzida());
        ordemExistente.setValidade(dto.getValidade());
        ordemExistente.setObservacoes(dto.getObservacoes());
        ordemExistente.setItens(ordemFabricacao.getItens());
        ordemExistente.setStatus(dto.getStatus());

        return mapper.toDto(ordemFabricacaoService.criar(ordemExistente));
    }

    @Transactional
    public void reverterOrdemFabricacao(Long id) {
        OrdemFabricacao ordemFabricacao = ordemFabricacaoService.buscarPeloId(id);

        // Verificar se a ordem já foi concluída
        if (ordemFabricacao.getStatus() == StatusFabricacao.CONCLUIDO) {
            // Reverter a adição do produto fabricado ao estoque
            estoqueService.consumirEstoque(ordemFabricacao.getProduto().getId(), ordemFabricacao.getQuantidadeProduzida());

            // Reverter o consumo dos insumos
            ordemFabricacao.getItens().forEach(item -> {
                estoqueService.reporEstoque(item.getLote().getId(), item.getQuantidade());
            });
        }
    }

    @Transactional
    public void deletarOrdemFabricacao(Long id) {
        OrdemFabricacao ordemFabricacao = ordemFabricacaoService.buscarPeloId(id);

        reverterOrdemFabricacao(id);

        ordemFabricacaoService.deletar(ordemFabricacao);
    }
}
