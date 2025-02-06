package com.nicolas.DB;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.nicolas.Constantes.prop;

public abstract class DbConect implements prop{

    protected Connection StartConection(){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(MYSQL_URL);

        } catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return conn;
    }

    protected void StopConection(Connection cn) throws SQLException{
        cn.close();
    }
}
