package com.nicolas.Sql.Inserir;
import java.sql.Connection;
import com.nicolas.DB.DbConect;
import com.nicolas.Exceptions.PizzzariaJaCadastradaException;
import com.nicolas.Exceptions.UsuarioJaExisteException;
import com.nicolas.Operacoes.BuscarUsuario.VerificarUsuario;
import com.nicolas.Operacoes.BuscarUsuario.pizzariaNome.pizzariaNome;
import com.nicolas.interfaces.CadastroInterface;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public abstract class InserirUsuario extends DbConect implements CadastroInterface{
    
    private String Query = "";
    protected String nome = "";
    protected String cargo = "";
    protected String email = "";
    protected String senha = "";
    protected String nomePizzaria = "";
    protected String endereco = "";
    protected String telefone = "";

    protected int limiteSaborPizza = 0;

    protected boolean CadastrarUsuario() {
        this.setQuery("INSERT INTO usuario(nome, cargo, limiteSaborPizza, email, senha, nomePizzaria, endereco, telefone) VALUES (? , ? , ?, ?, ?, ?, ?, ?)");
        try{
            //Verifica se o email ja existe
            VerificarUsuario vUsuario = new VerificarUsuario();
            if(vUsuario.UsuarioExiste(email)){
                throw new UsuarioJaExisteException();
            }
            
            try{
                pizzariaNome Pnome = new pizzariaNome();
                if(Pnome.BuscarPizzariaNome(nomePizzaria)){
                    throw new PizzzariaJaCadastradaException();
                }

                try {
                    Connection c = StartConection();
                    PreparedStatement statement = c.prepareStatement(this.getQuery());
                    statement.setString(1, this.getNome());
                    statement.setString(2, this.getCargo());
                    statement.setInt(3, this.getLimiteSaborPizza());
                    statement.setString(4, this.getEmail());
                    statement.setString(5, this.getSenha());
                    statement.setString(6, this.getNomePizzaria());
                    statement.setString(7, this.getEndereco());
                    statement.setString(8, this.getTelefone());

                    statement.executeUpdate();
                    System.out.println("Usuario Cadastrado");
                    return true;
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            } catch(PizzzariaJaCadastradaException exz){
                System.out.println(exz.getMessage());
            }
        } catch(UsuarioJaExisteException ex){
            System.out.println(ex.getMessage());
        }
        return false;
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
