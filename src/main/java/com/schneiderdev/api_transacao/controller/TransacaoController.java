package com.schneiderdev.api_transacao.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.schneiderdev.api_transacao.business.services.TransacaoService;
import com.schneiderdev.api_transacao.controller.dtos.TransacaoRequestDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transacao")
public class TransacaoController {
    
    private final TransacaoService transacaoService;

    @PostMapping
    @Operation(description = "Endpoint para adicionar transações")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Transaçõe gravada com sucesso"),
        @ApiResponse(responseCode = "400", description =  "Erro de requisição"),
        @ApiResponse(responseCode = "422", description = "Campos não atendem os requisitos da transação"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<Void> adicionarTransacao(@RequestBody TransacaoRequestDTO dto){
        transacaoService.adicionarTransacoes(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping
    @Operation(description = "Endpoint para deletar transações")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Transaçõe deletadas com sucesso"),
        @ApiResponse(responseCode = "400", description =  "Erro de requisição"),
        @ApiResponse(responseCode = "422", description = "Campos não atendem os requisitos da transação"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<Void> deletarTransacoes(){
        transacaoService.limparTransacoes();
        return ResponseEntity.ok().build();
    }
}
