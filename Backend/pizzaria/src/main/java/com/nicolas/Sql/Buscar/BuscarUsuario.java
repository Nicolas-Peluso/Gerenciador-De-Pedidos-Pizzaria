package com.nicolas.Sql.Buscar;
import java.sql.Connection;
import com.nicolas.DB.DbConect;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException; 

public abstract class BuscarUsuario extends DbConect{
    //Regra do sistema um usuario por restaurante
    public boolean UsuarioExiste(String email){
        try{
            Connection con = StartConection();
            PreparedStatement statement = con.prepareStatement("SELECT email FROM usuario WHERE email = ?");
            statement.setString(1, email);
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
