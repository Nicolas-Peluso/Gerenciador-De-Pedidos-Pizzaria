package com.nicolas.Exceptions;

public class TokenInvalidoException extends Exception{
    @Override
    public String getMessage() {
        return "Token invalido";
    }
}
