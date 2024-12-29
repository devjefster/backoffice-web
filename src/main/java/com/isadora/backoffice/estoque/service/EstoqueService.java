package com.isadora.backoffice.estoque.service;

import com.isadora.backoffice.estoque.controller.dto.EstoqueDTO;
import com.isadora.backoffice.estoque.controller.dto.FiltrosEstoque;
import com.isadora.backoffice.estoque.controller.dto.MovimentacaoEstoqueDTO;
import com.isadora.backoffice.estoque.model.entidades.Estoque;
import com.isadora.backoffice.estoque.model.entidades.GradeCadastrada;
import com.isadora.backoffice.estoque.model.entidades.LoteEstoque;
import com.isadora.backoffice.estoque.model.enums.TipoEstoque;
import com.isadora.backoffice.estoque.model.enums.TipoMovimentacaoEstoque;
import com.isadora.backoffice.estoque.model.mapper.EstoqueMapper;
import com.isadora.backoffice.estoque.model.repositories.EstoqueRepository;
import com.isadora.backoffice.estoque.model.specifications.EstoqueSpecifications;
import com.isadora.backoffice.fabricacao.model.enums.UnidadeMedida;
import com.isadora.backoffice.insumos.model.entidades.Insumo;
import com.isadora.backoffice.pessoa.model.entidades.Fabricante;
import com.isadora.backoffice.produto.model.Produto;
import com.isadora.backoffice.produto.model.ProdutoAcabado;
import com.isadora.backoffice.produto.model.ProdutoFinal;
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
    public void buscarOuCriarEstoqueInsumo(Insumo insumo, Fabricante fabricante, BigDecimal quantidade, UnidadeMedida unidadeMedida, LocalDate validade, List<GradeCadastrada> grades, BigDecimal custoUnitario) {
        Optional<Estoque> optEstoque = estoqueRepository.findByInsumo_Id(insumo.getId());
        Estoque estoque = optEstoque.orElseGet(() -> {
            Estoque novoEstoque = criarEstoque(insumo, null, quantidade, unidadeMedida, validade, grades);
            return estoqueRepository.save(novoEstoque);
        });
        loteEstoqueService.buscarOuCriarEstoqueInsumo(estoque, fabricante, quantidade, unidadeMedida, validade, grades, custoUnitario);
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

