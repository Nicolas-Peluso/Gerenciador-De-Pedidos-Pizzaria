package com.nicolas.Verificacoes;

public class VerificaCampo {

    /**
     * @param campo
     *array de String
     * @return
     *retorna true se o valor do null ou vazio.
     */
    public static boolean CampoVazio(String[] campo){
        for(String cp : campo){
            if(cp == null || cp.isEmpty()){
                return true;
            }
        }
        return false;
    }

    /**
     * @param campo
     * array de inteiros
     * @return
     * retorna true se o valor do campo for 0. considera 0 como vazio
     */
    public static boolean CampoVazio(int[] campo) {
        for (int cp : campo) {
            if (cp == 0) {
                return true;
            }
        }
        return false;
    }
}
