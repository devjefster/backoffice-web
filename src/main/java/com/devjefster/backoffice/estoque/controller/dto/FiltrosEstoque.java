package com.devjefster.backoffice.estoque.controller.dto;

import com.devjefster.backoffice.estoque.model.enums.TipoEstoque;
import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@ToString
@Data
public class FiltrosEstoque {

    private String textoBusca;

    private TipoEstoque tipo;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate validadeMinima;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate validadeMaxima;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate fabricacaoMinima;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate fabricacaoMaxima;

    private String unidadeMedida;

    private boolean someLotesComEstoque = true;
}
