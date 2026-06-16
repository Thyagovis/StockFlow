package com.stockflow.StockFlowApi.movimentacao.service;

import com.stockflow.StockFlowApi.movimentacao.dto.ItemMovimentacaoRequestDTO;
import com.stockflow.StockFlowApi.movimentacao.dto.ItemMovimentacaoResponseDTO;
import com.stockflow.StockFlowApi.movimentacao.entity.ItemMovimentacao;
import com.stockflow.StockFlowApi.movimentacao.entity.MovimentacaoLote;
import com.stockflow.StockFlowApi.movimentacao.repository.ItemMovimentacaoRepository;
import com.stockflow.StockFlowApi.movimentacao.repository.MovimentacaoLoteRepository;
import com.stockflow.StockFlowApi.produto.entity.Produto;
import com.stockflow.StockFlowApi.produto.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Data
@Service
@AllArgsConstructor
@Log4j2
@Transactional
public class ItemMovimentacaoService {

    private final ItemMovimentacaoRepository itemMovimentacaoRepository;

    private final MovimentacaoLoteRepository movimentacaoLoteRepository;

    private final ProdutoRepository produtoRepository;

    private ItemMovimentacaoResponseDTO definirDTO(ItemMovimentacao item){

        return new ItemMovimentacaoResponseDTO(
                item.getId(),
                item.getMovimentacaoLote().getId(),
                item.getProduto().getId(),
                item.getQuantidade(),
                item.getCustoUnitario(),
                item.getCustoTotal()
        );
    }

    private ItemMovimentacao findEntityById(Long id){

        return itemMovimentacaoRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Item de movimentação não encontrado"
                ));
    }

    public ItemMovimentacao save(ItemMovimentacaoRequestDTO dto){

        MovimentacaoLote lote =
                movimentacaoLoteRepository.findById(dto.movimentacaoLoteId())
                        .orElseThrow(() -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Movimentação não encontrada"
                        ));

        Produto produto =
                produtoRepository.findById(dto.produtoId())
                        .orElseThrow(() -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Produto não encontrado"
                        ));

        ItemMovimentacao item = new ItemMovimentacao();

        item.setMovimentacaoLote(lote);
        item.setProduto(produto);
        item.setQuantidade(dto.quantidade());
        item.setCustoUnitario(dto.custoUnitario());

        item.setCustoTotal(
                dto.quantidade().multiply(dto.custoUnitario())
        );

        return itemMovimentacaoRepository.save(item);
    }

    public List<ItemMovimentacaoResponseDTO> listAll(){

        return itemMovimentacaoRepository
                .findAll()
                .stream()
                .map(this::definirDTO)
                .toList();
    }

    public ItemMovimentacaoResponseDTO findById(Long id){

        return definirDTO(findEntityById(id));
    }

    public String delete(Long id){

        ItemMovimentacao item = findEntityById(id);

        itemMovimentacaoRepository.delete(item);

        return "Item de movimentação removido com sucesso";
    }

}