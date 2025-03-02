package com.devjefster.backoffice.fabricacao.controller.dto;

import com.devjefster.backoffice.fabricacao.model.enums.StatusFabricacao;

public record OrdemFabricacaoFiltros(String textoBusca, StatusFabricacao statusFabricacao, Boolean isRascunho) {


}
