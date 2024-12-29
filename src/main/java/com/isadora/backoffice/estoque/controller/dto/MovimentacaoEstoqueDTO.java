package com.isadora.backoffice.estoque.controller.dto;

import com.isadora.backoffice.estoque.model.enums.TipoMovimentacaoEstoque;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class MovimentacaoEstoqueDTO {
    private Long id;

    private LoteEstoqueDTO loteEstoque;

    private BigDecimal quantidade;

    private TipoMovimentacaoEstoque tipo;
    private LocalDate dataMovimentacao;
}
