package com.isadora.backoffice.estoque.model.repositories;

import com.isadora.backoffice.estoque.model.entidades.GradeCadastrada;
import com.isadora.backoffice.estoque.model.entidades.LoteEstoque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface LoteEstoqueRepository extends JpaRepository<LoteEstoque, Long>, JpaSpecificationExecutor<LoteEstoque> {
    List<LoteEstoque> findByValidadeAfterAndQuantidadeGreaterThanEqual(LocalDate validade, BigDecimal quantidade);

    @Query("SELECT l FROM LoteEstoque l JOIN l.estoque e JOIN l.grades g " +
            "WHERE e.id = :estoqueId AND l.validade = :validade AND g IN :grades")
    <T extends LoteEstoque> List<T> findLotes(@Param("estoqueId") Long estoqueId,
                                              @Param("validade") LocalDate validade,
                                              @Param("grades") List<GradeCadastrada> grades);

}

