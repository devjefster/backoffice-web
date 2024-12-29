package com.isadora.backoffice.pessoa.model.mapper;

import com.isadora.backoffice.pessoa.controller.dto.FornecedorDTO;
import com.isadora.backoffice.pessoa.model.entidades.Fornecedor;
import com.isadora.backoffice.util.model.BaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FornecedorMapper extends BaseMapper<Fornecedor, FornecedorDTO> {
}
