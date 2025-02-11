package com.nicolas.Sql.Inserir;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.nicolas.DB.DbConect;
import com.nicolas.Entities.Cliente;
import com.nicolas.Entities.Usuario;

public abstract class InserirCliente extends DbConect{
    private Cliente cliente = null;
    
    public boolean CadastrarCliente(){
        try {
            Connection cn = StartConection();
            PreparedStatement sta = cn.prepareStatement("INSERT INTO cliente(nome, numeroResidencia, obs, nomeRua, aptoNumero, bairro, IdUsuario) VALUES(?, ? , ? , ?, ?, ?, ?)");
            sta.setString(1, this.getCliente().getNome());
            sta.setInt(2, this.getCliente().getNumeroResidencia());
            sta.setString(3, this.getCliente().getObs());
            sta.setString(4, this.getCliente().getNomeRua());
            sta.setInt(5, this.getCliente().getAptoNumero());
            sta.setString(6, this.getCliente().getBairro());
            sta.setInt(7, Usuario.getUsrId());
            sta.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
