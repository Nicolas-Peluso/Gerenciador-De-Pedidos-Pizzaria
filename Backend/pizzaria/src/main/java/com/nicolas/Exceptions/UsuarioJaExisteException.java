package com.nicolas.Exceptions;

public class UsuarioJaExisteException extends Exception{

    public UsuarioJaExisteException(){
        super();
    }

    @Override
    public String toString() {
        return "Usuario Ja cadastrado no Sistema, faça login";
    }

    @Override
    public String getMessage() {
        return "Usuario ja Cadastrado";
    }
}
