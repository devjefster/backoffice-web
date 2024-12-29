package com.isadora.backoffice.notificacao.service;

import com.isadora.backoffice.notificacao.model.Notificacao;
import com.isadora.backoffice.notificacao.model.NotificacaoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificacaoService {

    private final NotificacaoRepository notificacaoRepository;

    public Notificacao criar(final Notificacao notificacao) {
        return notificacaoRepository.save(notificacao);
    }
}
