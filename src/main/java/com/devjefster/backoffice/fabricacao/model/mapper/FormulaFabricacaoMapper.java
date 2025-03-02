package com.devjefster.backoffice.fabricacao.model.mapper;

import com.devjefster.backoffice.processo_fabricacao.controller.dto.FormulaFabricacaoDTO;
import com.devjefster.backoffice.processo_fabricacao.model.entidades.FormulaFabricacao;
import com.devjefster.backoffice.util.model.BaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FormulaFabricacaoMapper extends BaseMapper<FormulaFabricacao, FormulaFabricacaoDTO> {
}
