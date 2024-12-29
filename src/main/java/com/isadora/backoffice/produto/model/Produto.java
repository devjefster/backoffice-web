package com.isadora.backoffice.produto.model;

import com.isadora.backoffice.fabricacao.model.enums.UnidadeMedida;
import com.isadora.backoffice.produto.model.enums.TipoProduto;
import com.isadora.backoffice.util.model.SoftDeleteEntidadeBase;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo", discriminatorType = DiscriminatorType.STRING)
@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Produto extends SoftDeleteEntidadeBase {

    private String nome;

    private String descricao;
    private UnidadeMedida unidadeMedida;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_produto")
    private TipoProduto tipoProduto;

}
