package com.isadora.backoffice.insumos.model.repository;

import com.isadora.backoffice.estoque.model.entidades.GradeCadastrada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface GradeRepository extends JpaRepository<GradeCadastrada, Long>, JpaSpecificationExecutor<GradeCadastrada> {
}
