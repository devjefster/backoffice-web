package com.isadora.backoffice.entrada_insumos.controller;

import com.isadora.backoffice.entrada_insumos.controller.dto.EntradaInsumosDTO;
import com.isadora.backoffice.entrada_insumos.facade.EntradaInsumosFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/entrada-insumos")
@RequiredArgsConstructor
@Slf4j
public class EntradaInsumosController {
    private final EntradaInsumosFacade facade;

    @PostMapping
    public ResponseEntity<EntradaInsumosDTO> criarEntrada(@RequestBody EntradaInsumosDTO dto) {
        log.info("Criando entrada de insumos: {}", dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(facade.criarEntrada(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntradaInsumosDTO> buscarPorId(@PathVariable Long id) {
        log.info("Buscando entrada de insumos pelo ID: {}", id);
        EntradaInsumosDTO entrada = facade.buscarPorId(id);
        return ResponseEntity.ok(entrada);
    }


    @GetMapping
    public ResponseEntity<PagedModel<EntradaInsumosDTO>> listar(
            @RequestParam(value = "textoBusca", required = false) String textoBusca,
            @RequestParam(value = "fornecedorId", required = false) Long fornecedorId,
            @RequestParam(value = "dataInicio", required = false) LocalDate dataInicio,
            @RequestParam(value = "dataFim", required = false) LocalDate dataFim,
            Pageable pageable) {
        log.info("Listando entradas de insumos com filtros: textoBusca={}, fornecedorId={}, dataInicio={}, dataFim={}", textoBusca, fornecedorId, dataInicio, dataFim);
        PagedModel<EntradaInsumosDTO> entradas = facade.listar(textoBusca, fornecedorId, dataInicio, dataFim, pageable);
        return ResponseEntity.ok(entradas);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizarEntrada(@PathVariable Long id, @RequestBody EntradaInsumosDTO dto) {
        log.info("Atualizando entrada de insumos ID: {}", id);
        facade.atualizarEntrada(id, dto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEntrada(@PathVariable Long id) {
        log.info("Deletando entrada de insumos ID: {}", id);
        facade.deletarEntrada(id);
        return ResponseEntity.noContent().build();
    }
}
