package com.nicolas.Exceptions;

public class LoginEmailSenhaException extends Exception{
    @Override
    public String getMessage() {
        return "email ou senha incorretos, nao foi possivel realizar o login";
    }
}
