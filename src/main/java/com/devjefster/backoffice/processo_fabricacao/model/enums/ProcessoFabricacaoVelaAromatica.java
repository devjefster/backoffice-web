package com.devjefster.backoffice.processo_fabricacao.model.enums;

public enum ProcessoFabricacaoVelaAromatica {
    PREPARACAO("Separação dos ingredientes necessários"),
    DERRETIMENTO("Derretimento da cera"),
    ADICAO_ESSENCIA("Adição de essência e corante"),
    MOLDAGEM("Despejo da mistura nos moldes com pavio"),
    RESFRIAMENTO("Resfriamento até solidificação"),
    ACABAMENTO("Ajuste e limpeza da vela"),
    ROTULAGEM("Adição de rótulos e/ou decoração");

    private final String descricao;

    ProcessoFabricacaoVelaAromatica(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
