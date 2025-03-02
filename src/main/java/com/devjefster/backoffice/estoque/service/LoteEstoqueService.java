package com.devjefster.backoffice.estoque.service;

import com.devjefster.backoffice.estoque.controller.dto.MovimentacaoEstoqueDTO;
import com.devjefster.backoffice.estoque.model.entidades.*;
import com.devjefster.backoffice.estoque.model.enums.TipoMovimentacaoEstoque;
import com.devjefster.backoffice.estoque.model.mapper.MovimentacaoEstoqueMapper;
import com.devjefster.backoffice.estoque.model.repositories.LoteEstoqueRepository;
import com.devjefster.backoffice.estoque.model.repositories.MovimentacaoEstoqueRepository;
import com.devjefster.backoffice.estoque.model.specifications.MovimentacaoEstoqueSpecifications;
import com.devjefster.backoffice.fabricacao.model.enums.UnidadeMedida;
import com.devjefster.backoffice.pessoa.model.entidades.Pessoa;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PagedModel;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoteEstoqueService {

    private final LoteEstoqueRepository loteEstoqueRepository;
    private final MovimentacaoEstoqueRepository movimentacaoEstoqueRepository;
    private final MovimentacaoEstoqueMapper movimentacaoEstoqueMapper;


    @Transactional
    public void buscarOuCriarEstoqueProdutoFinal(Estoque estoque, BigDecimal quantidade, UnidadeMedida unidadeMedida, LocalDate validade, List<GradeCadastrada> grades, BigDecimal custoUnitario) {
        LoteProdutoFinal lote = new LoteProdutoFinal();
        buscarOuCriarLoteEstoque(estoque, lote, quantidade, unidadeMedida, validade, grades, custoUnitario);
        registrarMovimentacao(lote, quantidade, TipoMovimentacaoEstoque.ENTRADA);
    }

    @Transactional
    public void buscarOuCriarEstoqueProdutoAcabado(Estoque estoque, BigDecimal quantidade, UnidadeMedida unidadeMedida, LocalDate validade, List<GradeCadastrada> grades, BigDecimal custoUnitario) {
        LoteProdutoAcabado lote = new LoteProdutoAcabado();
        buscarOuCriarLoteEstoque(estoque, lote, quantidade, unidadeMedida, validade, grades, custoUnitario);

        registrarMovimentacao(lote, quantidade, TipoMovimentacaoEstoque.ENTRADA);
    }


    @Transactional
    public void consumirEstoque(Long loteEntradaId, BigDecimal quantidade) {
        LoteEstoque loteEstoque = loteEstoqueRepository.findById(loteEntradaId)
                .orElseThrow(() -> new EntityNotFoundException("Lote do estoque não encontrado com id " + loteEntradaId));
        loteEstoque.reduzirQuantidade(quantidade);
        loteEstoque.getEstoque().reduzirQuantidade(quantidade);

        loteEstoqueRepository.save(loteEstoque);
        registrarMovimentacao(loteEstoque, quantidade, TipoMovimentacaoEstoque.SAIDA);
    }

    @Transactional
    public void buscarOuCriarEstoqueInsumo(Estoque estoque, Pessoa fabricante, BigDecimal quantidade, UnidadeMedida unidadeMedida, LocalDate validade, List<GradeCadastrada> grades, BigDecimal custoUnitario) {
        LoteInsumo lote = new LoteInsumo();
        List<LoteInsumo> loteEstoques = estoque.getLotes().stream()
                .filter(loteEstoque -> loteEstoque instanceof LoteInsumo)
                .map(loteEstoque -> (LoteInsumo) loteEstoque)
                .filter(loteInsumo -> loteInsumo.getFabricante().equals(fabricante) && loteInsumo.getValidade().equals(validade))
                .toList();
        if (loteEstoques.isEmpty()) {
            lote = criarLote(estoque, fabricante, quantidade, unidadeMedida, validade, grades, custoUnitario);
        } else {
            lote = loteEstoques.getFirst();
            lote.adicionarQuantidade(quantidade);
            lote.setCustoUnitario((lote.getCustoUnitario().add(custoUnitario)).divide(lote.getQuantidade(), RoundingMode.HALF_EVEN));
        }
        registrarMovimentacao(lote, quantidade, TipoMovimentacaoEstoque.ENTRADA);
    }

    private void registrarMovimentacao(LoteEstoque lote, BigDecimal quantidade, TipoMovimentacaoEstoque tipo) {
        MovimentacaoEstoque movimentacao = new MovimentacaoEstoque();
        movimentacao.setLoteEstoque(lote);
        movimentacao.setDataMovimentacao(LocalDate.now());
        movimentacao.setQuantidade(quantidade);
        movimentacao.setTipo(tipo);
        movimentacaoEstoqueRepository.save(movimentacao);
    }

    private void buscarOuCriarLoteEstoque(Estoque estoque, LoteEstoque lote, BigDecimal quantidade, UnidadeMedida unidadeMedida, LocalDate validade, List<GradeCadastrada> grades, BigDecimal custoUnitario) {
        lote.setEstoque(estoque);
        lote.adicionarQuantidade(quantidade);
        lote.setUnidadeMedida(unidadeMedida);
        lote.setValidade(validade);
        lote.setGrades(grades);
        lote.setCustoUnitario(custoUnitario);
        loteEstoqueRepository.save(lote);
    }

    private LoteInsumo criarLote(Estoque estoque, Pessoa fabricante, BigDecimal quantidade, UnidadeMedida unidadeMedida, LocalDate validade, List<GradeCadastrada> grades, BigDecimal custoUnitario) {
        LoteInsumo lote = new LoteInsumo();
        lote.setEstoque(estoque);
        lote.setFabricante(fabricante);
        lote.adicionarQuantidade(quantidade);
        lote.setUnidadeMedida(unidadeMedida);
        lote.setValidade(validade);
        lote.setGrades(grades);
        lote.setCustoUnitario(custoUnitario);
        return loteEstoqueRepository.save(lote);
    }

    /**
     * Consulta lotes por validade e quantidade mínima.
     */
    public List<LoteEstoque> consultarLotes(LocalDate validadeMinima, BigDecimal quantidadeMinima) {
        return loteEstoqueRepository.findByValidadeAfterAndQuantidadeGreaterThanEqual(validadeMinima, quantidadeMinima);
    }

    public LoteEstoque consultarLoteEstoque(Long id) {
        return loteEstoqueRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Lote do estoque não encontrado com id " + id));
    }

    @Transactional
    public void reporEstoque(Long loteId, BigDecimal quantidade) {
        LoteEstoque lote = loteEstoqueRepository.findById(loteId)
                .orElseThrow(() -> new EntityNotFoundException("Lote do estoque não encontrado com id " + loteId));
        lote.adicionarQuantidade(quantidade);
        lote.getEstoque().adicionarQuantidade(quantidade);

        loteEstoqueRepository.save(lote);
    }

    public PagedModel<MovimentacaoEstoqueDTO> listarMovimentacoesEstoques(Long estoqueId, TipoMovimentacaoEstoque tipo, Pageable pageable) {
        if (pageable.getSort().isUnsorted()) {
            pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("dataMovimentacao").ascending());
        }
        Specification<MovimentacaoEstoque> spec = Specification.where(MovimentacaoEstoqueSpecifications.porEstoque(estoqueId)).and(MovimentacaoEstoqueSpecifications.porTipo(tipo));
        return movimentacaoEstoqueMapper.toDto(movimentacaoEstoqueRepository.findAll(spec, pageable));
    }


}
