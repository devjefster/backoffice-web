package com.devjefster.backoffice.insumos.model.enums;

public enum EmbalagensEnum implements SubTipoInsumoEnum {

    EMBALAGEM("Embalagens"),
    ROTULO("Rótulos"),
    CAIXA("Caixas de transporte"),
    PACOTE("Caixas de transporte"),
    FITA("Fita");

    private final String descricao;

    EmbalagensEnum(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String getDescricacao() {
        return descricao;
    }
}
