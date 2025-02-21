package com.nicolas.Sql.Buscar;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.nicolas.DB.DbConect;

public class BuscarUserId extends DbConect{

    public int BuscarId(String token){
        Connection cn = null;
        try{
            cn = StartConection();
            PreparedStatement statement = cn.prepareStatement("SELECT IdUsuario FROM usuario WHERE tokenSession =?");
            statement.setString(1, token);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                return rs.getInt("IdUsuario");
            }
        }catch(SQLException w){
            w.printStackTrace();
            return -1;
        }
        return -1;
    }
    
}
