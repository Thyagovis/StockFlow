package com.stockflow.StockFlowApi.solicitacaoCompra.dto.solicitacao;

import com.stockflow.StockFlowApi.shared.enums.StatusSolicitacao;

import java.time.LocalDateTime;

public record SolicitacaoCompraResponseDTO(
        Long id,
        StatusSolicitacao statusSolicitacao,
        String observacao,
        LocalDateTime dataCriacao
) {
}
