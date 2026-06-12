package com.stockflow.StockFlowApi.solicitacaoCompra.dto.item;

import java.math.BigDecimal;

public record ItemSolicitacaoCompraResponseDTO(
        Long id,
        BigDecimal quantidadeSolicitada
) {
}
