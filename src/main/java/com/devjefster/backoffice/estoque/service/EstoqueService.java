package com.devjefster.backoffice.estoque.service;

import com.devjefster.backoffice.estoque.controller.dto.EstoqueDTO;
import com.devjefster.backoffice.estoque.controller.dto.FiltrosEstoque;
import com.devjefster.backoffice.estoque.controller.dto.MovimentacaoEstoqueDTO;
import com.devjefster.backoffice.estoque.model.entidades.Estoque;
import com.devjefster.backoffice.estoque.model.entidades.GradeCadastrada;
import com.devjefster.backoffice.estoque.model.entidades.LoteEstoque;
import com.devjefster.backoffice.estoque.model.enums.TipoEstoque;
import com.devjefster.backoffice.estoque.model.enums.TipoMovimentacaoEstoque;
import com.devjefster.backoffice.estoque.model.mapper.EstoqueMapper;
import com.devjefster.backoffice.estoque.model.repositories.EstoqueRepository;
import com.devjefster.backoffice.estoque.model.specifications.EstoqueSpecifications;
import com.devjefster.backoffice.fabricacao.model.enums.UnidadeMedida;
import com.devjefster.backoffice.insumos.model.entidades.Insumo;
import com.devjefster.backoffice.pessoa.model.entidades.Pessoa;
import com.devjefster.backoffice.produto.model.Produto;
import com.devjefster.backoffice.produto.model.ProdutoAcabado;
import com.devjefster.backoffice.produto.model.ProdutoFinal;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PagedModel;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class EstoqueService {


    private final LoteEstoqueService loteEstoqueService;
    private final EstoqueRepository estoqueRepository;
    private final EstoqueMapper mapper;

    public PagedModel<EstoqueDTO> listarEstoques(FiltrosEstoque filtros, Pageable pageable) {

        if (pageable.getSort().isUnsorted()) {
            pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("validade").ascending());
        }

        Specification<Estoque> spec = Specification.where(EstoqueSpecifications.porTextoBusca(filtros.getTextoBusca()))
                .and(EstoqueSpecifications.porTipo(filtros.getTipo()))
                .and(EstoqueSpecifications.porValidade(filtros.getValidadeMinima(), filtros.getValidadeMaxima()))
                .and(EstoqueSpecifications.porUnidadeMedida(filtros.getUnidadeMedida()))
                .and(EstoqueSpecifications.somenteLotesComEstoque(filtros.isSomeLotesComEstoque()))
                .and(EstoqueSpecifications.porDataFabricacao(filtros.getFabricacaoMinima(), filtros.getFabricacaoMaxima()));

        Page<Estoque> estoques = estoqueRepository.findAll(spec, pageable);
        return mapper.toDto(estoques);
    }


    @Transactional
    public void buscarOuCriarEstoqueProdutoFinal(ProdutoFinal produto, BigDecimal quantidade, UnidadeMedida unidadeMedida, LocalDate validade, List<GradeCadastrada> grades, BigDecimal custoUnitario) {
        Optional<Estoque> optEstoque = estoqueRepository.findEstoqueByProduto_Id(produto.getId());
        Estoque estoque = optEstoque.orElseGet(() -> {
            Estoque novoEstoque = criarEstoque(produto, quantidade, unidadeMedida, validade, grades);
            return estoqueRepository.save(novoEstoque);
        });
        loteEstoqueService.buscarOuCriarEstoqueProdutoFinal(estoque, quantidade, unidadeMedida, validade, grades, custoUnitario);

    }


    @Transactional
    public void buscarOuCriarEstoqueProdutoAcabado(ProdutoAcabado produto, BigDecimal quantidade, UnidadeMedida unidadeMedida, LocalDate validade, List<GradeCadastrada> grades, BigDecimal custoUnitario) {
        Optional<Estoque> optEstoque = estoqueRepository.findEstoqueByProduto_Id(produto.getId());

        Estoque estoque = optEstoque.orElseGet(() -> {
            Estoque novoEstoque = criarEstoque(produto, quantidade, unidadeMedida, validade, grades);
            return estoqueRepository.save(novoEstoque);
        });
        loteEstoqueService.buscarOuCriarEstoqueProdutoAcabado(estoque, quantidade, unidadeMedida, validade, grades, custoUnitario);
    }

    @Transactional
    public void buscarOuCriarEstoqueInsumo(Insumo insumo, Pessoa fabricante, BigDecimal quantidade, UnidadeMedida unidadeMedida, LocalDate validade, List<GradeCadastrada> grades, BigDecimal custoUnitario, LocalDate dataEntrada) {
        Optional<Estoque> optEstoque = estoqueRepository.findByInsumo_Id(insumo.getId());
        Estoque estoque = optEstoque.orElseGet(() -> {
            Estoque novoEstoque = criarEstoque(insumo, null, quantidade, unidadeMedida, validade, grades);
            return estoqueRepository.save(novoEstoque);
        });
        loteEstoqueService.buscarOuCriarEstoqueInsumo(estoque, fabricante, quantidade, unidadeMedida, validade, grades, custoUnitario, dataEntrada);
    }

    private Estoque criarEstoque(Insumo insumo, Produto produto, BigDecimal quantidade, UnidadeMedida unidadeMedida, LocalDate validade, List<GradeCadastrada> grades) {
        Estoque novoEstoque = new Estoque();
        if (insumo != null) {
            novoEstoque.setTipo(TipoEstoque.INSUMOS);
        }
        if (produto != null) {
            if (produto instanceof ProdutoFinal) novoEstoque.setTipo(TipoEstoque.PRODUTO_FINAL);
            if (produto instanceof ProdutoAcabado) novoEstoque.setTipo(TipoEstoque.PRODUTO_ACABADO);
        }
        novoEstoque.setProduto(produto);
        novoEstoque.setInsumo(insumo);
        novoEstoque.setGrades(grades);
        novoEstoque.setUnidadeMedida(unidadeMedida);
        novoEstoque.setQuantidade(quantidade);
        novoEstoque.setValidade(validade);
        return novoEstoque;
    }

    private Estoque criarEstoque(Produto produto, BigDecimal quantidade, UnidadeMedida unidadeMedida, LocalDate validade, List<GradeCadastrada> grades) {
        return criarEstoque(null, produto, quantidade, unidadeMedida, validade, grades);
    }

    private static Estoque criarEstoque(Insumo insumo, Produto produto, BigDecimal quantidade, UnidadeMedida unidadeMedida, LocalDate validade, List<GradeCadastrada> grades, TipoEstoque tipoEstoque) {
        Estoque novoEstoque = new Estoque();
        novoEstoque.setTipo(tipoEstoque);
        novoEstoque.setProduto(produto);
        novoEstoque.setInsumo(insumo);
        novoEstoque.setGrades(grades);
        novoEstoque.setUnidadeMedida(unidadeMedida);
        novoEstoque.setQuantidade(quantidade);
        novoEstoque.setValidade(validade);
        return novoEstoque;
    }

    /**
     * Consulta lotes por validade e quantidade mínima.
     */
    public List<LoteEstoque> consultarLotes(LocalDate validadeMinima, BigDecimal quantidadeMinima) {
        return loteEstoqueService.consultarLotes(validadeMinima, quantidadeMinima);
    }

    public LoteEstoque consultarLoteEstoque(Long id) {
        return loteEstoqueService.consultarLoteEstoque(id);
    }

    @Transactional
    public void reporEstoque(Long loteId, BigDecimal quantidade) {
        loteEstoqueService.reporEstoque(loteId, quantidade);
    }

    @Transactional
    public void consumirEstoque(Long loteId, BigDecimal quantidade) {
        loteEstoqueService.consumirEstoque(loteId, quantidade);
    }

    public PagedModel<MovimentacaoEstoqueDTO> listarMovimentacoesEstoques(Long estoqueId, TipoMovimentacaoEstoque tipo, Pageable pageable) {
        return loteEstoqueService.listarMovimentacoesEstoques(estoqueId, tipo, pageable);
    }
}

