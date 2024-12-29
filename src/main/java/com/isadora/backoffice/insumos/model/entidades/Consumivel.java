package com.isadora.backoffice.insumos.model.entidades;

import com.isadora.backoffice.insumos.model.enums.ItensConsumiveisEnum;
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
@DiscriminatorValue("CONSUMIVEL")
public class Consumivel extends Insumo {

    @Enumerated(EnumType.STRING)
    private ItensConsumiveisEnum tipoConsumivel;

    @Column
    private String aplicacao; // Exemplo: usado em difusores ou velas.
}

