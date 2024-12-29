package com.isadora.backoffice.pessoa.service;

import com.isadora.backoffice.pessoa.model.entidades.Fornecedor;
import com.isadora.backoffice.pessoa.model.repositories.FornecedorRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FornecedorService {

    private final FornecedorRepository fornecedorRepository;

    public Page<Fornecedor> listarComFiltros(Specification<Fornecedor> spec, Pageable pageable) {
        return fornecedorRepository.findAll(spec, pageable);
    }

    public Fornecedor buscarPeloId(Long id) {
        return fornecedorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Fornecedor não encontrado"));
    }

    public Fornecedor criar(Fornecedor fabricante) {
        return fornecedorRepository.save(fabricante);
    }

    public Fornecedor atualizar(Long id, Fornecedor fabricanteAtualizado) {
        Fornecedor fornecedor = buscarPeloId(id);
        fornecedor.setNomeFantasia(fabricanteAtualizado.getNomeFantasia());
        fornecedor.setCnpj(fabricanteAtualizado.getCnpj());
        fornecedor.setEmail(fabricanteAtualizado.getEmail());
        fornecedor.setEndereco(fabricanteAtualizado.getEndereco());
        return fornecedorRepository.save(fornecedor);
    }

    public void deletar(Long id) {
        fornecedorRepository.deleteById(id);
    }
}
