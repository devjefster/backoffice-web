package com.devjefster.backoffice.entrada_insumos.model.entidades;

import com.devjefster.backoffice.pessoa.model.entidades.Pessoa;
import com.devjefster.backoffice.util.model.EntidadeBase;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EntradaInsumos extends EntidadeBase {

    @ManyToOne
    @JoinColumn(name = "fornecedor_id", nullable = false)
    private Pessoa fornecedor;

    @Column(nullable = false)
    private LocalDate dataEntrada;

    @Column(nullable = false)
    private BigDecimal custoFrete; // Valor do frete.

    @Column(nullable = false)
    private BigDecimal custoOutros; // Outros custos adicionais.

    @OneToMany(mappedBy = "entradaInsumos", cascade = CascadeType.ALL)
    private List<EntradaInsumoItem> itens; // Itens da entrada.

    @Column(nullable = false)
    private BigDecimal custoTotal;

    public void calcularCusto() {
        custoTotal = itens.stream()
                .map(EntradaInsumoItem::getCustoTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .add(custoFrete)
                .add(custoOutros);
    }
}

