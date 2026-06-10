package com.stockflow.StockFlowApi.solicitacaoRetirada.repository;

import com.stockflow.StockFlowApi.solicitacaoRetirada.entity.SolicitacaoRetirada;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SolicitacaoRetiradaRepository  extends JpaRepository<SolicitacaoRetirada, Long> {
}
