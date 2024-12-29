package com.isadora.backoffice.insumos.model.enums;

public enum ServicosEnum implements SubTipoInsumoEnum {
    DESIGN_GRAFICO("Design gráfico para rótulos"),
    LOGISTICA("Serviço de logística e transporte"),
    IMPRESSAO("Impressão de rótulos e etiquetas"),
    DESENVOLVIMENTO("Desenvolvimento de fragrâncias");

    private final String descricao;

    private ServicosEnum(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String getDescricacao() {
        return descricao;
    }
}