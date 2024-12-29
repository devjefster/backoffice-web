package com.isadora.backoffice.insumos.service;

import com.isadora.backoffice.insumos.model.entidades.Insumo;
import com.isadora.backoffice.insumos.model.enums.TipoInsumo;
import com.isadora.backoffice.insumos.model.repository.InsumoRepository;
import com.isadora.backoffice.insumos.model.specifications.InsumoSpecifications;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class InsumoService {
    private final InsumoRepository repository;

    public Page<Insumo> listarComFiltros(String textoBusca, TipoInsumo tipo, Pageable pageable) {
        Specification<Insumo> filtros = InsumoSpecifications.filtrosDinamicos(textoBusca, tipo);
        return repository.findAll(filtros, pageable);
    }

    public Insumo buscarPeloId(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Insumo não encontrada"));
    }

    public Insumo criar(Insumo insumo) {
        return repository.save(insumo);
    }

    public Insumo atualizar(Long id, Insumo insumoAtualizado) {
        Insumo insumo = buscarPeloId(id);
        BeanUtils.copyProperties(insumo, insumoAtualizado, "id");
        return repository.save(insumoAtualizado);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
