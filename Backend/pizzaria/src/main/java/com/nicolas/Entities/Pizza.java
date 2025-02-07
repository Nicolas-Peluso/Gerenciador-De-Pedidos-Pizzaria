package com.nicolas.Entities;

public class Pizza extends Item{
    private String sabor = "";

    public String getSabor() {
        return sabor;
    }

    public void setSabor(String sabor) {
        this.sabor = sabor;
    }

    @Override
    public boolean CadastrarItem() {
        if(super.inserirPizza()){
            return true;
        }
        return false;
    }

}
