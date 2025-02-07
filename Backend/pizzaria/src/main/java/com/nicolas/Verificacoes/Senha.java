package com.nicolas.Verificacoes;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Senha {
    private static final String check8Caracteres = "^.{8,}$";
    private static final String checkLetra = "^(?=.*[A-Za-z]).*$";
    private static final String checkNumero = "^(?=.*\\d).*$";

    public static String VerificarSenha(String senha){

        if(!Senha8Cara(senha)){
            return "Senha deve ter mais que 8 digitos";
        }

        if (!SenhaNumero(senha)) {
            return "deve ter pelo menos 1 numero na composição da senha";
        }

        if (!SenhacheckLetra(senha)) {
            return "deve ter pelo menos 1 letra na composição da senha";
        }

        return "";
    }

    private static boolean Senha8Cara(String senha){
        Pattern pattern = Pattern.compile(check8Caracteres);
        Matcher matcher = pattern.matcher(senha);
        return matcher.matches();
    }
    
    private static boolean SenhacheckLetra(String senha) {
        Pattern pattern = Pattern.compile(checkLetra);
        Matcher matcher = pattern.matcher(senha);
        return matcher.matches();
    }

    private static boolean SenhaNumero(String senha) {
        Pattern pattern = Pattern.compile(checkNumero);
        Matcher matcher = pattern.matcher(senha);
        return matcher.matches();
    }

}
