package com.isadora.backoffice.pessoa.controller.dto;

import com.isadora.backoffice.pessoa.model.enums.TipoCadastro;
import com.isadora.backoffice.pessoa.model.enums.TipoPessoa;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class PessoaDTO {
    private Long id;
    private String nomeFantasia;
    private String razaoSocial;
    private String nome;
    private String cpfCnpj;

    private List<EnderecoDTO> enderecos;

    private String email;
    private String emailSecundario;

    private String telefone;
    private String telefoneSecundario;

    @NotNull
    private TipoPessoa tipoPessoa;


    @NotNull
    private TipoCadastro tipo;


}
