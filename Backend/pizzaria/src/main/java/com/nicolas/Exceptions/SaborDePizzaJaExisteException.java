package com.nicolas.Exceptions;

public class SaborDePizzaJaExisteException extends Exception{
    @Override
    public String getMessage() {
        return "Pizza com esse nome ja cadastrada";
    }
}
