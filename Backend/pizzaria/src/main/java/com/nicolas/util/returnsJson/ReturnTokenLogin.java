package com.nicolas.util.returnsJson;

import java.util.HashMap;

import com.google.gson.Gson;

public class ReturnTokenLogin {
    private static String TokenUsr = "";

    public static void setTokenUsr(String tokenUsr) {
        TokenUsr = tokenUsr;
    }

    public static String getTokenUsr() {
        return TokenUsr;
    }
    
    public static String setTokenUsrRet(String tokenUsr) {
        Gson g = new Gson();
        HashMap<String, Object> tokenUserRets = new HashMap<>();
        tokenUserRets.put("token", getTokenUsr());
        return g.toJson(tokenUserRets);
    }
}
