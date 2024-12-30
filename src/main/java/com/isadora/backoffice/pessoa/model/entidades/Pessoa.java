package com.isadora.backoffice.pessoa.model.entidades;

import com.isadora.backoffice.pessoa.model.enums.TipoCadastro;
import com.isadora.backoffice.pessoa.model.enums.TipoPessoa;
import com.isadora.backoffice.util.model.SoftDeleteEntidadeBase;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public abstract class Pessoa extends SoftDeleteEntidadeBase {
    private String nome;
    private String razaoSocial;
    private String nomeFantasia;
    private String email;
    private String telefone;
    private TipoCadastro tipo;
    private TipoPessoa tipoPessoa;

    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL)
    private List<Endereco> enderecos;

    private String cpfCnpj;

}
