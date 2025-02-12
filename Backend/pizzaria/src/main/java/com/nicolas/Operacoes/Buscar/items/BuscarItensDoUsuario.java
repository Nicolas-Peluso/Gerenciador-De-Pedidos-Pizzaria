package com.nicolas.Operacoes.Buscar.items;

import java.util.ArrayList;

import com.nicolas.Entities.Item;
import com.nicolas.Sql.Buscar.BuscarAcompanhamento;
import com.nicolas.Sql.Buscar.BuscarPizza;
import com.nicolas.util.returnsJson.ReturnListPedidos;

public class BuscarItensDoUsuario {

    public void BuscarItens(){
        ArrayList<Item> it = new ArrayList<>();

        BuscarAcompanhamento Ba = new BuscarAcompanhamento();
        BuscarPizza Bpz = new BuscarPizza();
        
        Ba.BuscarTodoAcompanhamento(it);
        Bpz.BuscarTodaPizza(it);

        ReturnListPedidos.setItems(it);
    }
}
