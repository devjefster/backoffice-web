package com.devjefster.backoffice.pessoa.model.mapper;

import com.devjefster.backoffice.pessoa.controller.dto.EnderecoDTO;
import com.devjefster.backoffice.pessoa.model.entidades.Endereco;
import com.devjefster.backoffice.util.model.BaseMapper;

@org.mapstruct.Mapper(componentModel = "spring")
public interface EnderecoMapper extends BaseMapper<Endereco, EnderecoDTO> {
}
