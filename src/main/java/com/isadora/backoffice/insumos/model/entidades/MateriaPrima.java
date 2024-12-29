package com.isadora.backoffice.insumos.model.entidades;

import com.isadora.backoffice.insumos.model.enums.TipoMateriaPrima;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@DiscriminatorValue("MATERIA_PRIMA")
public class MateriaPrima extends Insumo {

    @Enumerated(EnumType.STRING)
    private TipoMateriaPrima tipoMateriaPrima;

    @Column
    private String especificacoesTecnicas;
}

