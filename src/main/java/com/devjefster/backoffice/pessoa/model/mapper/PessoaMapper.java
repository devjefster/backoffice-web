package com.devjefster.backoffice.pessoa.model.mapper;

import com.devjefster.backoffice.pessoa.controller.dto.PessoaDTO;
import com.devjefster.backoffice.pessoa.model.entidades.Pessoa;
import com.devjefster.backoffice.util.model.BaseMapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@org.mapstruct.Mapper(componentModel = "spring")
public interface PessoaMapper extends BaseMapper<Pessoa, PessoaDTO> {


    @Mapping(target = "id",  ignore = true)
    @Mapping(target = "enderecos",  ignore = true)
    void update(@MappingTarget Pessoa pessoa, PessoaDTO dto);

}
