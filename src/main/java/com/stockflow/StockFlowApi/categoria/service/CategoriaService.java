package com.stockflow.StockFlowApi.categoria.service;

import com.stockflow.StockFlowApi.categoria.entity.Categoria;
import com.stockflow.StockFlowApi.categoria.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public List<Categoria> listarTodas() {
        return categoriaRepository.findAll();
    }

    public Categoria buscarPorId(Long id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
    }

    public Categoria criar(Categoria categoria) {

        if (categoria.getName() == null || categoria.getName().isBlank()) {
            throw new RuntimeException("Nome da categoria é obrigatório");
        }

        categoria.setDataCadastro(LocalDateTime.now());

        return categoriaRepository.save(categoria);
    }

    public Categoria atualizar(Long id, Categoria dadosAtualizados) {

        Categoria categoria = buscarPorId(id);

        categoria.setName(dadosAtualizados.getName());
        categoria.setDescription(dadosAtualizados.getDescription());
        categoria.setAtivo(dadosAtualizados.isAtivo());

        return categoriaRepository.save(categoria);
    }

    public void deletar(Long id) {

        Categoria categoria = buscarPorId(id);

        categoriaRepository.delete(categoria);
    }
}