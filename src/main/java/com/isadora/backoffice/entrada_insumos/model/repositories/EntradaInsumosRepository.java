package com.isadora.backoffice.entrada_insumos.model.repositories;

import com.isadora.backoffice.entrada_insumos.model.entidades.EntradaInsumos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface EntradaInsumosRepository extends JpaRepository<EntradaInsumos, Long>, JpaSpecificationExecutor<EntradaInsumos> {
}
