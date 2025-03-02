package com.devjefster.backoffice.fabricacao.model.entidades;

import com.devjefster.backoffice.estoque.model.entidades.LoteEstoque;
import com.devjefster.backoffice.util.model.EntidadeBase;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ItensConsumidosFabricacao extends EntidadeBase {

    @ManyToOne
    @JoinColumn(name = "ordem_fabricacao_id")
    private OrdemFabricacao ordemFabricacao;

    /*
        Caso seja fabricacao de produto final serão consumidados produtos acaabados no processo.
        Caso seja fabricacaod e produto acabado, insumos serão consumidos
     */
    @ManyToOne
    @JoinColumn(name = "lote__id")
    private LoteEstoque lote;

    /*
        Quantidade consumida
     */
    private BigDecimal quantidade;
}
