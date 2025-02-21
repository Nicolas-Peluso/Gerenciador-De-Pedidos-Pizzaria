package com.nicolas.Sql.Buscar;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.nicolas.DB.DbConect;

public class PizzariaNome extends DbConect{
    //Uma unica Pizzaria por nome
    public boolean BuscarPizzariaNome(String PizzariaNome){
        try{
            Connection cn = StartConection();
            PreparedStatement statement = cn.prepareStatement("SELECT nomePizzaria FROM usuario WHERE nomePizzaria = ?");
            statement.setString(1, PizzariaNome);
            ResultSet resultSet = statement.executeQuery();
            if(!resultSet.next()){
                StopConection(cn);
                return false;
            }
            StopConection(cn);
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return true;
    }

    public String BuscarPizzariaNomePorToken(String token) {
        try {
            Connection cn = StartConection();
            PreparedStatement statement = cn
                    .prepareStatement("SELECT nomePizzaria FROM usuario WHERE tokenSession = ?");
            statement.setString(1, token);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                StopConection(cn);
                return "";
            }
            return resultSet.getString("nomePizzaria");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return "";
    }

}
