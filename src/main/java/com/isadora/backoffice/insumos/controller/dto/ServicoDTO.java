package com.isadora.backoffice.insumos.controller.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ServicoDTO extends InsumoDTO {
    private String tipoServico; // Enum: DESIGN, LOGISTICA, etc.
    private String descricaoServico;
}
