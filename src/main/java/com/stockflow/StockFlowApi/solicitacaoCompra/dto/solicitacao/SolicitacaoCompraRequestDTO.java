package com.stockflow.StockFlowApi.solicitacaoCompra.dto.solicitacao;

import com.stockflow.StockFlowApi.solicitacaoCompra.dto.item.ItemSolicitacaoCompraRequestDTO;
import com.stockflow.StockFlowApi.solicitacaoCompra.entity.ItemSolicitacaoCompra;

import java.util.List;

public record SolicitacaoCompraRequestDTO(
        Long usuario_id,
        String obs,
        List<ItemSolicitacaoCompraRequestDTO> itensCompra
) {
}
