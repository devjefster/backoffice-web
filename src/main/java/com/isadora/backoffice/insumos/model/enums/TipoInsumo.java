package com.isadora.backoffice.insumos.model.enums;

import lombok.Getter;

@Getter
public enum TipoInsumo {

    MATERIA_PRIMA("Matéria Prima", TipoMateriaPrima.class),
    CONSUMIVEIS("Consumíveis", ItensConsumiveisEnum.class),
    EMBALAGEM("Embalagens", EmbalagensEnum.class);

    private final String descricao;
    private final Class<? extends SubTipoInsumoEnum> subTipo;

    TipoInsumo(String descricao, Class<? extends SubTipoInsumoEnum> subTipo) {
        this.descricao = descricao;
        this.subTipo = subTipo;
    }
}

