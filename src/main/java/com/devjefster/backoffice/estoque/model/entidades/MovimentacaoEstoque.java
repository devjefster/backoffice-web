package com.devjefster.backoffice.estoque.model.entidades;

import com.devjefster.backoffice.estoque.model.enums.TipoMovimentacaoEstoque;
import com.devjefster.backoffice.util.model.EntidadeBase;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MovimentacaoEstoque extends EntidadeBase {

    @ManyToOne
    @JoinColumn(name = "lote_estoque_id", nullable = false)
    private LoteEstoque loteEstoque;

    @Column(nullable = false)
    private BigDecimal quantidade;

    @Column(nullable = false)
    private LocalDate dataMovimentacao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoMovimentacaoEstoque tipo;

}
