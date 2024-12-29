package com.isadora.backoffice.insumos.controller.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MateriaPrimaDTO extends InsumoDTO {
    private String tipoMateriaPrima; // Enum: ESSENCIA, ALCOOL, etc.
    private String especificacoesTecnicas;
}
