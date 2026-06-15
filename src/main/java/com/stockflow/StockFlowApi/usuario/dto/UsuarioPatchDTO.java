package com.stockflow.StockFlowApi.usuario.dto;

import com.stockflow.StockFlowApi.shared.costraints.NullOrNotBlank;
import com.stockflow.StockFlowApi.usuario.enums.Cargo;
import jakarta.validation.constraints.Email;

public record UsuarioPatchDTO(

        @NullOrNotBlank
        String nome,

        @NullOrNotBlank(message = "email não pode ser nulo")
        @Email
        String email,

        @NullOrNotBlank
        String login,

        @NullOrNotBlank
        String senha,

        Cargo cargo,

        Boolean ativo

) {
}
