package com.isadora.backoffice.processo_fabricacao.model.entidades;

import com.isadora.backoffice.insumos.model.entidades.Insumo;
import com.isadora.backoffice.processo_fabricacao.model.enums.TipoProcessoFabricacao;
import com.isadora.backoffice.util.model.SoftDeleteEntidadeBase;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SQLDelete(sql = "UPDATE processo_fabricacao SET deletado = true WHERE id=?")
@SQLRestriction("deletado <> true")
public class ProcessoFabricacao extends SoftDeleteEntidadeBase {

    @ManyToOne
    @JoinColumn(name = "formula_fabricacao_id", nullable = false)
    private FormulaFabricacao formulaFabricacao;

    @ManyToOne
    @JoinColumn(name = "insumo_id", nullable = false)
    private Insumo insumo;

    @Enumerated(EnumType.STRING)
    private TipoProcessoFabricacao tipoProcesso;


    private double porcentagem;
}
