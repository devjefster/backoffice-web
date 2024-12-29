package com.isadora.backoffice.insumos.model.enums;

public enum TipoMateriaPrima implements SubTipoInsumoEnum {

    ESSENCIA("Essência aromática"),
    ALCOOL("Álcool"),
    CERA("Cera"),
    AGUA("Água desmineralizada"),
    CORANTE("Corante"),
    RENEX("Renex - tensoativo não iônico"),
    OLEO_ESSENCIAL("Óleo essencial"),
    BASE_SABONETE("Base para sabonete"),
    AGENTE_FIXADOR("Agente fixador para perfumes"),
    ACIDO("Ácido para neutralização"),

    BASE_DIFUSOR("Base para difusor"); // Base específica para difusores

    private final String descricao;

    TipoMateriaPrima(String descricao) {
        this.descricao = descricao;
    }


    @Override
    public String getDescricacao() {
        return descricao;
    }
}
