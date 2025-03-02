package com.devjefster.backoffice.entrada_insumos.model.specifications;

import com.devjefster.backoffice.entrada_insumos.model.entidades.EntradaInsumos;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class EntradaInsumosSpecifications {

    public static Specification<EntradaInsumos> porTextoBusca(String textoBusca) {
        return (root, query, criteriaBuilder) -> {
            if (textoBusca != null && !textoBusca.isBlank()) {
                String texto = "%" + textoBusca.toLowerCase() + "%";
                return criteriaBuilder.or(
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("fornecedor").get("nome")), texto),
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("itens").get("insumo").get("nome")), texto)
                );
            }
            return criteriaBuilder.conjunction();
        };
    }

    public static Specification<EntradaInsumos> porFornecedor(Long fornecedorId) {
        return (root, query, criteriaBuilder) -> {
            if (fornecedorId != null) {
                return criteriaBuilder.equal(root.get("fornecedor").get("id"), fornecedorId);
            }
            return criteriaBuilder.conjunction();
        };
    }

    public static Specification<EntradaInsumos> porDataEntrada(LocalDate dataInicio, LocalDate dataFim) {
        return (root, query, criteriaBuilder) -> {
            if (dataInicio != null && dataFim != null) {
                return criteriaBuilder.between(root.get("dataEntrada"), dataInicio, dataFim);
            } else if (dataInicio != null) {
                return criteriaBuilder.greaterThanOrEqualTo(root.get("dataEntrada"), dataInicio);
            } else if (dataFim != null) {
                return criteriaBuilder.lessThanOrEqualTo(root.get("dataEntrada"), dataFim);
            }
            return criteriaBuilder.conjunction();
        };
    }

    public static Specification<EntradaInsumos> filtrosDinamicos(String textoBusca, Long fornecedorId, LocalDate dataInicio, LocalDate dataFim) {
        return Specification
                .where(porTextoBusca(textoBusca))
                .and(porFornecedor(fornecedorId))
                .and(porDataEntrada(dataInicio, dataFim));
    }
}

