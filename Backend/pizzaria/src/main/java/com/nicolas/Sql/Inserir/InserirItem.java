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
    
    public boolean inserirPizza(){
        try{
            Connection con = StartConection();
            PreparedStatement statement = con.prepareStatement("INSERT INTO pizza(Sabor, nome, Preco, IdUsuario, tipo) VALUES(?, ?, ?, ?, ?)");
            statement.setString(1, this.pizza.getSabor());
            statement.setString(2, this.pizza.getNome());
            statement.setDouble(3, this.pizza.getPreco());
            statement.setInt(4, Usuario.getUsrId());
            statement.setString(5, this.pizza.getTipo());

            statement.executeUpdate();
            setPizza(null);
            return true;
        } catch(SQLException sqlEx){
            sqlEx.printStackTrace();
            return false;
        }
    }

    public boolean inserirAcompanhamento() {
        try {
            Connection con = StartConection();
            PreparedStatement statement = con.prepareStatement("INSERT INTO Acompanhamento(nome, preco, obs, IdUsuario, tipo) VALUES(?, ?, ?, ?, ?)");
            
            statement.setString(1, this.acompanhamento.getNome());
            statement.setDouble(2, this.acompanhamento.getPreco());
            statement.setString(3, this.acompanhamento.getObs());
            statement.setInt(4, Usuario.getUsrId());
            statement.setString(5, this.acompanhamento.getTipo());

            statement.executeUpdate();
            setAcompanhamento(null);
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
