package com.luisfabricio.cadastrodeusuario.exceptions;

public class UsuarioDesconhecidoException extends RuntimeException {
    public UsuarioDesconhecidoException(String mensagem) {
        super(mensagem);
    }
}