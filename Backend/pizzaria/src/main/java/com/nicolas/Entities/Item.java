package com.nicolas.Entities;
import com.nicolas.Operacoes.Cadastro.Produto.CadastrarItem;

public abstract class Item extends CadastrarItem{
    private String obs = "";
    private String nome = "";
    private double preco;
    private String tipo = "";
    private int Id = 0;

    public int getId() {
            return Id;
        }

        public void setId(int id) {
            Id = id;
    }


    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public abstract boolean CadastrarItem();

    public Pizza BuscarPizza(String nome){
        Pizza e = null;
        return e;
    }

    public Acompanhamento BuscarAcompanhamento(String nome) {
        Acompanhamento e = null;
        return e;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
}
