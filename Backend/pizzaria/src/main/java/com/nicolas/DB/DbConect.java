package com.nicolas.DB;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.nicolas.Constantes.prop;

public class DbConect implements prop{

    public static Connection StartConection(){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(MYSQL_URL);
            System.out.println("Conexão iniciada");
        } catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return conn;
    }

    public static void StopConection(Connection cn) throws SQLException{
        System.out.println("Conexão encerrada");
        cn.close();
    }
}
