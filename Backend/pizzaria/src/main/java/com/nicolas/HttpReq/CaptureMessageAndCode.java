package com.nicolas.HttpReq;

import java.util.HashMap;

import com.google.gson.Gson;

public class CaptureMessageAndCode {
    private static String message = "";
    private static int CodeErro = 0;
    
    public static String getMessage() {
        Gson g = new Gson();
        HashMap<String, Object> jsonHashMap = new HashMap<>();
        jsonHashMap.put("Message", message);

        return  g.toJson(jsonHashMap);
    }

    public static void setMessage(String message) {
        CaptureMessageAndCode.message = message;
    }

    public static int getCodeErro() {
        return CodeErro;
    }

    public static void setCodeErro(int codeErro) {
        CodeErro = codeErro;
    }


}
