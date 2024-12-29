package com.isadora.backoffice.estoque.model.mapper;

import com.isadora.backoffice.estoque.controller.dto.MovimentacaoEstoqueDTO;
import com.isadora.backoffice.estoque.model.entidades.MovimentacaoEstoque;
import com.isadora.backoffice.util.model.BaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MovimentacaoEstoqueMapper extends BaseMapper<MovimentacaoEstoque, MovimentacaoEstoqueDTO> {
}
