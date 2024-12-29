package com.isadora.backoffice.insumos.controller.dto;

import com.isadora.backoffice.pessoa.controller.dto.FabricanteDTO;
import com.isadora.backoffice.pessoa.controller.dto.FornecedorDTO;
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
    private List<FabricanteDTO> fabricantes;
    private List<FornecedorDTO> fornecedores;
    private List<GradeCadastradaDTO> grades;
}

