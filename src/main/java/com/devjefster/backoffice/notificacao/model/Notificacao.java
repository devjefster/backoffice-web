package com.devjefster.backoffice.notificacao.model;

import com.devjefster.backoffice.util.model.SoftDeleteEntidadeBase;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Notificacao extends SoftDeleteEntidadeBase {


    private String titulo;
    private String descricao;
    private LocalDateTime dataNotificacao;
    private TipoNotificacao tipo;
    private LocalDateTime lido;
}
