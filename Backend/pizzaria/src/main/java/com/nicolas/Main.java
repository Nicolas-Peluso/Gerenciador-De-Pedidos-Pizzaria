package com.nicolas;
import com.nicolas.Operacoes.Cadastro.Usuario.CadastraUsuario;

public class Main {
    public static void main(String[] args){
        CadastraUsuario usr1 = new CadastraUsuario("Nicolas", "Gerente", 4, "nicolasteste@gmail.com", "121323423");
        usr1.Cadastro();
    }
}