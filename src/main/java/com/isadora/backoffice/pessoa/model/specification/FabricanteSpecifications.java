package com.isadora.backoffice.pessoa.model.specification;

import com.isadora.backoffice.pessoa.model.entidades.Fabricante;
import org.springframework.data.jpa.domain.Specification;

public class FabricanteSpecifications {

    public static Specification<Fabricante> porNomeFantasia(String nomeFantasia) {
        return (root, query, criteriaBuilder) -> nomeFantasia == null ? null :
                criteriaBuilder.like(criteriaBuilder.lower(root.get("nomeFantasia")), "%" + nomeFantasia.toLowerCase() + "%");
    }

    public static Specification<Fabricante> porCnpj(String cnpj) {
        return (root, query, criteriaBuilder) -> cnpj == null ? null :
                criteriaBuilder.equal(root.get("cnpj"), cnpj);
    }
}
