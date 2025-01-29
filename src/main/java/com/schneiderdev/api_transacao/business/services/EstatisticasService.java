package com.schneiderdev.api_transacao.business.services;

import java.util.DoubleSummaryStatistics;
import java.util.List;

import org.springframework.stereotype.Service;

import com.schneiderdev.api_transacao.controller.dtos.EstatisticasResponseDTO;
import com.schneiderdev.api_transacao.controller.dtos.TransacaoRequestDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class EstatisticasService {
    
    public final TransacaoService transacaoService;
    
    public EstatisticasResponseDTO calcularEstatisticasTransacoes(Integer intervalo) {

        log.info("Calculando estatísticas das transações pelo periodo de tempo: ", intervalo);
        List<TransacaoRequestDTO> transacoes = transacaoService.buscarTransacoes(intervalo);

        DoubleSummaryStatistics estatisticasTransacoes = transacoes.stream()
            .mapToDouble(TransacaoRequestDTO::valor).summaryStatistics();
        log.info("Estatísticas calculadas: {}", estatisticasTransacoes);
        return new EstatisticasResponseDTO(estatisticasTransacoes.getCount(), 
            estatisticasTransacoes.getSum(), 
            estatisticasTransacoes.getAverage(), 
            estatisticasTransacoes.getMin(), 
            estatisticasTransacoes.getMax());
    }
}
