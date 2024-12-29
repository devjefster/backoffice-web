package com.isadora.backoffice.pessoa.controller.dto;

import com.isadora.backoffice.pessoa.model.enums.TipoEndereco;
import lombok.Data;

@Data
public class EnderecoDTO {
    private String id;
    private TipoEndereco tipo;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String cep;
    private String cidade;
    private String uf;
    private String referencia;

}
