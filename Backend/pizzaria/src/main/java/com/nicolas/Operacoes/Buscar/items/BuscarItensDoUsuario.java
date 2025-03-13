package com.nicolas.Operacoes.Buscar.items;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.nicolas.Entities.Item;
import com.nicolas.Entities.Usuario;
import com.nicolas.HttpReq.CaptureMessageAndCode;
import com.nicolas.Sql.Buscar.BuscarAcompanhamento;
import com.nicolas.Sql.Buscar.BuscarPizza;
import com.nicolas.util.returnsJson.ReturnListPedidos;

public class BuscarItensDoUsuario {

    private String Filtro;
    private int Limite;

    public boolean getFiltroELimiteDaUrl(String url){

        Pattern pattern;
        Matcher matcher;

        String regFiltro = "filtro=([a-zA-Z]+)";
        String reg = "limite=(\\d+)";

        try {
            pattern = Pattern.compile(regFiltro);
            matcher = pattern.matcher(url);

            if(!matcher.find()){
               throw new Exception("Não foi possível encontrar o filtro na url");
            }

            setFiltro(matcher.group(1));

            pattern = Pattern.compile(reg);
            matcher = pattern.matcher(url);

            if(!matcher.find()){
               throw new Exception("Não foi possível encontrar o valor do Limite na url");
            }

            setLimite(Integer.parseInt(matcher.group(1)));

            if(getLimite() <= 0){
                throw new Exception("Você deve enviar apenas valores positivos");
            }
            return true;

        } catch (Exception ex){
            CaptureMessageAndCode.setMessage(ex.getMessage());
            CaptureMessageAndCode.setCodeErro(400);
            return false;
        }
    }

    public void BuscarItens(String token){
        ArrayList<Item> it = new ArrayList<>();
        Usuario.setUsrId(token);

        if(getFiltro().equals("pizza")){
            BuscarPizza Bpz = new BuscarPizza();
            Bpz.BuscarTodaPizza(it, (getLimite() - 1) * 2);
        }
        else if(getFiltro().equals("acompanhamento")){
            BuscarAcompanhamento Ba = new BuscarAcompanhamento();
            Ba.BuscarTodoAcompanhamento(it, (getLimite() - 1) * 2);
        }else{
            BuscarPizza Bpz = new BuscarPizza();
            BuscarAcompanhamento Ba = new BuscarAcompanhamento();

            Ba.BuscarTodoAcompanhamento(it, (getLimite() - 1) * 2);
            Bpz.BuscarTodaPizza(it, (getLimite() - 1) * 2);
        }

        ReturnListPedidos.setItems(it);

        if(!ReturnListPedidos.getItems().isEmpty()){
            ReturnListPedidos.setPage(getLimite());
        }
        else {
            ReturnListPedidos.setPage(getLimite());
        }
    }

    public int getLimite() {
        return Limite;
    }

    public void setLimite(int limite) {
        Limite = limite;
    }

    public String getFiltro() {
        return Filtro;
    }

    public void setFiltro(String filtro) {
        Filtro = filtro;
    }
}
