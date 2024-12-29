package com.isadora.backoffice.pessoa.controller.dto;

import com.isadora.backoffice.pessoa.model.entidades.Endereco;
import lombok.Data;

import java.util.List;

@Data
public class PessoaDTO {
    private String id;
    private String cpf;
    private String cnpj;
    private String nomeFantasia;
    private String nomeSocial;

    private List<Endereco> endereco;

    private String email;
    private String emailSecundario;

    private String telefone;
    private String telefoneSecundario;

}
