package com.isadora.backoffice.pessoa.service;

import com.isadora.backoffice.pessoa.model.entidades.Fabricante;
import com.isadora.backoffice.pessoa.model.repositories.FabricanteRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FabricanteService {

    private final FabricanteRepository fabricanteRepository;

    public Page<Fabricante> listarComFiltros(Specification<Fabricante> spec, Pageable pageable) {
        return fabricanteRepository.findAll(spec, pageable);
    }

    public Fabricante buscarPeloId(Long id) {
        return fabricanteRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Fabricante não encontrado"));
    }

    public Fabricante criar(Fabricante fabricante) {
        return fabricanteRepository.save(fabricante);
    }

    public Fabricante atualizar(Long id, Fabricante fabricanteAtualizado) {
        Fabricante fabricante = buscarPeloId(id);
        fabricante.setNomeFantasia(fabricanteAtualizado.getNomeFantasia());
        fabricante.setCnpj(fabricanteAtualizado.getCnpj());
        fabricante.setEmail(fabricanteAtualizado.getEmail());
        fabricante.setEndereco(fabricanteAtualizado.getEndereco());
        return fabricanteRepository.save(fabricante);
    }

    public void deletar(Long id) {
        fabricanteRepository.deleteById(id);
    }
}
