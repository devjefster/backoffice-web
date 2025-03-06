package com.devjefster.backoffice.pessoa.model.specification;

import com.devjefster.backoffice.pessoa.controller.dto.FiltrosPessoaDTO;
import com.devjefster.backoffice.pessoa.model.entidades.Pessoa;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class PessoaSpecification implements Specification<Pessoa> {

    private final FiltrosPessoaDTO filtros;

    public PessoaSpecification(FiltrosPessoaDTO filtros) {
        this.filtros = filtros;
    }

    @Override
    public Predicate toPredicate(Root<Pessoa> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        criteriaBuilder.conjunction();
        if (filtros != null) {
            if (filtros.cpfCnpj() != null) {
                predicates.add(criteriaBuilder.equal(root.get("cpfCnpj"), filtros.cpfCnpj()));
            }
            if (filtros.nome() != null && !filtros.nome().trim().isEmpty()) {
                predicates.add(criteriaBuilder.or(
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("nome")), "%" + filtros.nome().trim().toLowerCase() + "%"),
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("razaoSocial")), "%" + filtros.nome().trim().toLowerCase() + "%"),
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("nomeFantasia")), "%" + filtros.nome().trim().toLowerCase() + "%")
                ));
            }
            if (filtros.tipo() != null) {
                predicates.add(criteriaBuilder.equal(root.get("tipo"), filtros.tipo())); // Ensure "tipo" is not null
            }

            if (filtros.tipoPessoa() != null) {
                criteriaBuilder.equal(root.get("tipoPessoa"), filtros.tipoPessoa());

            }
        }

        return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
    }
}
