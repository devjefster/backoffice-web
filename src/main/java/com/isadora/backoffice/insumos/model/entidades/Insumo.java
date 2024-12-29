package com.isadora.backoffice.insumos.model.entidades;

import com.isadora.backoffice.estoque.model.entidades.GradeCadastrada;
import com.isadora.backoffice.fabricacao.model.enums.UnidadeMedida;
import com.isadora.backoffice.pessoa.model.entidades.Fabricante;
import com.isadora.backoffice.pessoa.model.entidades.Fornecedor;
import com.isadora.backoffice.util.model.EntidadeBase;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_insumo", discriminatorType = DiscriminatorType.STRING)
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


    @ManyToMany
    @JoinTable(
            name = "insumo_fabricante",
            joinColumns = @JoinColumn(name = "insumo_id"),
            inverseJoinColumns = @JoinColumn(name = "fabricante_id"))
    private List<Fabricante> fabricantes;

    @ManyToMany
    @JoinTable(
            name = "insumo_fornecedor",
            joinColumns = @JoinColumn(name = "insumo_id"),
            inverseJoinColumns = @JoinColumn(name = "fornecedor_id"))
    private List<Fornecedor> fornecedores;


    @ManyToMany
    @JoinTable(
            name = "insumo_grades",
            joinColumns = @JoinColumn(name = "insumo_id"),
            inverseJoinColumns = @JoinColumn(name = "grade_id"))
    private List<GradeCadastrada> grades;
}
