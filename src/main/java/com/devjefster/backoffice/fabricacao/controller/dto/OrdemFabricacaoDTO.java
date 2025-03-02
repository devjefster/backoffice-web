package com.devjefster.backoffice.fabricacao.controller.dto;

import com.devjefster.backoffice.fabricacao.model.enums.StatusFabricacao;
import com.devjefster.backoffice.insumos.controller.dto.GradeCadastradaDTO;
import com.devjefster.backoffice.processo_fabricacao.controller.dto.FormulaFabricacaoDTO;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrdemFabricacaoDTO {

    private Long id;
    private ProdutoDTO produto;

    private List<ItensConsumidosFabricacaoDTO> itensConsumidos;
    private List<GradeCadastradaDTO> grades;
    private FormulaFabricacaoDTO formulaFabricacao; // Fórmula utilizada para a fabricação.

    private BigDecimal quantidadeProduzida; // Quantidade a ser fabricada.
    private LocalDate validade;
    private String observacoes;

    private StatusFabricacao status; // Status da fabricação (e.g., Planejado, Em Produção, Finalizado).
    private LocalDateTime dataNotificacao; // Caso o produto precise de tempo de maceracao


}
