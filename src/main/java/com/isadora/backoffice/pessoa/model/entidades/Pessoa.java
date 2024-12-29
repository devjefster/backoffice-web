package com.isadora.backoffice.pessoa.model.entidades;

import com.isadora.backoffice.util.model.SoftDeleteEntidadeBase;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Pessoa extends SoftDeleteEntidadeBase {


    private String cpf;
    private String cnpj;
    private String nomeFantasia;
    private String nomeSocial;

    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL)
    private List<Endereco> endereco;

    private String email;
    private String emailSecundario;

    private String telefone;
    private String telefoneSecundario;
}
