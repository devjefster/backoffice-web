package com.isadora.backoffice.insumos.model.specifications;

import com.isadora.backoffice.estoque.model.entidades.GradeCadastrada;
import org.springframework.data.jpa.domain.Specification;

public class GradeSpecifications {
    public static Specification<GradeCadastrada> porTextoBusca(String textoBusca) {
        return (root, query, criteriaBuilder) -> {
            if (textoBusca != null && !textoBusca.isBlank()) {
                String texto = "%" + textoBusca.toLowerCase() + "%";
                return
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("nome")), texto);

            }
            return criteriaBuilder.conjunction();
        };
    }


}
