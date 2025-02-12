package com.nicolas.Sql.Inserir;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.nicolas.DB.DbConect;
import com.nicolas.Entities.Cliente;
import com.nicolas.Entities.Pedido;

public abstract class InserirPedido extends DbConect{
    private Pedido pd = null;

    public int CadastrarPedido(Connection ExternalConectionTrasaction){
        try{
            Connection cn = ExternalConectionTrasaction;
            PreparedStatement stm = cn.prepareStatement("INSERT INTO pedido(formaDePagamento, valorTotal, dinheiro, PIX, pagamentoConfirmado, idCliente, tempoDeEspera) VALUES(?, ?, ? , ?, ?, ?, ?)"
            , PreparedStatement.RETURN_GENERATED_KEYS);
            stm.setString(1, this.getPd().getFormaDePagamento());
            stm.setDouble(2, this.getPd().getValorTotal());
            stm.setBoolean(3, this.getPd().isDinheiro());
            stm.setBoolean(4, this.getPd().isPix());
            stm.setBoolean(5, this.getPd().isPagamentoConfirmado());
            stm.setInt(6, Cliente.getClienteId());
            stm.setString(7, this.getPd().getTempoEspera());
            stm.executeUpdate();

            ResultSet idPedido = stm.getGeneratedKeys();
            if(idPedido.next()){
                return idPedido.getInt(1);
            }
        }catch(SQLException e){
            e.printStackTrace();
            return -1;
        }
        return -1;
    }
    
    public Pedido getPd() {
        return pd;
    }

    public void setPd(Pedido pd) {
        this.pd = pd;
    }
}
