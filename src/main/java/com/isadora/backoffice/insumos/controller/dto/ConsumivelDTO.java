package com.isadora.backoffice.insumos.controller.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ConsumivelDTO extends InsumoDTO {
    private String tipoConsumivel; // Enum: PAVIO, VARETAS, etc.
    private String aplicacao;
}
