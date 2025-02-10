package com.nicolas.HttpReq;
import java.io.IOException;
import java.net.InetSocketAddress;

import com.nicolas.ControleDeEndPoints.Root;
import com.sun.net.httpserver.HttpServer;

public class Server{

    public void StartServer() throws IOException{

        try{
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        server.createContext("/", new Root());
        server.start();
        System.out.println("Sever roando em:" + server.getAddress());
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
