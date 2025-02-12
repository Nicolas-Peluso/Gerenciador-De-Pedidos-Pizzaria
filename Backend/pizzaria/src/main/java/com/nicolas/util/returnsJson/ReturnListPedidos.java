package com.nicolas.util.returnsJson;

import java.util.ArrayList;
import java.util.HashMap;

import com.google.gson.Gson;
import com.nicolas.Entities.Item;

public class ReturnListPedidos {

    private static ArrayList<Item> items = null;

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
        return g.toJson(listaI);
    }
    
}
