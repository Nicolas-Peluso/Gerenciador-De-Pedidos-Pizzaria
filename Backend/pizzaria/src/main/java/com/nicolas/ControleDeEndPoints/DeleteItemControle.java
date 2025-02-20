package com.nicolas.ControleDeEndPoints;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.nicolas.Entities.Usuario;
import com.nicolas.HttpReq.CaptureMessageAndCode;
import com.nicolas.HttpReq.Handle;
import com.nicolas.Operacoes.Deletar.Acompanhamento.DeletarAcomOp;
import com.nicolas.Operacoes.Deletar.pizza.DeletarPizzaOp;
import com.nicolas.util.TokenFromHeader.GetTokenFromHeader;
import com.sun.net.httpserver.HttpExchange;

public class DeleteItemControle extends Handle{

    @Override
    protected void handleRequest(HttpExchange exchange) throws IOException {
           if("DELETE".equals(exchange.getRequestMethod())){
                 String token = GetTokenFromHeader.GetToken(exchange);
            
            if(token.isEmpty()){
                throw new IOException();
            }

            if(!Usuario.isLogado(token)){
                throw new IOException();
            }

                Gson j = new Gson();
                InputStreamReader inputStreamReader = new InputStreamReader(exchange.getRequestBody(), "UTF-8");
                JsonObject jObject = j.fromJson(inputStreamReader, JsonObject.class);

                String tipo = jObject.get("tipo").getAsString();

                if(!tipo.equals("pizza") && !tipo.equals("Acompanhamento")){
                    CaptureMessageAndCode.setMessage("Voce deve deletar pizzas e acompanhamento, tipo de item invalido");
                    CaptureMessageAndCode.setCodeErro(405);
                    throw new IOException();
                }

                if(tipo.equals("pizza")){
                    String NomePizzaADeletar = jObject.get("nome").getAsString();

                    DeletarPizzaOp delpzOp = new DeletarPizzaOp();
                    delpzOp.setNomePZ(NomePizzaADeletar);

                    if (!delpzOp.VerificaNome(new String[] {NomePizzaADeletar})) {
                        CaptureMessageAndCode.setMessage("Nome da pizza nao pode ser vazio");
                        CaptureMessageAndCode.setCodeErro(405);
                        throw new IOException();
                    }

                    boolean delRes = delpzOp.DeletarPzOP();

                    if(!delRes){
                        throw new IOException();
                    }

                    CaptureMessageAndCode.setMessage("Pizza Deletado com sucesso");
                    CaptureMessageAndCode.setCodeErro(200);
                    exchange.sendResponseHeaders(CaptureMessageAndCode.getCodeErro(), CaptureMessageAndCode.getMessage().getBytes().length);
                    OutputStream os = exchange.getResponseBody();
                    os.write(CaptureMessageAndCode.getMessage().getBytes());
                    os.close();
                }

                String NomeAcomDeletar = jObject.get("nome").getAsString();

                DeletarAcomOp delAcomOp = new DeletarAcomOp();
                delAcomOp.setNomeAcom(NomeAcomDeletar);

                if (!delAcomOp.VerificaNome(new String[] {NomeAcomDeletar})) {
                    CaptureMessageAndCode.setMessage("Nome Do acompanhamento nao pode ser vazio");
                    CaptureMessageAndCode.setCodeErro(405);
                    throw new IOException();
                }

                boolean delRes = delAcomOp.DeletarAcomOP();

                if (!delRes) {
                    throw new IOException();
                }

                CaptureMessageAndCode.setMessage("Acompanhamento Deletado com sucesso");
                CaptureMessageAndCode.setCodeErro(200);
                exchange.sendResponseHeaders(CaptureMessageAndCode.getCodeErro(),CaptureMessageAndCode.getMessage().getBytes().length);
                OutputStream os = exchange.getResponseBody();
                os.write(CaptureMessageAndCode.getMessage().getBytes());
                os.close();   

           } else{
                CaptureMessageAndCode.setMessage("Apenas requisições delete devem ser feitas para esse endpoint");
                CaptureMessageAndCode.setCodeErro(404);
                throw new IOException();
            }
    }
}
