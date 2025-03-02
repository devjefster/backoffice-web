package com.devjefster.backoffice.pessoa.controller.dto;

import com.devjefster.backoffice.pessoa.model.enums.TipoCadastro;
import com.devjefster.backoffice.pessoa.model.enums.TipoPessoa;

public record FiltrosPessoaDTO(String nome, TipoCadastro tipo, TipoPessoa tipoPessoa, String cpfCnpj) {
}
