package com.devjefster.backoffice.fabricacao.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.devjefster.backoffice.insumos.controller.dto.InsumoDTO;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@ToString
public class ItensConsumidosFabricacaoDTO {

    private Long id;

    @JsonIgnore
    private OrdemFabricacaoDTO ordemFabricacao;


    private ProdutoAcabadoDTO produtoAcabado;
    private Long produtoAcabadoId;

    private InsumoDTO insumo;
    private Long insumoId;


    private BigDecimal quantidade;
}
