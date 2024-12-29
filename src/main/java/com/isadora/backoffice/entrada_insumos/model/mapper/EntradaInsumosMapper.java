package com.isadora.backoffice.entrada_insumos.model.mapper;

import com.isadora.backoffice.entrada_insumos.controller.dto.EntradaInsumosDTO;
import com.isadora.backoffice.entrada_insumos.model.entidades.EntradaInsumos;
import com.isadora.backoffice.util.model.BaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EntradaInsumosMapper extends BaseMapper<EntradaInsumos, EntradaInsumosDTO> {
}
