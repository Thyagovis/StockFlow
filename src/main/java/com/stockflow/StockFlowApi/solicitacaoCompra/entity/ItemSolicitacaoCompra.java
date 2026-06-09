package com.stockflow.StockFlowApi.solicitacaoCompra.entity;

import com.stockflow.StockFlowApi.produto.entity.Produto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ItemSolicitacaoCompra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "solicitacao_compra_id")
    private SolicitacaoCompra solicitacaoCompra;

    @OneToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;

    private BigDecimal quantidadeSolicitada;
    private String observacao;

}
