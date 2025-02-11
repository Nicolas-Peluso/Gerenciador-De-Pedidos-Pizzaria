package com.nicolas.Sql.Buscar;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.nicolas.DB.DbConect;

public class ClienteBusca extends DbConect{
    private String nome = "";

    /**
     * @return
     * Retorna o Id Do cliente ou -1 se nao for achado
     */
    public int ClienteId(){
        try {
            Connection cn = StartConection();
            PreparedStatement stm = cn.prepareStatement("SELECT idCliente FROM cliente WHERE nome = ?");
            stm.setString(1, this.getNome());
            ResultSet rs = stm.executeQuery();
            if(rs.next()){
                return rs.getInt("idCliente");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
