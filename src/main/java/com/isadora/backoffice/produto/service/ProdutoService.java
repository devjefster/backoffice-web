package com.isadora.backoffice.produto.service;

import com.isadora.backoffice.fabricacao.model.repositories.ProdutoRepository;
import com.isadora.backoffice.produto.model.Produto;
import com.isadora.backoffice.produto.model.ProdutoAcabado;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository repository;

//
//    public ProdutoAcabado buscarPorTipo(TipoProduto tipoProduto) {
//        return repository.findByTipo(tipoProduto)
//                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado para o tipo: " + tipoProduto));
//    }

    public Produto buscarPeloId(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));
    }

    public Produto criar(ProdutoAcabado fabricacao) {
        return repository.save(fabricacao);
    }
}
