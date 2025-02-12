package com.nicolas.ControleDeEndPoints;

import java.io.IOException;
import java.io.OutputStream;

import com.nicolas.Entities.Usuario;
import com.nicolas.HttpReq.CaptureMessageAndCode;
import com.nicolas.HttpReq.Handle;
import com.nicolas.Operacoes.Buscar.items.BuscarItensDoUsuario;
import com.nicolas.util.returnsJson.ReturnListPedidos;
import com.sun.net.httpserver.HttpExchange;

public class GetItens extends Handle{
    //
    @Override
    protected void handleRequest(HttpExchange exchange) throws IOException {    
        if("GET".equals(exchange.getRequestMethod())){
            if (!Usuario.isLogado()) {
                    throw new IOException();
            }

            BuscarItensDoUsuario buscarItensDoUsuario = new BuscarItensDoUsuario();
            buscarItensDoUsuario.BuscarItens();
            
            exchange.sendResponseHeaders(200, ReturnListPedidos.returnitensCadastradosLista().getBytes().length);
            OutputStream os = exchange.getResponseBody();
            os.write(ReturnListPedidos.returnitensCadastradosLista().getBytes());
            os.close();
        }else{
            CaptureMessageAndCode.setCodeErro(404);
            CaptureMessageAndCode.setMessage("Apenas requisições GET sao aceitas nesse endPoint");
            throw new IOException();
        }        
    }
}
