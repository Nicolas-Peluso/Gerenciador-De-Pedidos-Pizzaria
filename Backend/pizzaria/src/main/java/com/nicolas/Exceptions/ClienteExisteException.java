package com.nicolas.Exceptions;

public class ClienteExisteException extends Exception{
    @Override 
    public String getMessage(){
        return "ja existe um cliente com esse nome cadastrado, voce pode adicionar um sobrenome aos clientes";
    }
}
