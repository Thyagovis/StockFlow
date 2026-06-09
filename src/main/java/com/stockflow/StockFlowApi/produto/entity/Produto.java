package com.stockflow.StockFlowApi.produto.entity;

import com.stockflow.StockFlowApi.categoria.entity.Categoria;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    private String name;
    private String description;
    private boolean ativo;
    private LocalDateTime dataCadastro;

}
