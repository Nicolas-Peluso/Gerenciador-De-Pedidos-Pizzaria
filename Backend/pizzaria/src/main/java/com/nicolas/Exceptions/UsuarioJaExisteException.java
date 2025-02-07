package com.nicolas.Exceptions;

public class UsuarioJaExisteException extends Exception{

    public UsuarioJaExisteException(){
        super();
    }

    @Override
    public String getMessage() {
        return "Esse email ja esta sendo usado, faça login";
    }
}
