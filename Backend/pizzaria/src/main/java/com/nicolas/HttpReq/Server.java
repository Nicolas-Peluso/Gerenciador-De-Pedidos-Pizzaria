package com.nicolas.HttpReq;
import java.io.IOException;
import java.net.InetSocketAddress;

import com.nicolas.ControleDeEndPoints.CadastraItemControle;
import com.nicolas.ControleDeEndPoints.CadastroControle;
import com.nicolas.ControleDeEndPoints.CadastroPedidoControle;
import com.nicolas.ControleDeEndPoints.LoginControle;
import com.nicolas.ControleDeEndPoints.GetItens;
import com.sun.net.httpserver.HttpServer;

public class Server{

    public void StartServer() throws IOException{

        try{
        HttpServer server = HttpServer.create(new InetSocketAddress(9000), 0);

        server.createContext("/itens", new GetItens());
        server.createContext("/Login", new LoginControle());
        server.createContext("/Cadastro", new CadastroControle());
        server.createContext("/CadastrarItem", new CadastraItemControle());
        server.createContext("/CadastroPedido", new CadastroPedidoControle());
        
        server.start();
        System.out.println("Sever roando em:" + server.getAddress());
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
