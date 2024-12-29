package com.isadora.backoffice.estoque.model.mapper;

import com.isadora.backoffice.estoque.controller.dto.EstoqueDTO;
import com.isadora.backoffice.estoque.model.entidades.Estoque;
import com.isadora.backoffice.util.model.BaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EstoqueMapper extends BaseMapper<Estoque, EstoqueDTO> {
}
