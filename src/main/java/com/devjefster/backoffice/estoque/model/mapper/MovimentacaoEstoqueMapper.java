package com.devjefster.backoffice.estoque.model.mapper;

import com.devjefster.backoffice.estoque.controller.dto.MovimentacaoEstoqueDTO;
import com.devjefster.backoffice.estoque.model.entidades.MovimentacaoEstoque;
import com.devjefster.backoffice.util.model.BaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MovimentacaoEstoqueMapper extends BaseMapper<MovimentacaoEstoque, MovimentacaoEstoqueDTO> {
}
