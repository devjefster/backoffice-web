package com.isadora.backoffice.pessoa.model.mapper;

import com.isadora.backoffice.pessoa.controller.dto.EnderecoDTO;
import com.isadora.backoffice.pessoa.model.entidades.Endereco;
import com.isadora.backoffice.util.model.BaseMapper;

@org.mapstruct.Mapper(componentModel = "spring")
public interface EnderecoMapper extends BaseMapper<Endereco, EnderecoDTO> {
}
