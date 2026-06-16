package com.stockflow.StockFlowApi.movimentacao.controller;

import com.stockflow.StockFlowApi.movimentacao.dto.ItemMovimentacaoRequestDTO;
import com.stockflow.StockFlowApi.movimentacao.dto.ItemMovimentacaoResponseDTO;
import com.stockflow.StockFlowApi.movimentacao.entity.ItemMovimentacao;
import com.stockflow.StockFlowApi.movimentacao.service.ItemMovimentacaoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/itens-movimentacao")
@AllArgsConstructor
public class ItemMovimentacaoController {

    private final ItemMovimentacaoService itemMovimentacaoService;

    @PostMapping
    public ResponseEntity<ItemMovimentacao> save(
            @RequestBody ItemMovimentacaoRequestDTO dto){

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(itemMovimentacaoService.save(dto));
    }

    @GetMapping
    public ResponseEntity<List<ItemMovimentacaoResponseDTO>> listAll(){

        return ResponseEntity.ok(
                itemMovimentacaoService.listAll()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemMovimentacaoResponseDTO> findById(
            @PathVariable Long id){

        return ResponseEntity.ok(
                itemMovimentacaoService.findById(id)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(
            @PathVariable Long id){

        return ResponseEntity.ok(
                itemMovimentacaoService.delete(id)
        );
    }

}
