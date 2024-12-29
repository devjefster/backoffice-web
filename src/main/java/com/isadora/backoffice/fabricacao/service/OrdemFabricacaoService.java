package com.isadora.backoffice.fabricacao.service;

import com.isadora.backoffice.fabricacao.controller.dto.OrdemFabricacaoFiltros;
import com.isadora.backoffice.fabricacao.model.entidades.OrdemFabricacao;
import com.isadora.backoffice.fabricacao.model.repositories.OrdemFabricacaoRepository;
import com.isadora.backoffice.fabricacao.model.specifications.OrdemFabricacaoSpecification;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrdemFabricacaoService {
    private final OrdemFabricacaoRepository repository;

    public OrdemFabricacao buscarPeloId(Long id) {
        log.info("Buscando uma OrdemFabricacao com ID {}", id);
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Ordem Fabricação não encontrado"));
    }


    public OrdemFabricacao criar(OrdemFabricacao ordem) {
        log.info("Criando uma OrdemFabricacao {}", ordem);
        ordem.validarOrdemFabricacao();
        return repository.save(ordem);
    }

    public void deletar(OrdemFabricacao ordem) {
        log.info("Deletando uma OrdemFabricacao {}", ordem);
        repository.delete(ordem);
    }


    public Page<OrdemFabricacao> listarOrdens(OrdemFabricacaoFiltros fabricacaoFiltros, Pageable pageable) {
        log.info("Listando ordens de OrdemFabricacao, {}", fabricacaoFiltros);
        Specification<OrdemFabricacao> spec = Specification.where(OrdemFabricacaoSpecification.porTexto(fabricacaoFiltros.textoBusca()))
                .and(OrdemFabricacaoSpecification.porStatusFabricacao(fabricacaoFiltros.statusFabricacao()))
                .and(OrdemFabricacaoSpecification.isRascunho(fabricacaoFiltros.isRascunho()));
        return repository.findAll(spec, pageable);
    }
}
