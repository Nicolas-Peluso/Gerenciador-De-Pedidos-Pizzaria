package com.nicolas.ControleDeEndPoints;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.nicolas.HttpReq.CaptureMessageAndCode;
import com.nicolas.HttpReq.Handle;
import com.nicolas.Operacoes.Login.Login;
import com.sun.net.httpserver.HttpExchange;

public class LoginControle extends Handle{
    // /Login
    @Override
    protected void handleRequest(HttpExchange exchange) throws IOException {
        if("POST".equals(exchange.getRequestMethod())){
            Login lg = new Login();
            Gson geson = new Gson();
            InputStreamReader streamReader = new InputStreamReader(exchange.getRequestBody(), "UTF-8");
            JsonObject jsonObject = geson.fromJson(streamReader, JsonObject.class);
            
            lg.setEmailLogin(jsonObject.get("email").getAsString());
            lg.setSenhaLogin((jsonObject.get("senha").getAsString()));
            
            if (!lg.VerificaCamposLogin()) {
                throw new IOException();
            }

            boolean LoginSetFine = lg.Login();
            if(!LoginSetFine){
                throw new IOException();
            }

            lg.UserLogado();

            CaptureMessageAndCode.setCodeErro(201);
            exchange.sendResponseHeaders(getCodeErro(), -1);
            OutputStream os = exchange.getResponseBody();
            os.close();
        }
        else {
            CaptureMessageAndCode.setCodeErro(405);
            CaptureMessageAndCode.setMessage("requisições para esse endpoint deve ser POST");
            throw new IOException();
        }
    }
}
