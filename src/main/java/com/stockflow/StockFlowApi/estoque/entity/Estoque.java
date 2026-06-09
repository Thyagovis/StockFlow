package com.stockflow.StockFlowApi.estoque.entity;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

import java.time.LocalDateTime;

public class Estoque {

    @Id
    @OneToOne
    @JoinColumn(name = "produto_id")
    private Long id;

    private Long quantidadeAtual;
    private Long quantidadeReservada;
    private Long estoqueMinimo;
    private Long estoqueMaximo;
    private LocalDateTime ultimaAtualizacao;

}
