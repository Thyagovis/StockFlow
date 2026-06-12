package com.stockflow.StockFlowApi.solicitacaoCompra.repository;

import com.stockflow.StockFlowApi.solicitacaoCompra.entity.ItemSolicitacaoCompra;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemSolicitacaoCompraRepository extends JpaRepository<ItemSolicitacaoCompra, Long> {
}
