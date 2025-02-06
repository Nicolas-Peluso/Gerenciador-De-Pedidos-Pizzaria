package com.nicolas;
import com.nicolas.Operacoes.Cadastro.Usuario.CadastraUsuario;

public class Main {
    public static void main(String[] args){
        CadastraUsuario usr1 = new CadastraUsuario("Nicolas", "Gerente", 
        4, "nicolasteste@gmail.com",
        "1222222", "Pizzaria Agua Na Boca", "rua alceu luchiari", "11 91232324");
        usr1.Cadastro();
    }
}