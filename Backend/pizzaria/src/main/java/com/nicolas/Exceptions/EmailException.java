package com.nicolas.Exceptions;

public class EmailException extends Exception{
    
    @Override
    public String getMessage() {
        return "Formato de email esta incorreto Verifique tente novamente";
    }
}
