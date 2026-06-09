package com.stockflow.StockFlowApi.usuario.entity;

import com.stockflow.StockFlowApi.usuario.enums.Cargo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;

    @Enumerated
    private Cargo cargo;

    private String name;
    private String login;
    private String password;
    private boolean isAtivo;
    private LocalDateTime dataCadastro;

}
