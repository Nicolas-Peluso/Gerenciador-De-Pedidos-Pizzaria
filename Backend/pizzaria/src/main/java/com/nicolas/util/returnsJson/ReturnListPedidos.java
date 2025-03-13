package com.nicolas.util.returnsJson;

import java.util.ArrayList;
import java.util.HashMap;

import com.google.gson.Gson;
import com.nicolas.Entities.Item;

public class ReturnListPedidos {

    private static ArrayList<Item> items = null;
    public static int getPage() {
        return page;
    }

    public static void setPage(int page) {
        ReturnListPedidos.page = page;
    }

    private static int page = 0;
    private static int totalElementos = 0;

    public static ArrayList<Item> getItems() {
        return items;
    }

    public static void setItems(ArrayList<Item> item) {
        items = item;
    }

    public static String returnitensCadastradosLista(){
        Gson g = new Gson();
        HashMap<String, Object> listaI = new HashMap<>();
        listaI.put("itens", getItems());
        listaI.put("mostrando", getPage());
        listaI.put("total", getTotalElementos());
        return g.toJson(listaI);
    }

    public static int getTotalElementos() {
        return totalElementos;
    }

    public static void setTotalElementos(int totalElementos) {
        ReturnListPedidos.totalElementos = totalElementos;
    }
}
