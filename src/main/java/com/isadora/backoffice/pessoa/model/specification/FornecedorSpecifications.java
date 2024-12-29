package com.isadora.backoffice.pessoa.model.specification;

import com.isadora.backoffice.pessoa.model.entidades.Fornecedor;
import org.springframework.data.jpa.domain.Specification;

public class FornecedorSpecifications {

    public static Specification<Fornecedor> porNomeFantasia(String nomeFantasia) {
        return (root, query, criteriaBuilder) -> nomeFantasia == null ? null :
                criteriaBuilder.like(criteriaBuilder.lower(root.get("nomeFantasia")), "%" + nomeFantasia.toLowerCase() + "%");
    }

    public static Specification<Fornecedor> porCnpj(String cnpj) {
        return (root, query, criteriaBuilder) -> cnpj == null ? null :
                criteriaBuilder.equal(root.get("cnpj"), cnpj);
    }
}
