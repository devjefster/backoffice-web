package com.devjefster.backoffice.entrada_insumos.model.mapper;

import com.devjefster.backoffice.entrada_insumos.controller.dto.LoteEntradaDTO;
import com.devjefster.backoffice.entrada_insumos.model.entidades.LoteEntrada;
import com.devjefster.backoffice.util.model.BaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LoteEntradaMapper extends BaseMapper<LoteEntrada, LoteEntradaDTO> {
}
