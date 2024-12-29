package com.isadora.backoffice.insumos.model.enums;

public enum OutroEnum implements SubTipoInsumoEnum {
    INSTRUMENTOS_LABORATORIO("Instrumentos de laboratório"),
    FERRAMENTAS_PRODUCAO("Ferramentas de produção"),
    TESTE_QUALIDADE("Materiais para teste de qualidade");
    private final String descricao;

    private OutroEnum(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String getDescricacao() {
        return descricao;
    }
}