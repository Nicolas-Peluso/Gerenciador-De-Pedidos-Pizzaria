package com.nicolas.Sql.Inserir;
import java.sql.Connection;
import com.nicolas.DB.DbConect;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public abstract class InserirUsuario extends DbConect{
    
    private String Query = "";
    protected String nome = "";
    protected String cargo = "";
    protected String email = "";
    protected String senha = "";
    protected String nomePizzaria = "";
    protected String endereco = "";
    protected String telefone = "";
    protected String tempoMedioDeDelivery = "";

    protected int limiteSaborPizza = 0;

    protected boolean CadastrarUsuario() {
        this.setQuery("INSERT INTO usuario(nome, cargo, email, senha, nomePizzaria, endereco, telefone) VALUES (? , ? , ?, ?, ?, ?, ?)");
                try {
                    Connection c = StartConection();
                    PreparedStatement statement = c.prepareStatement(this.getQuery());
                    statement.setString(1, this.getNome());
                    statement.setString(2, this.getCargo());
                    statement.setString(3, this.getEmail());
                    statement.setString(4, this.getSenha());
                    statement.setString(5, this.getNomePizzaria());
                    statement.setString(6, this.getEndereco());
                    statement.setString(7, this.getTelefone());

                    statement.executeUpdate();
                    StopConection(c);
                    return true;
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        return false;
    }

    public String getTempoMedioDeDelivery() {
        return tempoMedioDeDelivery;
    }

    public void setTempoMedioDeDelivery(String tempoMedioDeDelivery) {
        this.tempoMedioDeDelivery = tempoMedioDeDelivery;
    }

    public String getNomePizzaria() {
        return nomePizzaria;
    }

    public void setNomePizzaria(String nomePizzaria) {
        this.nomePizzaria = nomePizzaria;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
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
