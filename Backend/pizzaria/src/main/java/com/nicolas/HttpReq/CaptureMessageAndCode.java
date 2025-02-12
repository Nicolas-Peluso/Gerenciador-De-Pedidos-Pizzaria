package com.nicolas.HttpReq;

import java.util.HashMap;

import com.google.gson.Gson;

public class CaptureMessageAndCode {
    private static String message = "";
    private static int CodeErro = 0;
    private static String tempoDeEspera = "";
    private static double trocoa = 0.0;
    private static double valorTotal = 0.0;


    public static String getMessage() {
        Gson g = new Gson();
        HashMap<String, Object> jsonHashMap = new HashMap<>();
        jsonHashMap.put("Message", message);

        return  g.toJson(jsonHashMap);
    }

    public static String setReturnPedidoJson() {
        Gson g = new Gson();
        HashMap<String, Object> jsonHashMap = new HashMap<>();
        jsonHashMap.put("Tempo de Espera", getTempoDeEspera());
        jsonHashMap.put("troco", getTrocoa());
        jsonHashMap.put("Valor Total", getValorTotal());

        return g.toJson(jsonHashMap);
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

    public static String getTempoDeEspera() {
        return tempoDeEspera;
    }

    public static void setTempoDeEspera(String tempoDeEspera) {
        CaptureMessageAndCode.tempoDeEspera = tempoDeEspera;
    }

    public static double getTrocoa() {
        return trocoa;
    }

    public static void setTrocoa(double trocoa) {
        CaptureMessageAndCode.trocoa = trocoa;
    }

    public static double getValorTotal() {
        return valorTotal;
    }

    public static void setValorTotal(double valorTotal) {
        CaptureMessageAndCode.valorTotal = valorTotal;
    }

}
