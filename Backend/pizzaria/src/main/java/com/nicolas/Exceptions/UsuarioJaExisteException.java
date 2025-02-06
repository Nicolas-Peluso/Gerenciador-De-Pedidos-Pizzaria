package com.nicolas.Exceptions;

public class UsuarioJaExisteException extends Exception{

    public UsuarioJaExisteException(){
        super();
    }

    @Override
    public String getMessage() {
        return "Usuario ja Cadastrado, faça login";
    }
}
