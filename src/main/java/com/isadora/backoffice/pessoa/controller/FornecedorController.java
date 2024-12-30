package com.isadora.backoffice.pessoa.controller;

import com.isadora.backoffice.pessoa.controller.dto.FiltrosPessoaDTO;
import com.isadora.backoffice.pessoa.controller.dto.PessoaDTO;
import com.isadora.backoffice.pessoa.facade.PessoaFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/fornecedores")
@RequiredArgsConstructor
public class FornecedorController {

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

}

