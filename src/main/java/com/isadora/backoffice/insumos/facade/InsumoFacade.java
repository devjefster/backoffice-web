package com.isadora.backoffice.insumos.facade;

import com.isadora.backoffice.insumos.controller.dto.InsumoDTO;
import com.isadora.backoffice.insumos.model.entidades.Insumo;
import com.isadora.backoffice.insumos.model.enums.TipoInsumo;
import com.isadora.backoffice.insumos.model.mapper.InsumoMapper;
import com.isadora.backoffice.insumos.service.InsumoService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InsumoFacade {

    private final InsumoMapper insumoMapper;
    private final InsumoService insumoService;


    @Transactional
    public InsumoDTO criar(InsumoDTO dto) {
        Insumo insumo = insumoMapper.toEntity(dto);
        Insumo created = insumoService.criar(insumo);
        return insumoMapper.toDto(created);
    }


    public InsumoDTO atualizar(Long id, InsumoDTO dto) {
        Insumo insumo = insumoMapper.toEntity(dto);
        Insumo updated = insumoService.atualizar(id, insumo);
        return insumoMapper.toDto(updated);
    }

    public InsumoDTO buscarPeloId(Long id) {
        Insumo insumo = insumoService.buscarPeloId(id);
        return insumoMapper.toDto(insumo);
    }


    public Page<InsumoDTO> findAll(
            String textoBusca,
            TipoInsumo tipo,
            Pageable pageable) {
        Page<Insumo> insumos = insumoService.listarComFiltros(textoBusca, tipo, pageable);
        return insumos.map(insumoMapper::toDto);
    }


    public void deletar(Long id) {
        insumoService.deletar(id);
    }
}
