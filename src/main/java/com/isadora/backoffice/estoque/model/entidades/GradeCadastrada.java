package com.isadora.backoffice.estoque.model.entidades;

import com.isadora.backoffice.util.model.EntidadeBase;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GradeCadastrada extends EntidadeBase {


    @Column(nullable = false)
    private String nome; // Ex.: Cor, Tamanho, Tipo.

    @ElementCollection
    @CollectionTable(name = "grade_valores", joinColumns = @JoinColumn(name = "grade_id"))
    @Column(name = "valor")
    private List<String> valores; // Ex.: [Vermelho, Azul, Preto] para cores.
}
