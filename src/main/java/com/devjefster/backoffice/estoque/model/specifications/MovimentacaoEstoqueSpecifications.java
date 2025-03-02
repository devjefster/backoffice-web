package com.devjefster.backoffice.estoque.model.specifications;

import com.devjefster.backoffice.estoque.model.entidades.MovimentacaoEstoque;
import com.devjefster.backoffice.estoque.model.enums.TipoMovimentacaoEstoque;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

public class MovimentacaoEstoqueSpecifications {


    public static Specification<MovimentacaoEstoque> porEstoque(Long estoqueId) {
        return (root, query, criteriaBuilder) -> {
            if (estoqueId != null) {
                return criteriaBuilder.equal(root.join("loteEstoque", JoinType.LEFT).join("estoque", JoinType.INNER).get("id"), estoqueId);
            }
            return criteriaBuilder.conjunction();
        };
    }

    public static Specification<MovimentacaoEstoque> porTipo(TipoMovimentacaoEstoque tipo) {
        return (root, query, criteriaBuilder) -> {
            if (tipo != null) {
                criteriaBuilder.equal(root.get("tipo"), tipo);
            }
            return criteriaBuilder.conjunction();
        };
    }


}
