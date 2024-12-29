package com.isadora.backoffice.pessoa.controller;

import com.isadora.backoffice.pessoa.controller.dto.FornecedorDTO;
import com.isadora.backoffice.pessoa.facade.FabricanteFacade;
import com.isadora.backoffice.pessoa.facade.FornecedorFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/fornecedores")
@RequiredArgsConstructor
public class FornecedorController {

    private final FornecedorFacade facade;

    @GetMapping
    public PagedModel<FornecedorDTO> listarComFiltros(
            @RequestParam(required = false) String nomeFantasia,
            @RequestParam(required = false) String cnpj,
            Pageable pageable) {
        return facade.listarComFiltros(nomeFantasia, cnpj, pageable);
    }

    @PostMapping
    public FornecedorDTO criar(@RequestBody FornecedorDTO dto) {
        return facade.criar(dto);
    }

    @PutMapping("/{id}")
    public FornecedorDTO atualizar(@PathVariable Long id, @RequestBody FornecedorDTO dto) {
        return facade.atualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        facade.deletar(id);
    }
}

