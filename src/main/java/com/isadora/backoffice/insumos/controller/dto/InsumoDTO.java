package com.isadora.backoffice.insumos.controller.dto;

import com.isadora.backoffice.pessoa.controller.dto.PessoaDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class InsumoDTO {
    private Long id;
    private String nome;
    private String descricao;
    private String tipoInsumo;
    private List<PessoaDTO> fabricantes;
    private List<PessoaDTO> fornecedores;
    private List<GradeCadastradaDTO> grades;
}

