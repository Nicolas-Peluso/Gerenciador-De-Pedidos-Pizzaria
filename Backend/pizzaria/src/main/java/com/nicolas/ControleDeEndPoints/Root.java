package com.nicolas.ControleDeEndPoints;

import java.io.IOException;
import java.io.OutputStream;

import com.nicolas.Entities.Usuario;
import com.nicolas.HttpReq.CaptureMessageAndCode;
import com.nicolas.HttpReq.Handle;
import com.sun.net.httpserver.HttpExchange;

public class Root extends Handle{
    // \
    @Override
    protected void handleRequest(HttpExchange exchange) throws IOException {
            String allString = "Tudo certo";
            
                if (!Usuario.isLogado()) {
                    CaptureMessageAndCode.setCodeErro(405);
                    CaptureMessageAndCode.setMessage("Faça Login");
                    throw new IOException();
                }
                
                // retorna uma lista de pizzas
                exchange.sendResponseHeaders(201, allString.getBytes().length);
                OutputStream os = exchange.getResponseBody();
                os.write(allString.getBytes());
                os.close();
        
    }
}
