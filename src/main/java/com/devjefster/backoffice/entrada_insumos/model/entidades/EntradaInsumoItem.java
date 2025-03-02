package com.devjefster.backoffice.entrada_insumos.model.entidades;

import com.devjefster.backoffice.estoque.model.entidades.GradeCadastrada;
import com.devjefster.backoffice.fabricacao.model.enums.UnidadeMedida;
import com.devjefster.backoffice.insumos.model.entidades.Insumo;
import com.devjefster.backoffice.pessoa.model.entidades.Pessoa;
import com.devjefster.backoffice.util.model.EntidadeBase;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
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
public class EntradaInsumoItem extends EntidadeBase {


    @ManyToOne
    @JoinColumn(name = "entrada_id", nullable = false)
    private EntradaInsumos entradaInsumos;

    @ManyToOne
    @JoinColumn(name = "insumo_id", nullable = false)
    private Insumo insumo;

    @ManyToOne
    @JoinColumn(name = "fabricante_id", nullable = false)
    private Pessoa fabricante;

    @Column(nullable = false)
    private BigDecimal quantidade; // Quantidade do insumo na unidade de entrada.

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UnidadeMedida unidadeMedidaEntrada; // Unidade de medida da entrada.

    @Column(nullable = false)
    private BigDecimal precoUnitario; // Preço por unidade de entrada.

    @Column(nullable = false)
    private LocalDate validade;

    @Column(nullable = false)
    private BigDecimal custoTotal;

    @OneToOne(mappedBy = "entradaItem", cascade = CascadeType.ALL)
    private LoteEntrada lote; // Lote gerado.

    @ManyToMany
    @JoinTable(
            name = "entrada_insumo_item_grades",
            joinColumns = @JoinColumn(name = "entrada_item_id"),
            inverseJoinColumns = @JoinColumn(name = "grade_id"))
    private List<GradeCadastrada> grades;

    public void calcularCustoTotal() {
        this.custoTotal = precoUnitario.multiply(quantidade);
    }
}
