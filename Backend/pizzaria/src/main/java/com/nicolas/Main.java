package com.nicolas;
import java.sql.Connection;
import java.sql.SQLException;

import com.nicolas.DB.DbConect;

public class Main {
    public static void main(String[] args)throws SQLException{
        Connection cn = DbConect.StartConection();
        
        DbConect.StopConection(cn);
    }
}