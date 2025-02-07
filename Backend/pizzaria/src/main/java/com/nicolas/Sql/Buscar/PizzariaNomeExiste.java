package com.nicolas.Sql.Buscar;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.nicolas.DB.DbConect;

public abstract class PizzariaNomeExiste extends DbConect{
    //Uma unica Pizzaria por nome
    public boolean BuscarPizzariaNome(String PizzariaNome){
        try{
            Connection cn = StartConection();
            PreparedStatement statement = cn.prepareStatement("SELECT nomePizzaria FROM usuario WHERE nomePizzaria = ?");
            statement.setString(1, PizzariaNome);
            ResultSet resultSet = statement.executeQuery();
            if(!resultSet.next()){
                return false;
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return true;
    }

}
