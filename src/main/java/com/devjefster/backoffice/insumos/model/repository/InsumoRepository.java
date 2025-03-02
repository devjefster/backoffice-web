package com.devjefster.backoffice.insumos.model.repository;

import com.devjefster.backoffice.insumos.model.entidades.Insumo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface InsumoRepository extends JpaRepository<Insumo, Long>, JpaSpecificationExecutor<Insumo> {
}
