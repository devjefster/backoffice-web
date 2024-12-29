package com.isadora.backoffice.processo_fabricacao.controller.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CriarFabricacaoRequest {
    @NotNull(message = "O ID do produto é obrigatório.")
    private Long produtoId;

    @NotNull(message = "O Nome é obrigatório.")
    private String nome;
    private String descricao;

    @NotEmpty(message = "A lista de processos de fabricação não pode ser vazia.")
    private List<@Valid ProcessoFabricacaoDTO> processos;
    private double volumeTotal;                          // Volume total desejado
}