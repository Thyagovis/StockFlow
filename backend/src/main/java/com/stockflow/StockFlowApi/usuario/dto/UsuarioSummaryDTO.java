package com.stockflow.StockFlowApi.usuario.dto;

import com.stockflow.StockFlowApi.usuario.enums.Cargo;

public record UsuarioSummaryDTO(

        Long id,
        String nome,
        String email,
        Cargo cargo

) {
}
