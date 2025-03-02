package com.devjefster.backoffice.estoque.model.mapper;

import com.devjefster.backoffice.estoque.controller.dto.EstoqueDTO;
import com.devjefster.backoffice.estoque.model.entidades.Estoque;
import com.devjefster.backoffice.util.model.BaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EstoqueMapper extends BaseMapper<Estoque, EstoqueDTO> {
}
