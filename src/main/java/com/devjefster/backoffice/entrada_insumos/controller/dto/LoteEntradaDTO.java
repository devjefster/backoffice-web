package com.devjefster.backoffice.entrada_insumos.controller.dto;

import com.devjefster.backoffice.insumos.controller.dto.GradeCadastradaDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoteEntradaDTO {
    private Long id;
    private BigDecimal quantidadeConvertida;
    private String unidadeMedida; // Enum UnidadeMedida.
    private LocalDate validade;
    private List<GradeCadastradaDTO> grades; // Grades associadas ao lote.
}
