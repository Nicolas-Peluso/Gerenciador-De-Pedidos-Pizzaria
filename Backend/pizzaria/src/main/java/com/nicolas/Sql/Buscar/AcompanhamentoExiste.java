package com.nicolas.Sql.Buscar;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.nicolas.DB.DbConect;
import com.nicolas.Entities.Usuario;
import com.nicolas.Exceptions.AcompanhamentoExisteException;

public class AcompanhamentoExiste extends DbConect{
     public boolean VerificaAcompanhamento(String nome){
        try{
            Connection cn = StartConection();
            PreparedStatement statement = cn.prepareStatement("SELECT nome FROM Acompanhamento WHERE nome = ? AND IdUsuario = ?");
            statement.setString(1, nome);
            statement.setInt(2, Usuario.getUsrId());

            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()){
                StopConection(cn);
                throw new AcompanhamentoExisteException();
            }

        }catch(SQLException ex){
            ex.printStackTrace();
        } catch(AcompanhamentoExisteException pjEx){
            return true;
        }
        return false;
    }
}
