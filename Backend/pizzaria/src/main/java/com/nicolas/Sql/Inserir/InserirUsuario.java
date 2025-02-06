package com.nicolas.Sql.Inserir;
import java.sql.Connection;
import com.nicolas.DB.DbConect;
import com.nicolas.interfaces.CadastroInterface;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public abstract class InserirUsuario extends DbConect implements CadastroInterface{
    private String Query = "";
    String nome = "";
    String cargo = "";
    int limiteSaborPizza = 0;

    protected boolean CadastrarUsuario() {
        this.setQuery("INSERT INTO usuario(nome, cargo, limiteSaborPizza) VALUES (? , ? , ?)");

        try {
            Connection c = StartConection();
            PreparedStatement statement = c.prepareStatement(this.getQuery());
            statement.setString(1, this.getNome());
            statement.setString(2, this.getCargo());
            statement.setInt(3, this.getLimiteSaborPizza());
            statement.executeUpdate();
            System.out.println("Usuario Cadastrado");
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public int getLimiteSaborPizza() {
        return limiteSaborPizza;
    }

    public void setLimiteSaborPizza(int limiteSaborPizza) {
        this.limiteSaborPizza = limiteSaborPizza;
    }

    public String getQuery() {
        return Query;
    }

    public void setQuery(String query) {
        Query = query;
    }
}
