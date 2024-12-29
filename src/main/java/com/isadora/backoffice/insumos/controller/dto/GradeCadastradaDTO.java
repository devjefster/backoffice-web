package com.isadora.backoffice.insumos.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GradeCadastradaDTO {
    private Long id;
    private String nome;
    private List<String> valores;
}
