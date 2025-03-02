package com.devjefster.backoffice.fabricacao.controller.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ProdutoAcabadoDTO extends ProdutoDTO {

    private String tipoProdutoAcabado;
}
