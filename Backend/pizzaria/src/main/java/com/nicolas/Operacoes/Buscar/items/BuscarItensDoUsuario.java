package com.nicolas.Operacoes.Buscar.items;

import java.util.ArrayList;

import com.nicolas.Entities.Item;
import com.nicolas.Entities.Usuario;
import com.nicolas.Sql.Buscar.BuscarAcompanhamento;
import com.nicolas.Sql.Buscar.BuscarPizza;
import com.nicolas.util.returnsJson.ReturnListPedidos;

public class BuscarItensDoUsuario {

    public void BuscarItens(String filtro, String token){
        ArrayList<Item> it = new ArrayList<>();
        Usuario.setUsrId(token);

        if(filtro.equals("pizza")){
            BuscarPizza Bpz = new BuscarPizza();
            Bpz.BuscarTodaPizza(it);
        }
        else if(filtro.equals("acompanhamento")){
            BuscarAcompanhamento Ba = new BuscarAcompanhamento();
            Ba.BuscarTodoAcompanhamento(it);
        }else{
            BuscarPizza Bpz = new BuscarPizza();
            BuscarAcompanhamento Ba = new BuscarAcompanhamento();
            
            Ba.BuscarTodoAcompanhamento(it); 
            Bpz.BuscarTodaPizza(it);
        }

        ReturnListPedidos.setItems(it);
    }
}
