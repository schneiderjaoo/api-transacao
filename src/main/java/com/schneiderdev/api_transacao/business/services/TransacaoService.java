package com.schneiderdev.api_transacao.business.services;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import com.schneiderdev.api_transacao.controller.dtos.TransacaoRequestDTO;
import com.schneiderdev.api_transacao.infrastructure.exceptions.UnprocessableEntity;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransacaoService {
    
    private final List<TransacaoRequestDTO> listaTransacoes = new ArrayList<>();

    public void adicionarTransacoes(TransacaoRequestDTO dto){

        log.info("Iniciado o processamento de transação");

        if (dto.dataHora().isAfter(OffsetDateTime.now())) {
            log.error("Data e hora maior que a atual");
            throw new UnprocessableEntity("Data e hora maior que a atual");
        }

        log.info("Data e hora válida");

        if (dto.valor() < 0) {
            log.error("Valor menor ou igual a zero");
            throw new UnprocessableEntity("Valor menor ou igual a zero");
        }

        listaTransacoes.add(dto);
        log.info("Transacao adicionada com sucesso");
    }

    public void limparTransacoes(){
        log.info("Iniciado a limpeza de transações");
        listaTransacoes.clear();
        log.info("Transações limpas com sucesso");
    }

    public List<TransacaoRequestDTO> buscarTransacoes(Integer intervalo){
        
    log.info("Iniciado a busca de transações pelo intervalo de tempo: {}", intervalo);
        OffsetDateTime dataHoraIntervalo = OffsetDateTime.now().minusSeconds(intervalo);

        log.info("Retorno da busca de transações pelo intervalo de tempo: {}", dataHoraIntervalo);
        return listaTransacoes.stream()
            .filter(transacoes -> transacoes.dataHora()
                .isAfter(dataHoraIntervalo)).toList();
    }
}
