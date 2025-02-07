package com.nicolas;
import com.nicolas.Entities.Usuario;

public class Main {
    public static void main(String[] args){
        Usuario usuario = new Usuario();
        //usuario.Cadastrar("Nicolas", "Auxiliar", "NicolasAux@gmail.com", 1, "123456789a", "Jose boni", "adssssss", "11221212");
        //usuario.Cadastrar("Jose", "Auxiliar", "NicolasAux2@gmail.com", 1, "100000000N", "Agua na boca", "ds", "119282872");

        usuario.Login("NicolasAux@gmail.com", "");
        //usuario.Login("NicolasAux2@gmail.com", "100000000N");

        //usuario.CadastrarPizza("Muçarela com azeite e azeitonas", "Muçarela", 23.45);
        //System.out.println(Usuario.getUsrId());
    }
}