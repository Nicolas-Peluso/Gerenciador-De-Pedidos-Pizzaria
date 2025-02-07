package com.nicolas.Exceptions;

public class PizzzariaJaCadastradaException extends Exception{

    @Override
    public String getMessage(){
        return "Esse nome de pizzaria ja esta sendo utilizada por um usuario, voce pode adicionar Alguma obs para diferenciar a sua Pizzaria de outra ja existente";
    }
    
}