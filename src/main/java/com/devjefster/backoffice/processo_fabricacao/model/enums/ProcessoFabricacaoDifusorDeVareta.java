package com.devjefster.backoffice.processo_fabricacao.model.enums;

public enum ProcessoFabricacaoDifusorDeVareta {
    MISTURA("Mistura de base para difusor e essência"),
    MACERACAO("Maceração"),
    ENVASE("Envase"),
    ADICAO_DE_VARETAS("Adição de varetas"),
    HOMOGENEIZACAO("Homogeneização dos ingredientes"),
    FILTRAGEM("Filtragem para remover partículas ou impurezas"),
    ROTULAGEM("Rotulagem");

    private final String descricao;

    ProcessoFabricacaoDifusorDeVareta(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
