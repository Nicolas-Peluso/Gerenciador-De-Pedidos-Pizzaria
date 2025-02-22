package com.nicolas.ControleDeEndPoints;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.nicolas.HttpReq.CaptureMessageAndCode;
import com.nicolas.HttpReq.Handle;
import com.nicolas.Operacoes.Cadastro.Usuario.CadastraUsuario;
import com.sun.net.httpserver.HttpExchange;

public class CadastroControle extends Handle{

    @Override
    protected void handleRequest(HttpExchange exchange) throws IOException {
        if("POST".equals(exchange.getRequestMethod())){
            Gson gson = new Gson();
            InputStreamReader streamReader = new InputStreamReader(exchange.getRequestBody(), "UTF-8");
            JsonObject json = gson.fromJson(streamReader, JsonObject.class);
            
            CadastraUsuario user = new CadastraUsuario();
            
            user.setNome(json.get("nome").getAsString());
            user.setCargo(json.get("cargo").getAsString());
            
            user.setEmail(json.get("email").getAsString());
            user.setSenha(json.get("senha").getAsString());

            user.setNomePizzaria(json.get("nomePizzaria").getAsString());

            user.setEndereco(json.get("endereco").getAsString());
            user.setTelefone(json.get("telefone").getAsString());

            if(!user.VerificaCampos()){
                throw new IOException();
            }

            boolean CadastroFine = user.Cadastro();
            if(!CadastroFine){
                throw new IOException();
            }

            CaptureMessageAndCode.setMessage("Cadastro realizado com sucesso! Seja bem viado");
            CaptureMessageAndCode.setCodeErro(201);
            exchange.sendResponseHeaders(getCodeErro(), getMessage().getBytes().length);
            OutputStream os = exchange.getResponseBody();
            os.write(CaptureMessageAndCode.getMessage().getBytes());
            os.close();
        }
        
        else{
            CaptureMessageAndCode.setCodeErro(405);
            CaptureMessageAndCode.setMessage("requisições para esse endpoint deve ser POST");

            throw new IOException();
        }
    }
    
}
