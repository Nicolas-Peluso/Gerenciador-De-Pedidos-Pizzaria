package com.nicolas.Exceptions;

public class UsuarioLogadoException extends Exception{
    @Override
    public String getMessage() {
        return "é preciso estar logado para realizar essa operação!";
    }
}
