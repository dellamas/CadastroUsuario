package com.luisfabricio.cadastrodeusuario.Dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.LocalDate;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class IncluirUsuarioRequest {
    private Long id;
    private String nome;
    private String codigo;
    private LocalDate dataDeNascimento;
    private byte[] foto;
}