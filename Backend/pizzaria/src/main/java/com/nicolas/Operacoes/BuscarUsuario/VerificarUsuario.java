package com.nicolas.Operacoes.BuscarUsuario;
import com.nicolas.Sql.Buscar.BuscarUsuario;

public class VerificarUsuario extends BuscarUsuario{
    private String email = "";

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    
    public boolean Verifica(){
        return UsuarioExiste(this.getEmail());
    }
}
