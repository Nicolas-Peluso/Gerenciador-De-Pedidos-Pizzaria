package com.nicolas.Exceptions;

public class AcomNaoExisteException extends Exception{
    @Override
    public String getMessage() {
        return "Acompanhamento nao existe.";
    }
}
