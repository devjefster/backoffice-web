package com.isadora.backoffice.pessoa.facade;

import com.isadora.backoffice.pessoa.controller.dto.FornecedorDTO;
import com.isadora.backoffice.pessoa.model.entidades.Fornecedor;
import com.isadora.backoffice.pessoa.model.mapper.FornecedorMapper;
import com.isadora.backoffice.pessoa.model.specification.FornecedorSpecifications;
import com.isadora.backoffice.pessoa.service.FornecedorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PagedModel;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FornecedorFacade {

    private final FornecedorService service;
    private final FornecedorMapper mapper;

    public PagedModel<FornecedorDTO> listarComFiltros(String nomeFantasia, String cnpj, Pageable pageable) {
        Specification<Fornecedor> spec = Specification.where(FornecedorSpecifications.porNomeFantasia(nomeFantasia))
                .and(FornecedorSpecifications.porCnpj(cnpj));
        return mapper.toDto(service.listarComFiltros(spec, pageable));
    }

    public FornecedorDTO criar(FornecedorDTO dto) {
        Fornecedor fornecedor = mapper.toEntity(dto);
        return mapper.toDto(service.criar(fornecedor));
    }

    public FornecedorDTO atualizar(Long id, FornecedorDTO dto) {
        Fornecedor fornecedor = mapper.toEntity(dto);
        return mapper.toDto(service.atualizar(id, fornecedor));
    }

    public void deletar(Long id) {
        service.deletar(id);
    }
}
