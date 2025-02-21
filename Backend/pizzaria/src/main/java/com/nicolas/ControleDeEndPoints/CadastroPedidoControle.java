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
import com.nicolas.util.TokenFromHeader.GetTokenFromHeader;
import com.sun.net.httpserver.HttpExchange;

public class CadastroPedidoControle extends Handle{

    @Override
    protected void handleRequest(HttpExchange exchange) throws IOException {
        if("POST".equals(exchange.getRequestMethod())){
            
            String token = GetTokenFromHeader.GetToken(exchange);
            
            if(token.isEmpty()){
                throw new IOException();
            }
            
            if(!Usuario.isLogado(token)){
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
            pd.setPix(jsonO.get("pixPedido").getAsBoolean());
            pd.setPagamentoConfirmado(jsonO.get("pagamentoConfirmadoPedido").getAsBoolean());
            pd.setValorDoCliente(jsonO.get("valorDoCliente").getAsDouble());
            pd.setTempoEspera(Usuario.getTempoMedioDeDelivery());
            
            pd.setPd(pd);

            if(!pd.VerificaCampoPedido()){
                throw new IOException();
            }

            //verifica se os itens sao validos (tipo)
            if(!(pd.ItensPedidoTratamento(jsonO.get("itensPedido").getAsJsonArray()))){
                throw new IOException();
            }

            //Se nenhum erro com os campos, tipos de dados etc... Cadastra o pedido completo em uma trasação onde se nao for possivel cadastrar um vai dar erro
            if(!pd.CadastrarPedidoCompleto(cl, token)){
                throw new IOException();
            }

            pd.CalculaValorTotal();
            pd.CalculaTroco();
            
            CaptureMessageAndCode.setTempoDeEspera(pd.getTempoEspera());
            CaptureMessageAndCode.setValorTotal(pd.getValorTotal());
            CaptureMessageAndCode.setTrocoa(pd.getTroco());
            CaptureMessageAndCode.setReturnPedidoJson();
            CaptureMessageAndCode.setCodeErro(201);

            exchange.sendResponseHeaders(CaptureMessageAndCode.getCodeErro(), CaptureMessageAndCode.setReturnPedidoJson().getBytes().length);
            OutputStream os = exchange.getResponseBody();
            os.write(CaptureMessageAndCode.setReturnPedidoJson().getBytes());
            os.close();

        }else{
            CaptureMessageAndCode.setCodeErro(405);
            CaptureMessageAndCode.setMessage("Apenas conexões post");
            throw new IOException();
        }
        
    }
    
}
