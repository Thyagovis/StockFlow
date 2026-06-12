package com.stockflow.StockFlowApi.estoque.dto;

public record EstoqueRequestDTO(
        Long quantidadeAtual,
        Long quantidadeReservada,
        Long estoqueMinimo,
        Long estoqueMaximo
) {
}