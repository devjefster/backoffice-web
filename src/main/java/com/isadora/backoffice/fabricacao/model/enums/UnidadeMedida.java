package com.isadora.backoffice.fabricacao.model.enums;

public enum UnidadeMedida {
    LITRO("Litro", "L"),
    MILILITRO("Mililitro", "ml"),
    QUILO("Quilo", "Kg"),
    GRAMA("Grama", "g"),
    METRO("Metro", "m"),
    CENTIMETRO("Centímetro", "cm"),
    UNIDADE("Unidade", "Un");

    private final String descricao;
    private final String simbolo;

    UnidadeMedida(String descricao, String simbolo) {
        this.descricao = descricao;
        this.simbolo = simbolo;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getSimbolo() {
        return simbolo;
    }
}

