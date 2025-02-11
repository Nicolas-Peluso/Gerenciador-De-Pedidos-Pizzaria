package com.nicolas.Sql.Buscar;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.nicolas.DB.DbConect;

public class BuscarPizza extends DbConect{

    public int BuscarIdPizzaPorNome(String nome){
        try{
            Connection cn = StartConection();
            PreparedStatement stm = cn.prepareStatement("SELECT IdPizza FROM pizza WHERE nome = ?");
            stm.setString(1, nome);
            ResultSet rs = stm.executeQuery();
            if(rs.next()){
                return rs.getInt("IdPizza");
            }
        }catch(SQLException exc){
            exc.printStackTrace();
            return -1;
        }
        return -1;
    }
    
}
