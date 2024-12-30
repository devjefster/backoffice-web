package com.isadora.backoffice.entrada_insumos.controller.dto;

import com.isadora.backoffice.insumos.controller.dto.InsumoDTO;
import com.isadora.backoffice.pessoa.controller.dto.PessoaDTO;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EntradaInsumoItemDTO {
    private Long id;

    @NotNull
    private InsumoDTO insumo;
    private PessoaDTO fabricante;
    @NotNull
    private BigDecimal quantidade;
    @NotNull
    private String unidadeMedidaEntrada;
    @NotNull// Enum UnidadeMedida.
    private BigDecimal precoUnitario;

    private LocalDate validade;
    private BigDecimal custoTotal;
    private LoteEntradaDTO lote; // Informações do lote gerado.
}

