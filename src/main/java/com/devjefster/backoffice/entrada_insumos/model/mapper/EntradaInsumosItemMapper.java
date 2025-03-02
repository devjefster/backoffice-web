package com.devjefster.backoffice.entrada_insumos.model.mapper;

import com.devjefster.backoffice.entrada_insumos.controller.dto.EntradaInsumoItemDTO;
import com.devjefster.backoffice.entrada_insumos.model.entidades.EntradaInsumoItem;
import com.devjefster.backoffice.util.model.BaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EntradaInsumosItemMapper extends BaseMapper<EntradaInsumoItem, EntradaInsumoItemDTO> {
}
