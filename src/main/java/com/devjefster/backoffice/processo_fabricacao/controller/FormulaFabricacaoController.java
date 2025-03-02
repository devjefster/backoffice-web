package com.devjefster.backoffice.processo_fabricacao.controller;

import com.devjefster.backoffice.processo_fabricacao.controller.dto.CriarFabricacaoRequest;
import com.devjefster.backoffice.processo_fabricacao.controller.dto.FormulaFabricacaoDTO;
import com.devjefster.backoffice.processo_fabricacao.facade.FormulaFabricacaoFacade;
import com.devjefster.backoffice.processo_fabricacao.model.enums.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/formula-fabricacao")
@RequiredArgsConstructor
@Slf4j
public class FormulaFabricacaoController {

    private final FormulaFabricacaoFacade facade;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FormulaFabricacaoDTO criarFabricacao(@RequestBody CriarFabricacaoRequest request) {
        log.info("Criar fórmula de fabricação {}", request);
        return facade.criar(request);
    }

    @GetMapping("/{id}")
    public FormulaFabricacaoDTO buscarPorId(@PathVariable Long id) {
        log.info("Buscando fórmula de fabricação {}", id);
        return facade.buscarPorId(id);
    }

    @GetMapping
    public PagedModel<FormulaFabricacaoDTO> listarTodasComFiltros(
            @RequestParam(required = false) String textoBusca,
            @RequestParam(required = false) TipoProcessoFabricacao tipoProcesso,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim,
            Pageable pageable) {
        log.info("Buscando fórmula de fabricação {},{},{}", textoBusca, dataInicio, dataFim);
        return facade.listarTodasComFiltros(textoBusca, dataInicio, dataFim, tipoProcesso, pageable);
    }

    @PutMapping("/{id}")
    public FormulaFabricacaoDTO atualizar(@PathVariable Long id, @RequestBody CriarFabricacaoRequest request) {
        log.info("Alterando fórmula de fabricação {}", request);
        return facade.atualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        log.info("Deletando fórmula de fabricação {}", id);
        facade.deletar(id);
    }

    @GetMapping("/tipos-processo-fabricacao")
    public TipoProcessoFabricacao[] listarTiposProcessoFabricacao() {
        log.info("Listando tipos de processo de fabricação");
        return TipoProcessoFabricacao.values();
    }

    @GetMapping("/processos-difusor-vareta")
    public ProcessoFabricacaoDifusorDeVareta[] listarProcessosDifusorDeVareta() {
        log.info("Listando processos de fabricação para Difusor de Vareta");
        return ProcessoFabricacaoDifusorDeVareta.values();
    }

    @GetMapping("/processos-home-spray")
    public ProcessoFabricacaoHomeSpray[] listarProcessosHomeSpray() {
        log.info("Listando processos de fabricação para Home Spray");
        return ProcessoFabricacaoHomeSpray.values();
    }

    @GetMapping("/processos-vela-aromatica")
    public ProcessoFabricacaoVelaAromatica[] listarProcessosVelaAromatica() {
        log.info("Listando processos de fabricação para Vela Aromática");
        return ProcessoFabricacaoVelaAromatica.values();
    }

    @GetMapping("/processos-agua-lencois")
    public ProcessoFabricacaoAguaDeLencois[] listarProcessosAguaDeLencois() {
        log.info("Listando processos de fabricação para Agua de lencois");
        return ProcessoFabricacaoAguaDeLencois.values();
    }
}
