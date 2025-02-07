package com.nicolas.Exceptions;

public class CampoVazioException extends Exception{
    @Override
    public String getMessage() {
        return "Nenhum campo deve estar vazio";
    }
}
