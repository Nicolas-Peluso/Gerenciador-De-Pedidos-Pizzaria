package com.nicolas.Exceptions;

public class ExceptionGenerica extends Exception{
    
    @Override
    public String getMessage(){
        return "HOuve um erro inesperado, contate a nossa equipe de suporte";
    }
}
