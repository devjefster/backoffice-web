package com.devjefster.backoffice.fabricacao.controller;

import com.devjefster.backoffice.fabricacao.controller.dto.OrdemFabricacaoDTO;
import com.devjefster.backoffice.fabricacao.controller.dto.OrdemFabricacaoFiltros;
import com.devjefster.backoffice.fabricacao.facade.OrdemFabricacaoFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ordem-fabricacao")
@RequiredArgsConstructor
@Slf4j
public class OrdemFabricacaoController {

    private final OrdemFabricacaoFacade facade;

    @GetMapping
    public ResponseEntity<PagedModel<OrdemFabricacaoDTO>> listarOrdens(OrdemFabricacaoFiltros fabricacaoFiltros,Pageable pageable) {
        log.info("Listando ordens de fabricação");
        PagedModel<OrdemFabricacaoDTO> ordens = facade.listarOrdens(fabricacaoFiltros,pageable);
        return ResponseEntity.ok(ordens);
    }

    /**
     * Buscar uma ordem de fabricação pelo ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<OrdemFabricacaoDTO> buscarPorId(@PathVariable Long id) {
        log.info("Buscando ordem de fabricação com ID: {}", id);
        OrdemFabricacaoDTO ordem = facade.buscarOrdemPorId(id);
        return ResponseEntity.ok(ordem);
    }

    /**
     * Criar uma nova ordem de fabricação.
     */
    @PostMapping
    public ResponseEntity<OrdemFabricacaoDTO> criarOrdem(@RequestBody OrdemFabricacaoDTO dto) {
        log.info("Criando nova ordem de fabricação: {}", dto);
        OrdemFabricacaoDTO novaOrdem = facade.criarOrdemFabricacao(dto);
        return ResponseEntity.ok(novaOrdem);
    }

    @PutMapping("/{id}/finalizar")
    public OrdemFabricacaoDTO finalizarOrdem(@PathVariable Long id) {
        log.info("Finalizando ordem de fabricação com ID: {}", id);
        return facade.finalizarOrdemFabricacao(id);
    }

    @PutMapping("/{id}")
    public OrdemFabricacaoDTO atualizarOrdem(@PathVariable Long id, @RequestBody OrdemFabricacaoDTO dto) {
        log.info("Atualizando ordem de fabricação com ID: {}", id);
        return facade.atualizarOrdemFabricacao(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarOrdem(@PathVariable Long id) {
        log.info("Deletando ordem de fabricação com ID: {}", id);
        facade.deletarOrdemFabricacao(id);
        return ResponseEntity.noContent().build();
    }

}
