package com.nicolas.Exceptions;

public class AcompanhamentoExisteException extends Exception{
    @Override
    public String getMessage() {
        return "Ja existe um acompanhamento com esse nome cadastrado, voce pode alter o nome.";
    }
}
