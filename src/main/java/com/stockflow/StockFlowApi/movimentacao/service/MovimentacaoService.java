package com.stockflow.StockFlowApi.movimentacao.service;

import com.stockflow.StockFlowApi.movimentacao.dto.MovimentacaoLoteRequestDTO;
import com.stockflow.StockFlowApi.movimentacao.dto.MovimentacaoLoteResponseDTO;
import com.stockflow.StockFlowApi.movimentacao.entity.MovimentacaoLote;
import com.stockflow.StockFlowApi.movimentacao.repository.ItemMovimentacaoRepository;
import com.stockflow.StockFlowApi.movimentacao.repository.MovimentacaoLoteRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Service
@AllArgsConstructor
@Log4j2
@Transactional
public class MovimentacaoService {

    private final MovimentacaoLoteRepository movimentacaoLoteRepository;

    private final ItemMovimentacaoRepository itemMovimentacaoRepository;

    private MovimentacaoLoteResponseDTO definirDTO(
            MovimentacaoLote movimentacaoLote){

        return new MovimentacaoLoteResponseDTO(
                movimentacaoLote.getId(),
                movimentacaoLote.getTipoMovimentacao(),
                movimentacaoLote.getOrigemMovimentacao(),
                movimentacaoLote.getData(),
                movimentacaoLote.getObservacao(),
                movimentacaoLote.getCriadoPorId()
        );
    }

    private MovimentacaoLote findEntityById(Long id){

        return movimentacaoLoteRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Movimentação não encontrada"
                ));
    }

    public MovimentacaoLote save(MovimentacaoLoteRequestDTO dto){

        MovimentacaoLote movimentacao = new MovimentacaoLote();

        movimentacao.setTipoMovimentacao(dto.tipoMovimentacao());
        movimentacao.setOrigemMovimentacao(dto.origemMovimentacao());
        movimentacao.setObservacao(dto.observacao());
        movimentacao.setCriadoPorId(dto.criadoPorId());
        movimentacao.setData(LocalDateTime.now());

        movimentacaoLoteRepository.save(movimentacao);

        return movimentacao;
    }

    public List<MovimentacaoLoteResponseDTO> listAll(){

        return movimentacaoLoteRepository
                .findAll()
                .stream()
                .map(this::definirDTO)
                .toList();
    }

    public MovimentacaoLoteResponseDTO findById(Long id){

        return definirDTO(findEntityById(id));
    }

    public String delete(Long id){

        MovimentacaoLote movimentacao = findEntityById(id);

        movimentacaoLoteRepository.delete(movimentacao);

        return "Movimentação removida com sucesso";
    }

}