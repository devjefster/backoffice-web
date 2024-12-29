package com.isadora.backoffice.estoque.model.specifications;

import com.isadora.backoffice.estoque.model.entidades.Estoque;
import com.isadora.backoffice.estoque.model.entidades.LoteEstoque;
import com.isadora.backoffice.estoque.model.enums.TipoEstoque;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Subquery;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.time.LocalDate;

public class EstoqueSpecifications {

    public static Specification<Estoque> porTextoBusca(String textoBusca) {
        return (root, query, criteriaBuilder) -> {
            if (textoBusca != null && !textoBusca.isBlank()) {
                String texto = "%" + textoBusca.toLowerCase() + "%";
                return criteriaBuilder.or(
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("produtoFinal").get("nome")), texto),
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("produtoAcabado").get("nome")), texto),
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("fabricante").get("nomeFantasia")), texto)
                );
            }
            return criteriaBuilder.conjunction();
        };
    }

    public static Specification<Estoque> porFabricante(Long fabricanteId) {
        return (root, query, criteriaBuilder) -> {
            if (fabricanteId != null) {
                return criteriaBuilder.equal(root.get("fabricante").get("id"), fabricanteId);
            }
            return criteriaBuilder.conjunction();
        };
    }

    public static Specification<Estoque> porTipo(TipoEstoque tipoEstoque) {
        return (root, query, criteriaBuilder) -> {
            if (tipoEstoque != null) {
                criteriaBuilder.equal(root.get("tipoEstoque"), tipoEstoque);
            }
            return criteriaBuilder.conjunction();
        };
    }

    public static Specification<Estoque> porValidade(LocalDate validadeMinima, LocalDate validadeMaxima) {
        return (root, query, criteriaBuilder) -> {
            if (validadeMinima != null && validadeMaxima != null) {
                return criteriaBuilder.between(root.get("validade"), validadeMinima, validadeMaxima);
            } else if (validadeMinima != null) {
                return criteriaBuilder.greaterThanOrEqualTo(root.get("validade"), validadeMinima);
            } else if (validadeMaxima != null) {
                return criteriaBuilder.lessThanOrEqualTo(root.get("validade"), validadeMaxima);
            }
            return criteriaBuilder.conjunction();
        };
    }

    public static Specification<Estoque> porUnidadeMedida(String unidadeMedida) {
        return (root, query, criteriaBuilder) -> {
            if (unidadeMedida != null && !unidadeMedida.isBlank()) {
                return criteriaBuilder.equal(root.get("unidadeMedida"), unidadeMedida.toUpperCase());
            }
            return criteriaBuilder.conjunction();
        };
    }

    public static Specification<Estoque> porDataFabricacao(LocalDate fabricacaoMinima, LocalDate fabricacaoMaxima) {
        return (root, query, criteriaBuilder) -> {
            if (fabricacaoMinima != null && fabricacaoMaxima != null) {
                return criteriaBuilder.between(root.get("dataFabricacao"), fabricacaoMinima, fabricacaoMaxima);
            } else if (fabricacaoMinima != null) {
                return criteriaBuilder.greaterThanOrEqualTo(root.get("dataFabricacao"), fabricacaoMinima);
            } else if (fabricacaoMaxima != null) {
                return criteriaBuilder.lessThanOrEqualTo(root.get("dataFabricacao"), fabricacaoMaxima);
            }
            return criteriaBuilder.conjunction();
        };
    }

    public static Specification<Estoque> somenteLotesComEstoque(boolean someLotesComEstoque) {
        return (root, query, criteriaBuilder) -> {
            if (someLotesComEstoque) {
                assert query != null;
                Subquery<Long> subquery = query.subquery(Long.class);
                Root<LoteEstoque> loteRoot = subquery.from(LoteEstoque.class);
                subquery.select(loteRoot.get("id"))
                        .where(
                                criteriaBuilder.equal(loteRoot.get("estoque"), root),
                                criteriaBuilder.greaterThan(loteRoot.get("quantidade"), BigDecimal.ZERO)
                        );

                return criteriaBuilder.exists(subquery);
            }
            return criteriaBuilder.conjunction();
        };
    }

}
