package com.nicolas;
import java.io.IOException;

import com.nicolas.HttpReq.Server;

public class Main {
    public static void main(String[] args){
        Server s = new Server();
        try{
            s.StartServer();
        }catch(IOException e){
            
        }
    }
}