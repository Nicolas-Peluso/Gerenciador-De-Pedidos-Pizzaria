package com.nicolas.util.TokenFromHeader;
import java.util.List;
import java.util.Map;

import com.nicolas.Exceptions.TokenNotFoundException;
import com.nicolas.HttpReq.CaptureMessageAndCode;
import com.sun.net.httpserver.HttpExchange;

public class GetTokenFromHeader {
    
    public static String GetToken(HttpExchange exchange){
        Map<String, List<String>> headers = exchange.getRequestHeaders();
        try{
            if (!headers.containsKey("Authorization")) {
                throw new TokenNotFoundException();
            }

            List<String> authHeader = headers.get("Authorization");

            if (authHeader.isEmpty()) {
                throw new TokenNotFoundException();
            }

            String token = authHeader.get(0);

            if (!token.startsWith("Bearer ")) {
                throw new TokenNotFoundException();
            }               
            
            return token.substring(7);

        }catch(TokenNotFoundException e){
            CaptureMessageAndCode.setMessage(e.getMessage());
            CaptureMessageAndCode.setCodeErro(405);
            return "";
        }
    }
}
