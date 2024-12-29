package com.isadora.backoffice.fabricacao.model.mapper;

import com.isadora.backoffice.fabricacao.controller.dto.ItensConsumidosFabricacaoDTO;
import com.isadora.backoffice.fabricacao.model.entidades.ItensConsumidosFabricacao;
import com.isadora.backoffice.util.model.BaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ItensConsumidosFabricacaoMapper extends BaseMapper<ItensConsumidosFabricacao, ItensConsumidosFabricacaoDTO> {
}
