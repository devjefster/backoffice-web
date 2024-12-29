package com.isadora.backoffice.entrada_insumos.model.mapper;

import com.isadora.backoffice.entrada_insumos.controller.dto.LoteEntradaDTO;
import com.isadora.backoffice.entrada_insumos.model.entidades.LoteEntrada;
import com.isadora.backoffice.util.model.BaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LoteEntradaMapper extends BaseMapper<LoteEntrada, LoteEntradaDTO> {
}
