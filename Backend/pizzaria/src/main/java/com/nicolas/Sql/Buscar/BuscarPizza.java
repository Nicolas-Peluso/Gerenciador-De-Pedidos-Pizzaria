package com.nicolas.Sql.Buscar;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.nicolas.DB.DbConect;
import com.nicolas.Entities.Item;
import com.nicolas.Entities.Pizza;
import com.nicolas.Entities.Usuario;

public class BuscarPizza extends DbConect{

    public int BuscarIdPizzaPorNome(String nome){
        Connection cn = null;
        try{
            cn = StartConection();
            PreparedStatement stm = cn.prepareStatement("SELECT IdPizza FROM pizza WHERE nome = ? AND IdUsuario = ?");
            stm.setString(1, nome);
            stm.setInt(2, Usuario.getUsrId());
            ResultSet rs = stm.executeQuery();
            if(rs.next()){
                return rs.getInt("IdPizza");
            }
        }catch(SQLException exc){
            exc.printStackTrace();
            return -1;
        } finally{
            if(cn != null){
                try {
                    StopConection(cn);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return -1;
    }

    public Pizza BuscarTodaPizza(ArrayList<Item> iArrayList, int page) {
        Connection cn = null;
        System.out.println(page);
        try {
            cn = StartConection();
            PreparedStatement stm = cn.prepareStatement("SELECT * FROM pizza WHERE IdUsuario = ? LIMIT 2 OFFSET ?");
            stm.setInt(1, Usuario.getUsrId());
            stm.setInt(2, page);
            ResultSet rs = stm.executeQuery();

            while(rs.next()) {
                Pizza pz = new Pizza();
                pz.setNome(rs.getString("nome"));
                pz.setPreco(rs.getDouble("Preco"));
                pz.setSabor(rs.getString("Sabor"));
                pz.setId(rs.getInt("IdPizza"));
                pz.setTipo(rs.getString("tipo"));

                iArrayList.add(pz);
                pz = null;
            }

        } catch (SQLException exc) {
            exc.printStackTrace();
            return null;
        } finally {
            if (cn != null) {
                try {
                    StopConection(cn);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public double BuscarPrecoDaPizzaPorNome(String nome){
        Connection cn = null;
        try {
            cn = StartConection();
            PreparedStatement stm = cn.prepareStatement("SELECT Preco FROM pizza WHERE nome = ? AND IdUsuario = ?");
            stm.setString(1, nome);
            stm.setInt(2, Usuario.getUsrId());
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getDouble("Preco");
            }
        } catch (SQLException exc) {
            exc.printStackTrace();
            return -1;
        } finally{
            if(cn != null){
                try {
                    StopConection(cn);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return -1;
    }
    
}
