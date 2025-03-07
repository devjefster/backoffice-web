package com.devjefster.backoffice.estoque.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.devjefster.backoffice.estoque.model.enums.TipoEstoque;
import com.devjefster.backoffice.fabricacao.model.enums.UnidadeMedida;
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
public class LoteEstoqueDTO {
    private Long id;

    @JsonIgnore
    private EstoqueDTO estoque;
    private Long estoqueId;
    private TipoEstoque tipo;

    private UnidadeMedida unidadeMedida;

    private BigDecimal quantidade;

    private LocalDate validade;

    private List<GradeCadastradaDTO> grades; // Ex.: Cor Vermelha, Tamanho Pequeno.

    private BigDecimal custoUnitario;
}
