package com.isadora.backoffice.estoque.controller;

import com.isadora.backoffice.estoque.controller.dto.EstoqueDTO;
import com.isadora.backoffice.estoque.controller.dto.FiltrosEstoque;
import com.isadora.backoffice.estoque.controller.dto.LoteEstoqueDTO;
import com.isadora.backoffice.estoque.controller.dto.MovimentacaoEstoqueDTO;
import com.isadora.backoffice.estoque.model.entidades.LoteEstoque;
import com.isadora.backoffice.estoque.model.enums.TipoMovimentacaoEstoque;
import com.isadora.backoffice.estoque.model.mapper.LoteEstoqueMapper;
import com.isadora.backoffice.estoque.service.EstoqueService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/estoques")
@RequiredArgsConstructor
@Slf4j
public class EstoqueController {

    private final EstoqueService estoqueService;
    private final LoteEstoqueMapper loteMapper;

    @GetMapping
    public ResponseEntity<PagedModel<EstoqueDTO>> listarEstoques(
            FiltrosEstoque filtros,
            Pageable pageable) {

        log.info("Listando estoques com filtros: {}", filtros);

        PagedModel<EstoqueDTO> estoques = estoqueService.listarEstoques(filtros, pageable);
        return ResponseEntity.ok(estoques);
    }

    @GetMapping("/lotes")
    public ResponseEntity<List<LoteEstoqueDTO>> consultarLotes(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate validadeMinima,
            @RequestParam(required = false) BigDecimal quantidadeMinima) {
        List<LoteEstoque> lotes = estoqueService.consultarLotes(validadeMinima, quantidadeMinima);
        return ResponseEntity.ok(loteMapper.toDto(lotes));
    }


    @GetMapping("/movimentacoes")
    public ResponseEntity<PagedModel<MovimentacaoEstoqueDTO>> listarMovimentacoesEstoques(@RequestParam Long estoqueId, @RequestParam(required = false) TipoMovimentacaoEstoque tipo,
                                                                                    Pageable pageable) {

        log.info("Listando movimentacoes estoque: {}", pageable);

        PagedModel<MovimentacaoEstoqueDTO> estoques = estoqueService.listarMovimentacoesEstoques(estoqueId, tipo, pageable);
        return ResponseEntity.ok(estoques);
    }


}

