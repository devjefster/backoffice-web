package com.isadora.backoffice.estoque.controller.dto;

import com.isadora.backoffice.fabricacao.controller.dto.ProdutoDTO;
import com.isadora.backoffice.fabricacao.model.enums.UnidadeMedida;
import com.isadora.backoffice.insumos.controller.dto.GradeCadastradaDTO;
import com.isadora.backoffice.insumos.controller.dto.InsumoDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EstoqueDTO {

    private Long id;
    private InsumoDTO insumo;
    private ProdutoDTO produto;

    private UnidadeMedida unidadeMedida;

    private BigDecimal quantidade; // Quantidade total disponível.

    private List<GradeCadastradaDTO> grades;
    private List<LoteEstoqueDTO> lotes;
}
