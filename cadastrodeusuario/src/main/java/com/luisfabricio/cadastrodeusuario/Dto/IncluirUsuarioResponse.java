package com.luisfabricio.cadastrodeusuario.Dto;

import lombok.Data;

import java.time.Instant;
import java.time.LocalDate;

@Data
public class IncluirUsuarioResponse {
    private Long id;
    private String codigo;
    private String nome;
    private LocalDate dataDeNascimento;
    private Instant dataCadastro;
    private Instant ultimaAtualizacao;
}