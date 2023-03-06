package com.luisfabricio.cadastrodeusuario.Dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AtualizarUsuarioRequest {
    private Long id;
    private String nome;
    private String codigo;
    private byte[] foto;
}