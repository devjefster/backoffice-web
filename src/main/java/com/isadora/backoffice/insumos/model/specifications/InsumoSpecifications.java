package com.isadora.backoffice.insumos.model.specifications;

import com.isadora.backoffice.insumos.model.entidades.Insumo;
import com.isadora.backoffice.insumos.model.enums.TipoInsumo;
import org.springframework.data.jpa.domain.Specification;

public class InsumoSpecifications {
    public static Specification<Insumo> porTextoBusca(String textoBusca) {
        return (root, query, criteriaBuilder) -> {
            if (textoBusca != null && !textoBusca.isBlank()) {
                String texto = "%" + textoBusca.toLowerCase() + "%";
                return criteriaBuilder.or(
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("nome")), texto),
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("descricao")), texto),
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("aplicacao")), texto),
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("material")), texto),
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("descricaoServico")), texto),
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("especificacoesTecnicas")), texto)
                );
            }
            return criteriaBuilder.conjunction();
        };
    }


    public static Specification<Insumo> porTipo(TipoInsumo tipo) {
        return (root, query, criteriaBuilder) -> {
            if (tipo != null) {
                return criteriaBuilder.equal(root.get("tipo"), tipo);
            }
            return criteriaBuilder.conjunction();
        };
    }
    public static Specification<Insumo> filtrosDinamicos(String textoBusca, TipoInsumo tipo) {
        Specification<Insumo> spec = Specification.where(null);

        if (textoBusca != null && !textoBusca.isBlank()) {
            spec = spec.and(porTextoBusca(textoBusca));
        }

        if (tipo != null) {
            spec = spec.and(porTipo(tipo));
        }

        return spec;
    }


    public static Specification<Insumo> combinarFiltros(String textoBusca, TipoInsumo tipo) {
        return Specification.where(porTextoBusca(textoBusca)).and(porTipo(tipo));
    }


}
