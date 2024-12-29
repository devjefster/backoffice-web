package com.isadora.backoffice.entrada_insumos.model.entidades;


import com.isadora.backoffice.estoque.model.entidades.GradeCadastrada;
import com.isadora.backoffice.fabricacao.model.enums.UnidadeMedida;
import com.isadora.backoffice.util.model.EntidadeBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
public class LoteEntrada extends EntidadeBase {

    @OneToOne
    @JoinColumn(name = "entrada_item_id", nullable = false)
    private EntradaInsumoItem entradaItem;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UnidadeMedida unidadeMedida;

    @Column(nullable = false)
    private BigDecimal quantidadeConvertida; // Quantidade convertida para a unidade padrão.

    @Column(nullable = false)
    private LocalDate validade;

    @ManyToMany
    @JoinTable(
            name = "lote_grades",
            joinColumns = @JoinColumn(name = "lote_id"),
            inverseJoinColumns = @JoinColumn(name = "grade_id"))
    private List<GradeCadastrada> grades;

    public boolean isValidoParaUso(LocalDate dataAtual) {
        return validade == null || !validade.isBefore(dataAtual);
    }
}
