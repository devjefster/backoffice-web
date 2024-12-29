package com.isadora.backoffice.estoque.model.entidades;

import com.isadora.backoffice.estoque.model.enums.TipoEstoque;
import com.isadora.backoffice.fabricacao.model.enums.UnidadeMedida;
import com.isadora.backoffice.insumos.model.entidades.Insumo;
import com.isadora.backoffice.produto.model.Produto;
import com.isadora.backoffice.util.model.EntidadeBase;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Estoque extends EntidadeBase {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoEstoque tipo;


    @ManyToOne
    @JoinColumn(name = "insumo_id", nullable = true)
    private Insumo insumo;

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = true)
    private Produto produto;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UnidadeMedida unidadeMedida;

    @Column(nullable = false)
    private BigDecimal quantidade; // Quantidade total disponível.

    @Column(nullable = true)
    private LocalDate validade;

    @ManyToMany
    @JoinTable(
            name = "estoque_grades",
            joinColumns = @JoinColumn(name = "estoque_id"),
            inverseJoinColumns = @JoinColumn(name = "grade_id"))
    private List<GradeCadastrada> grades;

    @OneToMany(mappedBy = "estoque")
    private List<LoteEstoque> lotes;

    public void sincronizarQuantidadeComLotes() {
        if (this.lotes != null) {
            BigDecimal somaLotes = this.lotes.stream()
                    .map(LoteEstoque::getQuantidade)
                    .filter(Objects::nonNull)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            if (!this.quantidade.equals(somaLotes)) {
                throw new IllegalStateException("Inconsistência detectada: A quantidade do estoque não corresponde à soma das quantidades dos lotes.");
            }
        } else {
            this.quantidade = BigDecimal.ZERO;
        }
    }


    public void adicionarQuantidade(BigDecimal quantidade) {
        if (this.quantidade == null) {
            this.quantidade = BigDecimal.ZERO;
        }
        this.quantidade = this.quantidade.add(quantidade);
    }

    public void reduzirQuantidade(BigDecimal quantidade) {
        if (this.quantidade.compareTo(quantidade) < 0) {
            throw new IllegalArgumentException("Quantidade insuficiente no lote para realizar a operação.");
        }
        this.quantidade = this.quantidade.subtract(quantidade);
    }


}

