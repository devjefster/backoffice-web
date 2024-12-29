package com.isadora.backoffice.pessoa.controller;

import com.isadora.backoffice.pessoa.controller.dto.FabricanteDTO;
import com.isadora.backoffice.pessoa.facade.FabricanteFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
@RequestMapping("/api/fabricantes")
@RequiredArgsConstructor
public class FabricanteController {

    private final FabricanteFacade facade;

    @GetMapping
    public Page<FabricanteDTO> listarComFiltros(
            @RequestParam(required = false) String nomeFantasia,
            @RequestParam(required = false) String cnpj,
            Pageable pageable) {
        return facade.listarComFiltros(nomeFantasia, cnpj, pageable);
    }

    @PostMapping
    public FabricanteDTO criar(@RequestBody FabricanteDTO dto) {
        return facade.criar(dto);
    }

    @PutMapping("/{id}")
    public FabricanteDTO atualizar(@PathVariable Long id, @RequestBody FabricanteDTO dto) {
        return facade.atualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        facade.deletar(id);
    }
}

