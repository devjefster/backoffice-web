package com.isadora.backoffice.fabricacao.model.specifications;

import com.isadora.backoffice.processo_fabricacao.model.entidades.FormulaFabricacao;
import com.isadora.backoffice.processo_fabricacao.model.enums.TipoProcessoFabricacao;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class FormulaFabricacaoSpecification {
    public static Specification<FormulaFabricacao> porProduto(String nome) {
        return (root, query, criteriaBuilder) -> nome == null ? null :
                criteriaBuilder.like(criteriaBuilder.lower(root.get("produto").get("nome")), "%" + nome.toLowerCase() + "%");
    }

    public static Specification<FormulaFabricacao> porDataInicio(LocalDate dataInicio) {
        return (root, query, criteriaBuilder) -> dataInicio == null ? null :
                criteriaBuilder.greaterThanOrEqualTo(root.get("createdAt"), dataInicio.atStartOfDay());
    }

    public static Specification<FormulaFabricacao> porDataFim(LocalDate dataFim) {
        return (root, query, criteriaBuilder) -> dataFim == null ? null :
                criteriaBuilder.lessThanOrEqualTo(root.get("createdAt"), dataFim.atTime(23, 59, 59));
    }

    public static Specification<FormulaFabricacao> porTipoProcesso(TipoProcessoFabricacao tipoProcesso) {
        return (root, query, builder) -> builder.equal(root.join("processosFabricacao").get("tipoProcesso"), tipoProcesso);
    }

}
