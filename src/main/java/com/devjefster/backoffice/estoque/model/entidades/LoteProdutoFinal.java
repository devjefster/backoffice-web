package com.devjefster.backoffice.estoque.model.entidades;

import com.devjefster.backoffice.produto.model.Produto;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue("PRODUTO_FINAL")
public class LoteProdutoFinal extends LoteEstoque {


    @ManyToOne
    private Produto produto;
    @Column(nullable = false, updatable = false)
    private LocalDate dataFabricacao;

}
