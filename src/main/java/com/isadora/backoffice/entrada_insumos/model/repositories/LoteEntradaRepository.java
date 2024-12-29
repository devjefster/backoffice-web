package com.isadora.backoffice.entrada_insumos.model.repositories;

import com.isadora.backoffice.entrada_insumos.model.entidades.LoteEntrada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface LoteEntradaRepository extends JpaRepository<LoteEntrada, Long>, JpaSpecificationExecutor<LoteEntrada> {
}

