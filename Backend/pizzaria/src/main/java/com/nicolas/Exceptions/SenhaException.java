package com.nicolas.Exceptions;

public class SenhaException extends Exception{
    private String Msg = "";

    public String getMsg() {
        return Msg;
    }

    public void setMsg(String msg) {
        Msg = msg;
    }

    public SenhaException(String msg){
        this.Msg = msg;
    }

    @Override
    public String getMessage() {
        return this.getMsg();
    }
}
