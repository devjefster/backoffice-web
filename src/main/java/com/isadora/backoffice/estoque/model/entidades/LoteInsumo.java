package com.isadora.backoffice.estoque.model.entidades;

import com.isadora.backoffice.pessoa.model.entidades.Fabricante;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("INSUMOS")
public class LoteInsumo extends LoteEstoque {


    @ManyToOne
    @JoinColumn(name = "fabricante_id", nullable = false)
    private Fabricante fabricante;

    @Column(nullable = false, updatable = false)
    private LocalDate dataEntrada;
}

