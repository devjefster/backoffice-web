package com.isadora.backoffice.insumos.model.mapper;

import com.isadora.backoffice.insumos.controller.dto.InsumoDTO;
import com.isadora.backoffice.insumos.model.entidades.Insumo;
import com.isadora.backoffice.util.model.BaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InsumoMapper extends BaseMapper<Insumo, InsumoDTO> {


}
