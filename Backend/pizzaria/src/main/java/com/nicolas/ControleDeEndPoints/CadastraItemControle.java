package com.nicolas.ControleDeEndPoints;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.nicolas.Entities.Pizza;
import com.nicolas.Entities.Usuario;
import com.nicolas.HttpReq.CaptureMessageAndCode;
import com.nicolas.HttpReq.Handle;
import com.nicolas.Operacoes.Cadastro.Produto.CadastrarItem;
import com.sun.net.httpserver.HttpExchange;

public class CadastraItemControle extends Handle{

    @Override
    protected void handleRequest(HttpExchange exchange) throws IOException {
        if("POST".equals(exchange.getRequestMethod())){
            Gson gz = new Gson();
            InputStreamReader streamReader = new InputStreamReader(exchange.getRequestBody());
            JsonObject jsonObject = gz.fromJson(streamReader, JsonObject.class);

            if(!(jsonObject.get("tipo").getAsString().equals("pizza") || jsonObject.get("tipo").getAsString().equals("Acompanhamento"))){
                CaptureMessageAndCode.setMessage("Apenas pizzas e Acompanhamentos podem ser cadastrados");
                CaptureMessageAndCode.setCodeErro(405);
                throw new IOException();
            }

            if(!Usuario.isLogado()){
                throw new IOException();
            }

            if(jsonObject.get("tipo").getAsString().equals("pizza")){
                CadastrarItem item = new CadastrarItem();
                Pizza pizza = new Pizza();

                String NomePizza = jsonObject.get("nome").getAsString();
                String SaborPìzza = jsonObject.get("sabor").getAsString();
                double PrecoPizza = jsonObject.get("preco").getAsDouble();

                if(!pizza.ValidarCamposItem(NomePizza, SaborPìzza, PrecoPizza)){
                    throw new IOException();
                }
                
                pizza.setNome(NomePizza);
                pizza.setSabor(SaborPìzza);
                pizza.setPreco(PrecoPizza);
                
                item.setPizza(pizza);
                
                if(!item.inserirPizza()){
                    CaptureMessageAndCode.setCodeErro(404);
                    CaptureMessageAndCode.setMessage("Algo deu errado na manipulação");
                    throw new IOException();
                }

                CaptureMessageAndCode.setMessage("Item Cadastrado com sucesso");
                CaptureMessageAndCode.setCodeErro(201);
                exchange.sendResponseHeaders(CaptureMessageAndCode.getCodeErro(), CaptureMessageAndCode.getMessage().getBytes().length);
                OutputStream os = exchange.getResponseBody();
                os.write(CaptureMessageAndCode.getMessage().getBytes());
                os.close();
            }

        } else {
            CaptureMessageAndCode.setCodeErro(405);
            CaptureMessageAndCode.setMessage("requisições para esse endpoint deve ser POST");

            throw new IOException();
        }
        
    }
    
}
