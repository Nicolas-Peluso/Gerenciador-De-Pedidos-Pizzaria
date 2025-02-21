package com.nicolas.Exceptions;

public class UsuarioLogadoException extends Exception{
    @Override
    public String getMessage() {
        return "Voce precisa estar logado";
    }
}
