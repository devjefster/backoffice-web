package com.isadora.backoffice.processo_fabricacao.model.entidades;

import com.isadora.backoffice.produto.model.ProdutoAcabado;
import com.isadora.backoffice.util.model.SoftDeleteEntidadeBase;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SQLDelete(sql = "UPDATE formula_fabricacao SET deletado = true WHERE id=?")
@SQLRestriction("deletado <> true")
public class FormulaFabricacao extends SoftDeleteEntidadeBase {

    private String nome;
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private ProdutoAcabado produto;

    @OneToMany(mappedBy = "formulaFabricacao", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProcessoFabricacao> processosFabricacao;
}
