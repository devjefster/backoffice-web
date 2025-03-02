package com.devjefster.backoffice.fabricacao.model.mapper;

import com.devjefster.backoffice.fabricacao.controller.dto.OrdemFabricacaoDTO;
import com.devjefster.backoffice.fabricacao.model.entidades.OrdemFabricacao;
import com.devjefster.backoffice.util.model.BaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrdemFabricacaoMapper extends BaseMapper<OrdemFabricacao, OrdemFabricacaoDTO> {
}
