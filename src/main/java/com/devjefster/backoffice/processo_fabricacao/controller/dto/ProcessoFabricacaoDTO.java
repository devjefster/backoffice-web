package com.devjefster.backoffice.processo_fabricacao.controller.dto;

import com.devjefster.backoffice.insumos.controller.dto.InsumoDTO;
import com.devjefster.backoffice.processo_fabricacao.model.enums.TipoProcessoFabricacao;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProcessoFabricacaoDTO {

    private Long id;
    private InsumoDTO insumo;
    @NotNull(message = "O ID do insumo é obrigatório.")
    private Long insumoId;

    @DecimalMin(value = "0.1", message = "A porcentagem deve ser maior que zero.")
    @DecimalMax(value = "100.0", message = "A porcentagem não pode ser maior que 100.")
    private double porcentagem;
    private TipoProcessoFabricacao tipoProcesso;
}
