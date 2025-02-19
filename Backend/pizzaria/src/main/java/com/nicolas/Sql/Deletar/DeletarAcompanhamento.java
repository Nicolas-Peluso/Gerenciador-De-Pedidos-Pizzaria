package com.nicolas.Sql.Deletar;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.nicolas.DB.DbConect;
import com.nicolas.Entities.Usuario;

public class DeletarAcompanhamento extends DbConect{
    private int idAcom = 0;

    public int getIdAcom() {
        return idAcom;
    }

    public void setIdAcom(int idAcom) {
        this.idAcom = idAcom;
    }

    public boolean DeletarAcom(){
        Connection cn = null;
        try{
            cn = StartConection();
            PreparedStatement statement = cn.prepareStatement("DELETE FROM Acompanhamento WHERE idAcom = ? AND IdUsuario = ?");
            statement.setInt(1, getIdAcom());
            statement.setInt(2, Usuario.getUsrId());
            statement.executeUpdate();
            return true;
        }catch(SQLException exc){
            System.out.println(exc);
            return false;
        }finally{
            try{
                StopConection(cn);
            }catch(SQLException exc){
                System.out.println("Algo deu errado na hora de parar a conexão");
            }
        }
    }
}
