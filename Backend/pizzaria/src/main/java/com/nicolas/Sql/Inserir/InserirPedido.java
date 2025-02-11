package com.nicolas.Sql.Inserir;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.nicolas.DB.DbConect;
import com.nicolas.Entities.Cliente;
import com.nicolas.Entities.Pedido;

public abstract class InserirPedido extends DbConect{
    private Pedido pd = null;

    public boolean CadastrarPedido(){
        try{
            Connection cn = StartConection();
            PreparedStatement stm = cn.prepareStatement("INSERT INTO pedido(formaDePagamento, valorTotal, dinheiro, PIX, pagamentoConfirmado, idCliente, tempoDeEspera) VALUES(?, ?, ? , ?, ?, ?, ?)");
            stm.setString(1, this.getPd().getFormaDePagamento());
            stm.setDouble(2, this.getPd().getValorTotal());
            stm.setBoolean(3, this.getPd().isDinheiro());
            stm.setBoolean(4, this.getPd().isPix());
            stm.setBoolean(5, this.getPd().isPagamentoConfirmado());
            stm.setInt(6, Cliente.getClienteId());
            stm.setString(7, this.getPd().getTempoEspera());
            stm.executeUpdate();
            return true;
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }
    
    public Pedido getPd() {
        return pd;
    }

    public void setPd(Pedido pd) {
        this.pd = pd;
    }
}
