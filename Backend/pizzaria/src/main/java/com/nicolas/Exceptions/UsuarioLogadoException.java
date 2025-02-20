package com.nicolas.Exceptions;

public class UsuarioLogadoException extends Exception{
    @Override
    public String getMessage() {
        return "token invalido verifique e tente novamente";
    }
}
