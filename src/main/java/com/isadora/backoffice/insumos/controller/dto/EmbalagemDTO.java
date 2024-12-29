package com.isadora.backoffice.insumos.controller.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class EmbalagemDTO extends InsumoDTO {
    private String tipoEmbalagem; // Enum: FRASCO_VIDRO, CAIXA, etc.
    private String dimensoes;
    private String material;
}
