package com.isadora.backoffice.pessoa.model.mapper;

import com.isadora.backoffice.pessoa.controller.dto.PessoaDTO;
import com.isadora.backoffice.pessoa.model.entidades.Pessoa;
import com.isadora.backoffice.util.model.BaseMapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@org.mapstruct.Mapper(componentModel = "spring")
public interface PessoaMapper extends BaseMapper<Pessoa, PessoaDTO> {


    @Mapping(target = "id",  ignore = true)
    @Mapping(target = "enderecos",  ignore = true)
    void update(@MappingTarget Pessoa pessoa, PessoaDTO dto);

}
