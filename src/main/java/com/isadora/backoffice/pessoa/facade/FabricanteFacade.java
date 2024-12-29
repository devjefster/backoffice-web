package com.isadora.backoffice.pessoa.facade;

import com.isadora.backoffice.pessoa.controller.dto.FabricanteDTO;
import com.isadora.backoffice.pessoa.model.entidades.Fabricante;
import com.isadora.backoffice.pessoa.model.mapper.FabricanteMapper;
import com.isadora.backoffice.pessoa.model.specification.FabricanteSpecifications;
import com.isadora.backoffice.pessoa.service.FabricanteService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FabricanteFacade {

    private final FabricanteService fabricanteService;
    private final FabricanteMapper fabricanteMapper;

    public Page<FabricanteDTO> listarComFiltros(String nomeFantasia, String cnpj, Pageable pageable) {
        Specification<Fabricante> spec = Specification.where(FabricanteSpecifications.porNomeFantasia(nomeFantasia))
                .and(FabricanteSpecifications.porCnpj(cnpj));
        return fabricanteService.listarComFiltros(spec, pageable).map(fabricanteMapper::toDto);
    }

    public FabricanteDTO criar(FabricanteDTO dto) {
        Fabricante fabricante = fabricanteMapper.toEntity(dto);
        return fabricanteMapper.toDto(fabricanteService.criar(fabricante));
    }

    public FabricanteDTO atualizar(Long id, FabricanteDTO dto) {
        Fabricante fabricanteAtualizado = fabricanteMapper.toEntity(dto);
        return fabricanteMapper.toDto(fabricanteService.atualizar(id, fabricanteAtualizado));
    }

    public void deletar(Long id) {
        fabricanteService.deletar(id);
    }
}
