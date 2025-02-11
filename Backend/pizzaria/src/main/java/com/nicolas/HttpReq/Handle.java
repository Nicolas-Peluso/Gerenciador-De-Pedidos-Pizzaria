package com.nicolas.HttpReq;
import java.io.IOException;
import java.io.OutputStream;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public abstract class Handle extends CaptureMessageAndCode implements HttpHandler{

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try{
            exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
            exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
            exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type, Authorization");

            if ("OPTIONS".equals(exchange.getRequestMethod())) {
                exchange.sendResponseHeaders(201, -1);
                return;
            }

            handleRequest(exchange);
        }catch(IOException ec){
            exchange.sendResponseHeaders(CaptureMessageAndCode.getCodeErro(), CaptureMessageAndCode.getMessage().getBytes().length);
            OutputStream os = exchange.getResponseBody();
            os.write(getMessage().getBytes());
            os.close();
        }
    }
    
    protected abstract void handleRequest(HttpExchange exchange) throws IOException;
}
