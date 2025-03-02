package com.devjefster.backoffice.util.conversor;

import com.devjefster.backoffice.fabricacao.model.enums.UnidadeMedida;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class UnidadeMedidaConverter {

    public BigDecimal converterParaPadrao(UnidadeMedida unidadeEntrada, BigDecimal quantidade) {
        return switch (unidadeEntrada) {
            case LITRO -> quantidade; // Já está no padrão.
            case MILILITRO -> quantidade.divide(BigDecimal.valueOf(1000), RoundingMode.HALF_UP);
            case QUILO -> quantidade; // Já está no padrão.
            case GRAMA -> quantidade.divide(BigDecimal.valueOf(1000), RoundingMode.HALF_UP);
            default -> throw new IllegalArgumentException("Unidade não suportada para conversão: " + unidadeEntrada);
        };
    }
}

