package com.nicolas.Sql.Buscar;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.nicolas.DB.DbConect;
import com.nicolas.Entities.Acompanhamento;
import com.nicolas.Entities.Item;
import com.nicolas.Entities.Usuario;

public class BuscarAcompanhamento extends DbConect{

     public int BuscarIdAcompanhamentoPorNome(String nome){
        Connection cn = null;
        try{
            cn = StartConection();
            PreparedStatement stm = cn.prepareStatement("SELECT idAcom FROM Acompanhamento WHERE nome = ? AND IdUsuario = ?");
            stm.setString(1, nome);
            stm.setInt(2, Usuario.getUsrId());
            ResultSet rs = stm.executeQuery();
            if(rs.next()){
                return rs.getInt("idAcom");
            }
        }catch(SQLException exc){
            exc.printStackTrace();
            return -1;
        }finally{
            if(cn != null){
                try{
                    StopConection(cn);
                } catch(SQLException e){
                    e.printStackTrace();
                }
            }
        }
        return -1;
    }

    public Acompanhamento BuscarTodoAcompanhamento(ArrayList<Item> itemArrayList) {
        Connection cn = null;
        try {
            cn = StartConection();
            PreparedStatement stm = cn.prepareStatement("SELECT * FROM Acompanhamento WHERE IdUsuario = ?");
            stm.setInt(1, Usuario.getUsrId());
            ResultSet rs = stm.executeQuery();
            
            while (rs.next()) {
                Acompanhamento Acom = new Acompanhamento();
                Acom.setNome(rs.getString("nome"));
                Acom.setPreco(rs.getDouble("Preco"));
                Acom.setObs(rs.getString("obs"));
                Acom.setId(rs.getInt("idAcom"));
                Acom.setTipo(rs.getString("tipo"));
                
                itemArrayList.add(Acom);

                Acom = null;
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

    public double BuscarPrecoAcompanhamentoPorNome(String nome) {
        Connection cn = null;
        try {
            cn = StartConection();
            PreparedStatement stm = cn.prepareStatement("SELECT preco FROM Acompanhamento WHERE nome = ?");
            stm.setString(1, nome);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getDouble("preco");
            }
        } catch (SQLException exc) {
            exc.printStackTrace();
            return -1;
        } finally {
            if (cn != null) {
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
