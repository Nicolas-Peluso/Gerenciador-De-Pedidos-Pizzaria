package com.nicolas.Exceptions;

public class TokenNotFoundException extends Exception{
    @Override
    public String getMessage() {
        return "Nao foi encontrado um token no cabeçalho da requisição";
    }
}
