package com.devjefster.backoffice.entrada_insumos.model.mapper;

import com.devjefster.backoffice.entrada_insumos.controller.dto.EntradaInsumosDTO;
import com.devjefster.backoffice.entrada_insumos.model.entidades.EntradaInsumos;
import com.devjefster.backoffice.util.model.BaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EntradaInsumosMapper extends BaseMapper<EntradaInsumos, EntradaInsumosDTO> {
}
