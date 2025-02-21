package com.nicolas.Sql.Deletar;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.nicolas.DB.DbConect;
import com.nicolas.Entities.Usuario;

public class DeletarPizza extends DbConect{
    private int idPizza = 0;

    protected int getIdPizza() {
        return idPizza;
    }

    protected void setIdPizza(int idPizza) {
        this.idPizza = idPizza;
    }

    protected boolean Deletar(){
        Connection cn = null;
        try{
            cn = StartConection();
            PreparedStatement stm = cn.prepareStatement("DELETE FROM pizza WHERE IdPizza = ? AND IdUsuario = ?");
            stm.setInt(1, getIdPizza());
            stm.setInt(2, Usuario.getUsrId());
            stm.executeUpdate();
            return true;
        }catch(SQLException eSqlException){
            System.out.println(eSqlException);
            return false;
        }finally{
            if(cn != null){
                try{
                    StopConection(cn);
                }catch(SQLException c){
                    System.out.println("erro ao encerrar conexão");
                }
            }
        }
    }
}
