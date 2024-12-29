package com.isadora.backoffice.pessoa.model.entidades;

import com.isadora.backoffice.insumos.model.entidades.Insumo;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Fornecedor extends Pessoa {


    @ManyToMany(mappedBy = "fornecedores")
    private List<Insumo> insumos;

}
