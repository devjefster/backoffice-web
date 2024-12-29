package com.isadora.backoffice.fabricacao.model.specifications;

import com.isadora.backoffice.fabricacao.model.entidades.OrdemFabricacao;
import com.isadora.backoffice.fabricacao.model.enums.StatusFabricacao;
import org.springframework.data.jpa.domain.Specification;

public class OrdemFabricacaoSpecification {

    public static Specification<OrdemFabricacao> porTexto(String textoBusca) {
        return (root, query, criteriaBuilder) -> textoBusca == null ? null :
                criteriaBuilder.or(criteriaBuilder.like(criteriaBuilder.lower(root.get("produto").get("nome")), "%" + textoBusca.toLowerCase() + "%"),
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("formulaFabricacao").get("nome")), "%" + textoBusca.toLowerCase() + "%"));
    }

    public static Specification<OrdemFabricacao> porStatusFabricacao(StatusFabricacao statusFabricacao) {
        return (root, query, criteriaBuilder) -> statusFabricacao == null ? null :
                criteriaBuilder.equal(root.get("statusFabricacao"), statusFabricacao);
    }

    public static Specification<OrdemFabricacao> isRascunho(Boolean isRascunho) {
        return (root, query, criteriaBuilder) -> isRascunho == null ? null :
                criteriaBuilder.equal(root.get("isRascunho"), isRascunho);
    }


}
