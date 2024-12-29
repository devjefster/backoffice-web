package com.isadora.backoffice.insumos.model.entidades;

import com.isadora.backoffice.insumos.model.enums.ServicosEnum;
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
@DiscriminatorValue("SERVICO")
public class Servico extends Insumo {

    @Enumerated(EnumType.STRING)
    private ServicosEnum tipoServico;

    @Column
    private String descricaoServico;
}
