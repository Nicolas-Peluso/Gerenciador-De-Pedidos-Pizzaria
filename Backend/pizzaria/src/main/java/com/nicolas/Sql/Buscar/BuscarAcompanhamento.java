package com.nicolas.Sql.Buscar;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.nicolas.DB.DbConect;

public class BuscarAcompanhamento extends DbConect{

     public int BuscarIdAcompanhamentoPorNome(String nome){
        try{
            Connection cn = StartConection();
            PreparedStatement stm = cn.prepareStatement("SELECT idAcom FROM Acompanhamento WHERE nome = ?");
            stm.setString(1, nome);
            ResultSet rs = stm.executeQuery();
            if(rs.next()){
                return rs.getInt("idAcom");
            }
        }catch(SQLException exc){
            exc.printStackTrace();
            return -1;
        }
        return -1;
    }
    
}
