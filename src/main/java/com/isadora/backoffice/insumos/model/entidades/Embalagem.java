package com.isadora.backoffice.insumos.model.entidades;

import com.isadora.backoffice.insumos.model.enums.EmbalagensEnum;
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
@DiscriminatorValue("EMBALAGEM")
public class Embalagem extends Insumo {

    @Enumerated(EnumType.STRING)
    private EmbalagensEnum tipoEmbalagem;

    @Column
    private String dimensoes; // Exemplo: 10x15x20 cm.

    @Column
    private String material; // Exemplo: vidro, plástico, papel.
}
