package com.nicolas.Sql.Inserir;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.nicolas.DB.DbConect;
import com.nicolas.Entities.Pedido;

public class InserirItemPedido extends DbConect{
    private int pzId = 0;
    private int AcomId = 0;
    private String obs = "";
    
    public boolean InserirAcom(){
        try{
            Connection cn = StartConection();
            PreparedStatement stm = cn.prepareStatement("INSERT INTO item(obs, idPedido, IdAcom) VALUES (?, ?, ?)");
            stm.setString(1, this.getObs());
            stm.setInt(2, Pedido.getIdPedido());
            stm.setInt(3, this.getAcomId());
            System.out.println(this.getAcomId());
            System.out.println(this.getObs());
            stm.executeUpdate();
            return true;
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean InserirPizza() {
        try {
            Connection cn = StartConection();
            PreparedStatement stm = cn.prepareStatement("INSERT INTO item(obs, idPedido, IdPizza) VALUES (?, ?, ?)");
            stm.setString(1, this.getObs());
            stm.setInt(2, Pedido.getIdPedido());
            stm.setInt(3, this.getPzId());
            stm.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }


    public String getObs() {
            return obs;
        }

        public void setObs(String obs) {
            this.obs = obs;
        }
    public int getPzId() {
        return pzId;
    }

    public void setPzId(int pzId) {
        this.pzId = pzId;
    }

    public int getAcomId() {
        return AcomId;
    }

    public void setAcomId(int acomId) {
        AcomId = acomId;
    }
}
