package com.isadora.backoffice.entrada_insumos.model.entidades;

import com.isadora.backoffice.pessoa.model.entidades.Fornecedor;
import com.isadora.backoffice.util.model.EntidadeBase;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;
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
    private Fornecedor fornecedor;

    @Column(nullable = false)
    private LocalDate dataEntrada;

    @Column(nullable = false)
    private BigDecimal custoFrete; // Valor do frete.

    @Column(nullable = false)
    private BigDecimal custoOutros; // Outros custos adicionais.

    @OneToMany(mappedBy = "entradaInsumos", cascade = CascadeType.ALL)
    private List<EntradaInsumoItem> itens; // Itens da entrada.

    @Transient
    public BigDecimal getCustoTotal() {
        return itens.stream()
                .map(EntradaInsumoItem::getCustoTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .add(custoFrete)
                .add(custoOutros);
    }
}

