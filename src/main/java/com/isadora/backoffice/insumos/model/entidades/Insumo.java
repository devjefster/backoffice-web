package com.isadora.backoffice.insumos.model.entidades;

import com.isadora.backoffice.estoque.model.entidades.GradeCadastrada;
import com.isadora.backoffice.fabricacao.model.enums.UnidadeMedida;
import com.isadora.backoffice.insumos.model.enums.EmbalagensEnum;
import com.isadora.backoffice.insumos.model.enums.ItensConsumiveisEnum;
import com.isadora.backoffice.insumos.model.enums.TipoInsumo;
import com.isadora.backoffice.insumos.model.enums.TipoMateriaPrima;
import com.isadora.backoffice.pessoa.model.entidades.Pessoa;
import com.isadora.backoffice.util.model.EntidadeBase;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Insumo extends EntidadeBase {


    @Column(nullable = false)
    private String nome;

    @Column(nullable = true)
    private String descricao;

    private String codigoBarras;

    private String qrCode;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UnidadeMedida unidadeMedida;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoInsumo tipo;

    @Enumerated(EnumType.STRING)
    private TipoMateriaPrima tipoMateriaPrima;

    @Enumerated(EnumType.STRING)
    private ItensConsumiveisEnum tipoConsumivel;

    @Enumerated(EnumType.STRING)
    private EmbalagensEnum tipoEmbalagem;

    @Column
    private String especificacoesTecnicas;


    @Column
    private String aplicacao;


    @Column
    private String dimensoes; // Exemplo: 10x15x20 cm.

    @Column
    private String material;

    @ManyToMany
    @JoinTable(
            name = "insumo_fabricante",
            joinColumns = @JoinColumn(name = "insumo_id"),
            inverseJoinColumns = @JoinColumn(name = "fabricante_id"))
    private List<Pessoa> fabricantes;

    @ManyToMany
    @JoinTable(
            name = "insumo_fornecedor",
            joinColumns = @JoinColumn(name = "insumo_id"),
            inverseJoinColumns = @JoinColumn(name = "fornecedor_id"))
    private List<Pessoa> fornecedFores;


    @ManyToMany
    @JoinTable(
            name = "insumo_grades",
            joinColumns = @JoinColumn(name = "insumo_id"),
            inverseJoinColumns = @JoinColumn(name = "grade_id"))
    private List<GradeCadastrada> grades;
}
