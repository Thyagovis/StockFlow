package com.stockflow.StockFlowApi.produto.service;

import com.stockflow.StockFlowApi.categoria.entity.Categoria;
import com.stockflow.StockFlowApi.categoria.repository.CategoriaRepository;
import com.stockflow.StockFlowApi.produto.entity.Produto;
import com.stockflow.StockFlowApi.produto.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final CategoriaRepository categoriaRepository;

    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }

    public Produto buscarPorId(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }

    public Produto criar(Produto produto, Long categoriaId) {

        if (produto.getName() == null || produto.getName().isBlank()) {
            throw new RuntimeException("Nome do produto é obrigatório");
        }

        Categoria categoria = categoriaRepository.findById(categoriaId)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        produto.setCategoria(categoria);
        produto.setDataCadastro(LocalDateTime.now());

        return produtoRepository.save(produto);
    }

    public Produto atualizar(Long id, Produto dadosAtualizados, Long categoriaId) {

        Produto produto = buscarPorId(id);

        Categoria categoria = categoriaRepository.findById(categoriaId)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        produto.setName(dadosAtualizados.getName());
        produto.setDescription(dadosAtualizados.getDescription());
        produto.setAtivo(dadosAtualizados.isAtivo());
        produto.setCategoria(categoria);

        return produtoRepository.save(produto);
    }

    public void deletar(Long id) {

        Produto produto = buscarPorId(id);

        produtoRepository.delete(produto);
    }
}