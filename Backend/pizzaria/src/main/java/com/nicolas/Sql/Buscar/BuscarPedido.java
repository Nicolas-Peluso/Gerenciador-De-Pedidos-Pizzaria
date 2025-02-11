package com.nicolas.Sql.Buscar;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.nicolas.DB.DbConect;

public class BuscarPedido extends DbConect{
    /**
     * @return
     * Retorna o Id Do cliente ou -1 se nao for achado
     */
    public int PedidoId(int ClientId){
        try {
            Connection cn = StartConection();
            PreparedStatement stm = cn.prepareStatement("SELECT idPedido FROM pedido WHERE idCliente = ?");
            stm.setInt(1, ClientId);
            ResultSet rs = stm.executeQuery();
            if(rs.next()){
                return rs.getInt("idPedido");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
