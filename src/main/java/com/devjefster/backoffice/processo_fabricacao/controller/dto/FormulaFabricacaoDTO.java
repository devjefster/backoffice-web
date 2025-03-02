package com.devjefster.backoffice.processo_fabricacao.controller.dto;

import com.devjefster.backoffice.fabricacao.controller.dto.ProdutoDTO;
import lombok.Data;

import java.util.List;

@Data
public class FormulaFabricacaoDTO {

    private Long id;
    private ProdutoDTO produto;
    private List<ProcessoFabricacaoDTO> processosFabricacao;
}
