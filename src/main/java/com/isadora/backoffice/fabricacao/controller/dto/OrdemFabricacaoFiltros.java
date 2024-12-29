package com.isadora.backoffice.fabricacao.controller.dto;

import com.isadora.backoffice.fabricacao.model.enums.StatusFabricacao;

public record OrdemFabricacaoFiltros(String textoBusca, StatusFabricacao statusFabricacao, Boolean isRascunho) {


}
