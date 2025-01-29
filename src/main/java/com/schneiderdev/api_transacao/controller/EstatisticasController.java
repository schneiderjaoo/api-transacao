package com.schneiderdev.api_transacao.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.schneiderdev.api_transacao.business.services.EstatisticasService;
import com.schneiderdev.api_transacao.controller.dtos.EstatisticasResponseDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/estatistica")
@RequiredArgsConstructor
public class EstatisticasController {
    
    public final EstatisticasService estatisticasService;

    @GetMapping
    @Operation(description = "Endpoint responsavel por buscar estatisticas")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Busca efetuada"),
        @ApiResponse(responseCode = "400", description =  "Erro nas estatisticas"),
        @ApiResponse(responseCode = "422", description = "Campos não atendem os requisitos da transação"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<EstatisticasResponseDTO> buscarEstatisticas(
            @RequestParam(value = "intervalo", required = false, defaultValue = "60") Integer intervalo){
        estatisticasService.calcularEstatisticasTransacoes(intervalo);
        return ResponseEntity.ok(
            estatisticasService.calcularEstatisticasTransacoes(intervalo));
    }
}
