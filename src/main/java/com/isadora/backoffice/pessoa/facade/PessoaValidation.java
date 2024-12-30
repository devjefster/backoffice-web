package com.isadora.backoffice.pessoa.facade;

import com.isadora.backoffice.pessoa.model.entidades.Pessoa;
import com.isadora.backoffice.seguranca.config.ValidationException;

public class PessoaValidation {

    public static <T extends Pessoa> void validarPessoa(T pessoa) {
        if (pessoa != null) {
            if (pessoa.getCnpj() == null && pessoa.getCpf() == null) {
                throw new ValidationException("CPF ou CNPJ deve ser informado.", pessoa.getClass().getName());
            }
            if (pessoa.getNomeFantasia() == null && pessoa.getRazaoSocial() == null) {
                throw new ValidationException("Nome ou Razão Social deve ser informado.", pessoa.getClass().getName());
            }
        }
    }
}
