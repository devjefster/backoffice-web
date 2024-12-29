package com.isadora.backoffice.pessoa.model.mapper;

import com.isadora.backoffice.pessoa.controller.dto.FabricanteDTO;
import com.isadora.backoffice.pessoa.model.entidades.Fabricante;
import com.isadora.backoffice.util.model.BaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FabricanteMapper extends BaseMapper<Fabricante, FabricanteDTO> {
}
