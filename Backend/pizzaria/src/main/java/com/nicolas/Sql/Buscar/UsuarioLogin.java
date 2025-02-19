package com.nicolas.Sql.Buscar;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.nicolas.DB.DbConect;
import com.nicolas.Entities.Usuario;
import com.nicolas.Exceptions.LoginEmailSenhaException;
import com.nicolas.HttpReq.CaptureMessageAndCode;

public abstract class UsuarioLogin extends DbConect{
    private String emailLogin = "";
    private String senhaLogin = "";

    public boolean Login() {
        try{
            Connection con = StartConection();
            PreparedStatement statement = con.prepareStatement("SELECT nome, cargo, limiteSaborPizza, IdUsuario, nomePizzaria, endereco,"
            +"telefone, tempoMedioDelivery FROM usuario WHERE email = ? AND senha = ?");
            statement.setString(1, this.getEmailLogin());
            statement.setString(2, this.getSenhaLogin());
            ResultSet resultSet = statement.executeQuery();
                if(!resultSet.next()){
                    throw new LoginEmailSenhaException();
                }

            new Usuario(
                resultSet.getInt("IdUsuario"),
                resultSet.getString("nome"),
                resultSet.getString("cargo"),
                resultSet.getInt("limiteSaborPizza"),
                resultSet.getString("nomePizzaria"),
                resultSet.getString("endereco"),
                resultSet.getString("telefone"),
                resultSet.getString("tempoMedioDelivery")
            );
            
            return true;
        } catch(SQLException exc){
            exc.printStackTrace();
        } catch(LoginEmailSenhaException logEx){
            CaptureMessageAndCode.setMessage(logEx.getMessage());
            CaptureMessageAndCode.setCodeErro(405);
        }
        return false;
    }

    public String getEmailLogin() {
        return emailLogin;
    }

    public void setEmailLogin(String emailLogin) {
        this.emailLogin = emailLogin;
    }

    public String getSenhaLogin() {
        return senhaLogin;
    }

    public void setSenhaLogin(String senhaLogin) {
        this.senhaLogin = senhaLogin;
    }

}
