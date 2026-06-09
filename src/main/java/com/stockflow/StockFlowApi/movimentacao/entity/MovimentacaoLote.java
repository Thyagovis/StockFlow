package com.stockflow.StockFlowApi.movimentacao.entity;

import com.stockflow.StockFlowApi.movimentacao.enums.OrigemMovimentacao;
import com.stockflow.StockFlowApi.movimentacao.enums.TipoMovimentacao;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class MovimentacaoLote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated
    private TipoMovimentacao tipoMovimentacao;

    @Enumerated
    private OrigemMovimentacao origemMovimentacao;

    private LocalDateTime data;
    private String observacao;
    private Long criadoPorId;

}
