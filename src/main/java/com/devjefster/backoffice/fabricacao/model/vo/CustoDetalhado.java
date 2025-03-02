package com.devjefster.backoffice.fabricacao.model.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CustoDetalhado {

    private BigDecimal materiaPrima;
    private BigDecimal insumos;
    private BigDecimal maoDeObra;
    private BigDecimal utilidades;

    // Total Calculation
    public BigDecimal getTotal() {
        return materiaPrima.add(insumos).add(maoDeObra).add(utilidades);
    }
}
