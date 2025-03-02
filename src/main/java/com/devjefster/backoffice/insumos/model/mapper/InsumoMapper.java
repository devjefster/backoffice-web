package com.devjefster.backoffice.insumos.model.mapper;

import com.devjefster.backoffice.insumos.controller.dto.InsumoDTO;
import com.devjefster.backoffice.insumos.model.entidades.Insumo;
import com.devjefster.backoffice.util.model.BaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InsumoMapper extends BaseMapper<Insumo, InsumoDTO> {


}
