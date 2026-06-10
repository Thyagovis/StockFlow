package com.stockflow.StockFlowApi.solicitacaoCompra.controller;

import com.stockflow.StockFlowApi.solicitacaoCompra.dto.SolicitacaoCompraRequestDTO;
import com.stockflow.StockFlowApi.solicitacaoCompra.entity.SolicitacaoCompra;
import com.stockflow.StockFlowApi.solicitacaoCompra.service.SolicitacaoCompraService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("solicitacao/compra")
@AllArgsConstructor
@Log4j2
public class SolicitacaoCompraController {

    private final SolicitacaoCompraService solicitacaoCompraService;

    @GetMapping
    public ResponseEntity<List<SolicitacaoCompra>> listAll(){
        return new ResponseEntity<>(solicitacaoCompraService.listAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<SolicitacaoCompra> save(@RequestBody SolicitacaoCompraRequestDTO dto){
        System.out.println("Chegou no controller");
        return new ResponseEntity<>(solicitacaoCompraService.save(dto), HttpStatus.CREATED);
    }
}
