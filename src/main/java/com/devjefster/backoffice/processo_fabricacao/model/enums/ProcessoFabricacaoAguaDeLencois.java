package com.devjefster.backoffice.processo_fabricacao.model.enums;

public enum ProcessoFabricacaoAguaDeLencois {

    MISTURA("Mistura de essência suave, água desmineralizada e conservantes"),
    MACERACAO("Maceração"),
    ENVASE("Envase"),
    HOMOGENEIZACAO("Homogeneização dos ingredientes"),
    FILTRAGEM("Filtragem para remover partículas ou impurezas"),
    ROTULAGEM("Rotulagem");

    private final String descricao;

    ProcessoFabricacaoAguaDeLencois(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
