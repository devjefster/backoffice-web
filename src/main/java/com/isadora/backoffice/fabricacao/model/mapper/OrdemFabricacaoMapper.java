package com.isadora.backoffice.fabricacao.model.mapper;

import com.isadora.backoffice.fabricacao.controller.dto.OrdemFabricacaoDTO;
import com.isadora.backoffice.fabricacao.model.entidades.OrdemFabricacao;
import com.isadora.backoffice.util.model.BaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrdemFabricacaoMapper extends BaseMapper<OrdemFabricacao, OrdemFabricacaoDTO> {
}
