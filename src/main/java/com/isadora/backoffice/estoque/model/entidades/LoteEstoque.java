package com.isadora.backoffice.estoque.model.entidades;

import com.isadora.backoffice.fabricacao.model.enums.UnidadeMedida;
import com.isadora.backoffice.util.model.SoftDeleteEntidadeBase;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo", discriminatorType = DiscriminatorType.STRING)
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoteEstoque extends SoftDeleteEntidadeBase {


    @ManyToOne
    @JoinColumn(name = "estoque_id", nullable = false)
    private Estoque estoque;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UnidadeMedida unidadeMedida;

    @Column(nullable = false)
    private BigDecimal quantidade;

    @Column
    private LocalDate validade;

    @OneToMany(mappedBy = "loteEstoque", fetch = FetchType.LAZY)
    private List<MovimentacaoEstoque> movimentacaoEstoque;

    @ManyToMany
    @JoinTable(
            name = "lote_grades",
            joinColumns = @JoinColumn(name = "lote_id"),
            inverseJoinColumns = @JoinColumn(name = "grade_id"))
    private List<GradeCadastrada> grades; // Ex.: Cor Vermelha, Tamanho Pequeno.

    @Column(nullable = false)
    private BigDecimal custoUnitario; // Custo calculado por unidade padrão.

    public void adicionarQuantidade(BigDecimal quantidade) {
        if (this.quantidade == null) {
            this.quantidade = BigDecimal.ZERO;
        }
        this.quantidade = this.quantidade.add(quantidade);
        this.estoque.sincronizarQuantidadeComLotes();
    }

    public void reduzirQuantidade(BigDecimal quantidade) {
        if (this.quantidade.compareTo(quantidade) < 0) {
            throw new IllegalArgumentException("Quantidade insuficiente no lote para realizar a operação.");
        }
        this.quantidade = this.quantidade.subtract(quantidade);
        this.estoque.sincronizarQuantidadeComLotes();
    }


}

