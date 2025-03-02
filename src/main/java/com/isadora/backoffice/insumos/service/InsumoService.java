package com.isadora.backoffice.insumos.service;

import com.isadora.backoffice.insumos.model.entidades.Insumo;
import com.isadora.backoffice.insumos.model.enums.TipoInsumo;
import com.isadora.backoffice.insumos.model.repository.InsumoRepository;
import com.isadora.backoffice.insumos.model.specifications.InsumoSpecifications;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
        insumo.setId(null);
        return repository.save(insumo);
    }

    public Insumo atualizar(Long id, Insumo insumoAtualizado) {
        Insumo insumo = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Insumo não encontrado com id " + id));

        // Atualiza os campos necessários, sem sobrescrever timestamps ou versão
        insumo.setNome(insumoAtualizado.getNome());
        insumo.setDescricao(insumoAtualizado.getDescricao());
        insumo.setCodigoBarras(insumoAtualizado.getCodigoBarras());
        insumo.setQrCode(insumoAtualizado.getQrCode());
        insumo.setUnidadeMedida(insumoAtualizado.getUnidadeMedida());
        insumo.setTipo(insumoAtualizado.getTipo());
        insumo.setTipoMateriaPrima(insumoAtualizado.getTipoMateriaPrima());
        insumo.setTipoConsumivel(insumoAtualizado.getTipoConsumivel());
        insumo.setTipoEmbalagem(insumoAtualizado.getTipoEmbalagem());
        insumo.setEspecificacoesTecnicas(insumoAtualizado.getEspecificacoesTecnicas());
        insumo.setAplicacao(insumoAtualizado.getAplicacao());
        insumo.setDimensoes(insumoAtualizado.getDimensoes());
        insumo.setMaterial(insumoAtualizado.getMaterial());

        return repository.save(insumo);
    }


    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
