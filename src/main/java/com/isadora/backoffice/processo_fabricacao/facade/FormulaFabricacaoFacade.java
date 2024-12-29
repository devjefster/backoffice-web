package com.isadora.backoffice.processo_fabricacao.facade;

import com.isadora.backoffice.fabricacao.model.mapper.FormulaFabricacaoMapper;
import com.isadora.backoffice.fabricacao.model.specifications.FormulaFabricacaoSpecification;
import com.isadora.backoffice.insumos.service.InsumoService;
import com.isadora.backoffice.processo_fabricacao.controller.dto.CriarFabricacaoRequest;
import com.isadora.backoffice.processo_fabricacao.controller.dto.FormulaFabricacaoDTO;
import com.isadora.backoffice.processo_fabricacao.controller.dto.ProcessoFabricacaoDTO;
import com.isadora.backoffice.processo_fabricacao.model.entidades.FormulaFabricacao;
import com.isadora.backoffice.processo_fabricacao.model.entidades.ProcessoFabricacao;
import com.isadora.backoffice.processo_fabricacao.model.enums.TipoProcessoFabricacao;
import com.isadora.backoffice.processo_fabricacao.service.FormulaFabricacaoService;
import com.isadora.backoffice.produto.model.Produto;
import com.isadora.backoffice.produto.model.ProdutoAcabado;
import com.isadora.backoffice.produto.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PagedModel;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@Slf4j
@RequiredArgsConstructor
public class FormulaFabricacaoFacade {
    private final FormulaFabricacaoMapper mapper;
    private final FormulaFabricacaoService formulaFabricacaoService;
    private final InsumoService insumoService;
    private final ProdutoService produtoService;

    public FormulaFabricacaoDTO criar(CriarFabricacaoRequest request) {
        validarDuplicidadeDeInsumos(request.getProcessos());
        Produto produtoAcabado = produtoService.buscarPeloId(request.getProdutoId());

        FormulaFabricacao formulaFabricacao = new FormulaFabricacao();
        formulaFabricacao.setProduto((ProdutoAcabado) produtoAcabado);
        formulaFabricacao.setProcessosFabricacao(request.getProcessos().stream().map(proporcaoFabricacaoDTO -> {
            ProcessoFabricacao processoFabricacao = new ProcessoFabricacao();
            processoFabricacao.setInsumo(insumoService.buscarPeloId(proporcaoFabricacaoDTO.getInsumoId()));
            processoFabricacao.setPorcentagem(proporcaoFabricacaoDTO.getPorcentagem());
            processoFabricacao.setFormulaFabricacao(formulaFabricacao);
            return processoFabricacao;
        }).toList());
        return mapper.toDto(formulaFabricacaoService.salvar(formulaFabricacao));
    }

    public FormulaFabricacaoDTO buscarPorId(Long id) {
        return mapper.toDto(formulaFabricacaoService.buscarPeloId(id));
    }

    public PagedModel<FormulaFabricacaoDTO> listarTodasComFiltros(
            String textoBusca, LocalDate dataInicio, LocalDate dataFim, TipoProcessoFabricacao tipoProcesso, Pageable pageable) {

        Specification<FormulaFabricacao> spec = Specification.where(FormulaFabricacaoSpecification.porProduto(textoBusca))
                .and(FormulaFabricacaoSpecification.porDataInicio(dataInicio))
                .and(FormulaFabricacaoSpecification.porDataFim(dataFim))
                .and(FormulaFabricacaoSpecification.porTipoProcesso(tipoProcesso));

        return mapper.toDto(formulaFabricacaoService.listarComFiltros(spec, pageable));
    }


    public FormulaFabricacaoDTO atualizar(Long id, CriarFabricacaoRequest request) {
        FormulaFabricacao formulaExistente = formulaFabricacaoService.buscarPeloId(id);
        ProdutoAcabado produtoAcabado = (ProdutoAcabado) produtoService.buscarPeloId(request.getProdutoId());

        formulaExistente.setProduto(produtoAcabado);

        List<ProcessoFabricacao> processosAtualizados = request.getProcessos().stream().map(proporcaoFabricacaoDTO -> {
            ProcessoFabricacao processo = formulaExistente.getProcessosFabricacao().stream()
                    .filter(p -> p.getInsumo().getId().equals(proporcaoFabricacaoDTO.getInsumoId()))
                    .findFirst()
                    .orElse(new ProcessoFabricacao());

            processo.setInsumo(insumoService.buscarPeloId(proporcaoFabricacaoDTO.getInsumoId()));
            processo.setPorcentagem(proporcaoFabricacaoDTO.getPorcentagem());
            processo.setFormulaFabricacao(formulaExistente);
            return processo;
        }).toList();

        formulaExistente.getProcessosFabricacao().clear();
        formulaExistente.getProcessosFabricacao().addAll(processosAtualizados);

        return mapper.toDto(formulaFabricacaoService.salvar(formulaExistente));
    }


    public void deletar(Long id) {
        formulaFabricacaoService.deletar(id);
    }

    private void validarDuplicidadeDeInsumos(List<ProcessoFabricacaoDTO> processos) {
        Set<Long> insumosIds = new HashSet<>();
        for (ProcessoFabricacaoDTO processo : processos) {
            if (!insumosIds.add(processo.getInsumoId())) {
                throw new IllegalArgumentException("Não é permitido repetir o mesmo insumo em um processo de fabricação.");
            }
        }
    }
}
