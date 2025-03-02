package com.devjefster.backoffice.pessoa.controller;

import com.devjefster.backoffice.pessoa.controller.dto.FiltrosPessoaDTO;
import com.devjefster.backoffice.pessoa.controller.dto.PessoaDTO;
import com.devjefster.backoffice.pessoa.facade.PessoaFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/pessoa")
@RequiredArgsConstructor
public class PessoaController {

    private final PessoaFacade facade;

    @GetMapping
    public PagedModel<PessoaDTO> listarComFiltros(FiltrosPessoaDTO filtros, Pageable pageable) {
        return facade.listarComFiltros(filtros, pageable);
    }

    @PostMapping
    public PessoaDTO criar(@RequestBody PessoaDTO dto) {
        return facade.criar(dto);
    }

    @PutMapping("/{id}")
    public PessoaDTO atualizar(@PathVariable Long id, @RequestBody PessoaDTO dto) {
        return facade.atualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        facade.deletar(id);
    }

    @DeleteMapping("/endereco/{enderecoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarEndereco(@PathVariable Long enderecoId) {
        facade.deletarEndereco(enderecoId);
    }

    @PostMapping("/validar-unicidade")
    public ResponseEntity<Map<String, String>> validarUnicidade(@RequestBody PessoaDTO pessoa) {
        return ResponseEntity.ok(facade.validarUnicidade(pessoa));
    }
}

