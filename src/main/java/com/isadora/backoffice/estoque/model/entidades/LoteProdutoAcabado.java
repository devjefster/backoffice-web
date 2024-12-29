package com.isadora.backoffice.estoque.model.entidades;

import com.isadora.backoffice.produto.model.Produto;
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
@DiscriminatorValue("PRODUTO_ACABADO")
public class LoteProdutoAcabado extends LoteEstoque {

    @ManyToOne
    private Produto produto;
    @Column(nullable = false, updatable = false)
    private LocalDate dataFabricacao;
}
