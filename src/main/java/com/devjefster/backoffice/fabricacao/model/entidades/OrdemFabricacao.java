package com.devjefster.backoffice.fabricacao.model.entidades;

import com.devjefster.backoffice.estoque.model.entidades.GradeCadastrada;
import com.devjefster.backoffice.fabricacao.model.enums.StatusFabricacao;
import com.devjefster.backoffice.processo_fabricacao.model.entidades.FormulaFabricacao;
import com.devjefster.backoffice.produto.model.Produto;
import com.devjefster.backoffice.util.model.SoftDeleteEntidadeBase;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class OrdemFabricacao extends SoftDeleteEntidadeBase {

    private BigDecimal quantidadeProduzida; // Quantidade a ser fabricada.
    private LocalDate validade;
    private String observacoes;

    @Enumerated(EnumType.STRING)
    private StatusFabricacao status; // Status da fabricação (e.g., Planejado, Em Produção, Finalizado).

    private BigDecimal custoTotal; // Custo total da produção.
    private boolean isRascunho;

    /*
    Produto a ser fabricado
 */
    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;

    @OneToMany(mappedBy = "ordemFabricacao")
    private List<ItensConsumidosFabricacao> itens;

    /*
       Formula de fabricacao Usada
     */
    @ManyToOne
    @JoinColumn(name = "formula_fabricacao_id", nullable = false)
    private FormulaFabricacao formulaFabricacao; // Fórmula utilizada para a fabricação.


    @ManyToMany
    @JoinTable(
            name = "insumo_grades",
            joinColumns = @JoinColumn(name = "insumo_id"),
            inverseJoinColumns = @JoinColumn(name = "grade_id"))
    private List<GradeCadastrada> grades;

    public void validarOrdemFabricacao() {
        if (quantidadeProduzida == null || quantidadeProduzida.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("A quantidade produzida deve ser maior que zero.");
        }
        if (itens == null || itens.isEmpty()) {
            throw new IllegalArgumentException("A ordem de fabricação deve ter pelo menos um item consumido.");
        }
    }

}
