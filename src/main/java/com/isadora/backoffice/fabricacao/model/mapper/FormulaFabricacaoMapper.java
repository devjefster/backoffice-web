package com.isadora.backoffice.fabricacao.model.mapper;

import com.isadora.backoffice.processo_fabricacao.controller.dto.FormulaFabricacaoDTO;
import com.isadora.backoffice.processo_fabricacao.model.entidades.FormulaFabricacao;
import com.isadora.backoffice.util.model.BaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FormulaFabricacaoMapper extends BaseMapper<FormulaFabricacao, FormulaFabricacaoDTO> {
}
