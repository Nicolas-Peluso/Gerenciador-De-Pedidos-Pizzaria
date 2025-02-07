package com.nicolas.Sql.Buscar;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.nicolas.DB.DbConect;
import com.nicolas.Exceptions.LoginEmailSenhaException;

public abstract class UsuarioLogin extends DbConect{
    private String emailLogin = "";
    private String senhaLogin = "";

    private String nomeUsr = "";
    private String cargoUsr = "";
    private String nomePizzariaUsr = "";
    private String enderecoUsr = "";
    
    private int limiteSaborPizza;

    private String telefoneUsr = "";
    private int UsrId;

    public boolean Login() {
        try{
            Connection con = StartConection();
            PreparedStatement statement = con.prepareStatement("SELECT nome, cargo, limiteSaborPizza, IdUsuario, nomePizzaria, endereco,"
            +"telefone FROM usuario WHERE email = ? AND senha = ?");
            statement.setString(1, this.getEmailLogin());
            statement.setString(2, this.getSenhaLogin());
            ResultSet resultSet = statement.executeQuery();
                if(!resultSet.next()){
                    throw new LoginEmailSenhaException();
                }
            this.setCargoUsr(resultSet.getString("cargo"));
            this.setNomeUsr(resultSet.getString("nome"));
            this.setLimiteSaborPizza(resultSet.getInt("limiteSaborPizza"));
            this.setUsrId(resultSet.getInt("IdUsuario"));
            this.setNomePizzariaUsr(resultSet.getString("nomePizzaria"));
            this.setEnderecoUsr(resultSet.getString("endereco"));
            this.setTelefoneUsr(resultSet.getString("telefone"));
            return true;
        } catch(SQLException exc){
            exc.printStackTrace();
        } catch(LoginEmailSenhaException logEx){
            System.err.println(logEx.getMessage());
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

    public String getNomeUsr() {
        return nomeUsr;
    }

    public void setNomeUsr(String nomeUsr) {
        this.nomeUsr = nomeUsr;
    }

    public String getCargoUsr() {
        return cargoUsr;
    }

    public void setCargoUsr(String cargoUsr) {
        this.cargoUsr = cargoUsr;
    }

    public String getNomePizzariaUsr() {
        return nomePizzariaUsr;
    }

    public void setNomePizzariaUsr(String nomePizzariaUsr) {
        this.nomePizzariaUsr = nomePizzariaUsr;
    }

    public String getEnderecoUsr() {
        return enderecoUsr;
    }

    public void setEnderecoUsr(String enderecoUsr) {
        this.enderecoUsr = enderecoUsr;
    }

    public int getLimiteSaborPizza() {
        return limiteSaborPizza;
    }

    public void setLimiteSaborPizza(int limiteSaborPizza) {
        this.limiteSaborPizza = limiteSaborPizza;
    }

    public String getTelefoneUsr() {
        return telefoneUsr;
    }

    public void setTelefoneUsr(String telefoneUsr) {
        this.telefoneUsr = telefoneUsr;
    }

    public int getUsrId() {
        return UsrId;
    }

    public void setUsrId(int usrId) {
        UsrId = usrId;
    }
   
}
