package com.stockflow.StockFlowApi.shared.exceptions;


import java.time.LocalDateTime;

public record ErrorMessageResponse(

        LocalDateTime timestamp,
        Integer status,
        String error,
        String message,
        String path

) {
}
