package com.devjefster.backoffice.pessoa.model.entidades;

import com.devjefster.backoffice.pessoa.model.enums.TipoEndereco;
import com.devjefster.backoffice.util.model.EntidadeBase;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Endereco extends EntidadeBase {

    @Enumerated(EnumType.STRING)
    private TipoEndereco tipo;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String cep;
    private String cidade;
    private String uf;
    private String referencia;


    @ManyToOne
    @JoinColumn(name = "pessoa_id", nullable = false)
    private Pessoa pessoa;


}
