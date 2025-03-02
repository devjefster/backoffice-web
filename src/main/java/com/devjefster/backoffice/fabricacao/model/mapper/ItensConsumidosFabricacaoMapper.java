package com.devjefster.backoffice.fabricacao.model.mapper;

import com.devjefster.backoffice.fabricacao.controller.dto.ItensConsumidosFabricacaoDTO;
import com.devjefster.backoffice.fabricacao.model.entidades.ItensConsumidosFabricacao;
import com.devjefster.backoffice.util.model.BaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ItensConsumidosFabricacaoMapper extends BaseMapper<ItensConsumidosFabricacao, ItensConsumidosFabricacaoDTO> {
}
