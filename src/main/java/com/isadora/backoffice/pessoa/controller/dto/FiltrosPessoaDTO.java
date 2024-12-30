package com.isadora.backoffice.pessoa.controller.dto;

import com.isadora.backoffice.pessoa.model.enums.TipoCadastro;
import com.isadora.backoffice.pessoa.model.enums.TipoPessoa;

public record FiltrosPessoaDTO(String nome, TipoCadastro tipo, TipoPessoa tipoPessoa, String cpfCnpj) {
}
