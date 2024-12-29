package com.isadora.backoffice.pessoa.model.repositories;

import com.isadora.backoffice.pessoa.model.entidades.Fabricante;
import com.isadora.backoffice.pessoa.model.entidades.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


@Repository
public interface FornecedorRepository extends JpaRepository<Fornecedor, Long>, JpaSpecificationExecutor<Fornecedor> {
}
