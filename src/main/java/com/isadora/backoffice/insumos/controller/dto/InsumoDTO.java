package com.isadora.backoffice.insumos.controller.dto;

import com.isadora.backoffice.fabricacao.model.enums.UnidadeMedida;
import com.isadora.backoffice.insumos.model.enums.EmbalagensEnum;
import com.isadora.backoffice.insumos.model.enums.ItensConsumiveisEnum;
import com.isadora.backoffice.insumos.model.enums.TipoInsumo;
import com.isadora.backoffice.insumos.model.enums.TipoMateriaPrima;
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
    private UnidadeMedida unidadeMedida	;
    private TipoInsumo tipo;
    private List<PessoaDTO> fabricantes;
    private List<PessoaDTO> fornecedores;
    private List<GradeCadastradaDTO> grades;
    private ItensConsumiveisEnum tipoConsumivel; // Enum: PAVIO, VARETAS, etc.
    private String aplicacao;
    private EmbalagensEnum tipoEmbalagem; // Enum: FRASCO_VIDRO, CAIXA, etc.
    private String dimensoes;
    private String material;
    private TipoMateriaPrima tipoMateriaPrima; // Enum: ESSENCIA, ALCOOL, etc.
    private String especificacoesTecnicas;
}

