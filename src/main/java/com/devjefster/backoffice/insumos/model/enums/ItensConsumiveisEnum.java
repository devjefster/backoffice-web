package com.devjefster.backoffice.insumos.model.enums;

public enum ItensConsumiveisEnum implements SubTipoInsumoEnum {

    PAVIO("Pavios para velas"),
    VALVULA("Válvulas spray"),
    VARETAS_MADEIRA("Varetas de isopor"),
    VARETAS_ISOPOR("Varetas de isopor"),
    VARETAS("Varetas (diversos materiais)"),
    VALVULA_SPRAY("Válvula spray"),
    ADESIVO("Adesivo industrial"),
    PAVIO_ALGODAO("Pavio de algodão"),
    PAVIO_MADEIRA("Pavio de madeira");


    private final String descricao;

    ItensConsumiveisEnum(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String getDescricacao() {
        return descricao;
    }
}
