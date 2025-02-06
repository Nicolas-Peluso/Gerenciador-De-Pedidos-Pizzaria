package com.nicolas.Sql.Buscar;
import java.sql.Connection;
import com.nicolas.DB.DbConect;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException; 

public abstract class BuscarUsuario extends DbConect{
    //Regra do sistema um usuario por restaurante
    public boolean UsuarioExiste(){
        try{
            Connection con = StartConection();
            PreparedStatement statement = con.prepareStatement("SELECT * FROM usuario");
            ResultSet rs = statement.executeQuery();
            if(!rs.next()){
                return false;
        }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return true;
    }
}
