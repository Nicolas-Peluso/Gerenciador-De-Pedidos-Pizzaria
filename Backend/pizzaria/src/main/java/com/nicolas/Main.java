package com.nicolas;
import com.nicolas.Operacoes.Cadastro.Usuario.CadastraUsuario;

public class Main {
    public static void main(String[] args){
        CadastraUsuario usr1 = new CadastraUsuario("Nicolas", "Gerente", 
        4, "nicolastsestssse@gmail.com",
        "aaaaaaaaa", "Pizzaria Agua Na Boca - pq", "rua alceu luchiari", "11 91232324");
        System.out.println(usr1.Cadastro());
    }
}