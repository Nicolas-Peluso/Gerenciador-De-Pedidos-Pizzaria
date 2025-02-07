package com.nicolas;
import com.nicolas.Entities.Usuario;

public class Main {
    public static void main(String[] args){
        Usuario usuario = new Usuario();
        usuario.Cadastrar("Nicolas", "Auxiliar", "NicolasAux@gmail.com", 1, "123456789a", "Jose boni", "adssssss", "11221212");
        //usuario.Login("NicolasAux@gmail.com", "123456789a");

        System.out.println(Usuario.getUsrId());
    }
}