package com.nicolas.Sql.Inserir;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.nicolas.DB.DbConect;

import com.nicolas.Entities.Acompanhamento;
import com.nicolas.Entities.Pizza;
import com.nicolas.Entities.Usuario;

public abstract class InserirItem extends DbConect{
    private Pizza pizza = null;
    private Acompanhamento acompanhamento = null;
    
    protected boolean inserirPizza(){
        try{
            Connection con = StartConection();
            PreparedStatement statement = con.prepareStatement("INSERT INTO pizza(Sabor, nome, Preco, tipo, IdUsuario) VALUES(?, ?, ?, ?, ?)");
            statement.setString(1, pizza.getSabor());
            statement.setString(2, pizza.getNome());
            statement.setDouble(3, pizza.getPreco());
            statement.setString(4, pizza.getTipo());
            statement.setInt(5, Usuario.getUsrId());
            statement.executeUpdate();
            return true;
        } catch(SQLException sqlEx){
            sqlEx.printStackTrace();
            return false;
        }
    }

    protected boolean inserirAcompanhamento() {
        try {
            Connection con = StartConection();
            PreparedStatement statement = con.prepareStatement("INSERT INTO Acompanhamento() VALUES(?, ?, ?, ?)");
            statement.executeQuery();
            return true;
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
            return false;
        }
    }
    
    public Pizza getPizza() {
        return pizza;
    }
    
    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }

    public Acompanhamento getAcompanhamento() {
        return acompanhamento;
    }

    public void setAcompanhamento(Acompanhamento acompanhamento) {
        this.acompanhamento = acompanhamento;
    }

}
