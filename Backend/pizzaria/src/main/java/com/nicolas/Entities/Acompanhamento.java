package com.nicolas.Entities;

public class Acompanhamento extends Item{
    private String obsAcomp = "";

    public String getObsAcomp() {
        return obsAcomp;
    }

    public void setObsAcomp(String obsAcomp) {
        this.obsAcomp = obsAcomp;
    }
    
    @Override
    public boolean CadastrarItem() {
        return false;
    }

}
