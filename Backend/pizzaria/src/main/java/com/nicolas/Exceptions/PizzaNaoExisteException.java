package com.nicolas.Exceptions;

public class PizzaNaoExisteException extends Exception{
    
    @Override
    public String getMessage(){
        return "Pizza com esse nome nao encontrada";
    }
}
