package com.isadora.backoffice.notificacao.controller.dto;

import com.isadora.backoffice.notificacao.model.TipoNotificacao;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NotificacaoDTO {

    private Long id;

    private String titulo;
    private String descricao;
    private LocalDateTime dataNotificacao;
    private TipoNotificacao tipo;
    private LocalDateTime lido;
}
