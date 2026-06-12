package com.stockflow.StockFlowApi.produto.controller;

import com.stockflow.StockFlowApi.produto.entity.Produto;
import com.stockflow.StockFlowApi.produto.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService produtoService;

    @GetMapping
    public List<Produto> listarTodos() {
        return produtoService.listarTodos();
    }

    @GetMapping("/{id}")
    public Produto buscarPorId(@PathVariable Long id) {
        return produtoService.buscarPorId(id);
    }

    @PostMapping
    public Produto criar(
            @RequestBody Produto produto,
            @RequestParam Long categoriaId) {

        return produtoService.criar(produto, categoriaId);
    }

    @PutMapping("/{id}")
    public Produto atualizar(
            @PathVariable Long id,
            @RequestBody Produto produto,
            @RequestParam Long categoriaId) {

        return produtoService.atualizar(id, produto, categoriaId);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        produtoService.deletar(id);
    }
}