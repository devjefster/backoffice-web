package com.isadora.backoffice.processo_fabricacao.model.enums;

public enum ProcessoFabricacaoHomeSpray {

    MISTURA("Mistura de álcool essência e água desmineralizada"),
    MACERACAO("Maceração"),
    ENVASE("envase"),
    HOMOGENEIZACAO("Homogeneização dos ingredientes"),
    FILTRAGEM("Filtragem para remover partículas ou impurezas"),
    ROTULAGEM("rotulagem");


    private final String descricao;

    ProcessoFabricacaoHomeSpray(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
