package com.nicolas.Sql.Buscar;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.nicolas.DB.DbConect;
import com.nicolas.Entities.Usuario;
import com.nicolas.Exceptions.SaborDePizzaJaExisteException;

public abstract class PizzaSaborNome extends DbConect{

    public boolean VerificaPizzaExiste(String PizzaNome, String PizzaSabor){
        try{
            Connection cn = StartConection();
            PreparedStatement statement = cn.prepareStatement("SELECT Sabor, nome FROM pizza WHERE Sabor = ? AND nome = ? AND IdUsuario = ?");
            statement.setString(1, PizzaSabor);
            statement.setString(2, PizzaNome);
            statement.setInt(3, Usuario.getUsrId());

            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()){
                StopConection(cn);
                throw new SaborDePizzaJaExisteException();
            }

        }catch(SQLException ex){
            ex.printStackTrace();
        } catch(SaborDePizzaJaExisteException pjEx){
            System.err.println(pjEx.getMessage());
            return true;
        }
        return false;
    }
}
