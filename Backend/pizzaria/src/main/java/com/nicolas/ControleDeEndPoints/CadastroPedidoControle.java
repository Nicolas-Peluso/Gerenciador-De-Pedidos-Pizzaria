package com.nicolas.ControleDeEndPoints;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.nicolas.Entities.Cliente;
import com.nicolas.Entities.Pedido;
import com.nicolas.Entities.Usuario;
import com.nicolas.HttpReq.CaptureMessageAndCode;
import com.nicolas.HttpReq.Handle;
import com.sun.net.httpserver.HttpExchange;

public class CadastroPedidoControle extends Handle{

    @Override
    protected void handleRequest(HttpExchange exchange) throws IOException {
        if("POST".equals(exchange.getRequestMethod())){
            
            if(!Usuario.isLogado()){
                throw new IOException();
            }
        
            Gson g = new Gson();
            InputStreamReader inputStreamReader = new InputStreamReader(exchange.getRequestBody(), "UTF-8");
            JsonObject jsonO = g.fromJson(inputStreamReader, JsonObject.class);

            Cliente cl = new Cliente();

            cl.setNome(jsonO.get("nomeCliente").getAsString());
            cl.setBairro(jsonO.get("bairroCliente").getAsString());
            cl.setObs(jsonO.get("obsCliente").getAsString());
            cl.setNumeroResidencia(jsonO.get("numeroCasaCliente").getAsInt());
            cl.setNomeRua(jsonO.get("nomeRuaCliente").getAsString());
            cl.setAptoNumero(jsonO.get("aptoCliente").getAsInt());
            
            cl.setCliente(cl);

            if(!cl.VerificaCampoCliente()){
                throw new IOException();
            }

            //Verifica pedido
            Pedido pd = new Pedido();

            pd.setFormaDePagamento(jsonO.get("formaDePagamentoPedido").getAsString());
            pd.setDinheiro(jsonO.get("dinherioPedido").getAsBoolean());
            pd.setValorTotal(jsonO.get("valorTotalPedido").getAsDouble());
            pd.setTempoEspera(jsonO.get("tempoDeEsperaPedido").getAsString());
            pd.setPix(jsonO.get("pixPedido").getAsBoolean());
            pd.setPagamentoConfirmado(jsonO.get("pagamentoConfirmadoPedido").getAsBoolean());

            pd.setPd(pd);

            if(!pd.VerificaCampoPedido()){
                throw new IOException();
            }

            pd.ItensPedidoTratamento(jsonO.get("itensPedido").getAsJsonArray());

            if(!cl.Cadastrar()){
                throw new IOException();
            }

            if(!pd.Cadastrar()){
                throw new IOException();
            }

            pd.InserirItensDoPedido();

            CaptureMessageAndCode.setMessage("Pedido Cadastrado com sucesso");
            CaptureMessageAndCode.setCodeErro(201);
            exchange.sendResponseHeaders(CaptureMessageAndCode.getCodeErro(), CaptureMessageAndCode.getMessage().getBytes().length);
            OutputStream os = exchange.getResponseBody();
            os.write(CaptureMessageAndCode.getMessage().getBytes());
            os.close();

        }else{
            CaptureMessageAndCode.setCodeErro(405);
            CaptureMessageAndCode.setMessage("Apenas conexões post");
            throw new IOException();
        }
        
    }
    
}
