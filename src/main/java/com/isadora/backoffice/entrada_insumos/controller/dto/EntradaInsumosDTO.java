package com.isadora.backoffice.entrada_insumos.controller.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EntradaInsumosDTO {
    private Long id;
    @NotNull
    private FornecedorDTO fornecedor;

    private LocalDate dataEntrada = LocalDate.now();
    private BigDecimal custoFrete = BigDecimal.ZERO;
    private BigDecimal custoOutros = BigDecimal.ZERO;
    private List<EntradaInsumoItemDTO> itens; // Lista de itens associados à entrada.
}
