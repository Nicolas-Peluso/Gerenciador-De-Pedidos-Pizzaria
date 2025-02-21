package com.nicolas.Entities;

import com.nicolas.Exceptions.TokenInvalidoException;
import com.nicolas.HttpReq.CaptureMessageAndCode;
import com.nicolas.Sql.Buscar.BuscarUserId;
import com.nicolas.Sql.Buscar.PizzariaNome;
import com.nicolas.util.jwt.jwtAutCom;

public class Usuario {
    
    private static String nomeUsr = "";
    private static String cargoUsr = "";
    private static String nomePizzariaUsr = "";
    private static int limiteSaborPizza; 
    
    private static String enderecoUsr = "";
    private static String telefoneUsr = "";
    private static int UsrId;
    private static String TempoMedioDeDelivery = "";
    private static String token = "";

    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        Usuario.token = token;
    }

    public static String getTempoMedioDeDelivery() {
        return TempoMedioDeDelivery;
    }

    public static void setTempoMedioDeDelivery(String tempoMedioDeDelivery) {
        TempoMedioDeDelivery = tempoMedioDeDelivery;
    }

    public Usuario(String nome, String cargo, int limiteSaborPizza, String nomePizzaria, String endereco, String telefone, String TempoMedioDeDelivery, String token_) {
        setNomeUsr(nome);
        setCargoUsr(cargo);
        setLimiteSaborPizza(limiteSaborPizza);
        setNomePizzariaUsr(nomePizzaria);
        setEnderecoUsr(endereco);
        setTelefoneUsr(telefone);
        setTempoMedioDeDelivery(TempoMedioDeDelivery);
        setToken(token_);
    }

    /**
     * @return
     * retorna true se token for valido
     */
    public static boolean isLogado(String jwtToken) {
        try{
            PizzariaNome pzC = new PizzariaNome();
            String pznome = pzC.BuscarPizzariaNomePorToken(jwtToken);
            
            if(pznome.isEmpty()){
                throw new TokenInvalidoException();
            }

            if(!jwtAutCom.Auteticar(jwtToken, pznome)){
                throw new TokenInvalidoException();
            }

        } catch(TokenInvalidoException e){
            CaptureMessageAndCode.setMessage(e.getMessage());
            CaptureMessageAndCode.setCodeErro(405);
            return false;
        }

        return true;
    }

    public void CadastrarAcompanhamento() {

    }

    public static int getLimiteSaborPizza() {
        return limiteSaborPizza;
    }

    public static void setLimiteSaborPizza(int limiteSaborPizza) {
        Usuario.limiteSaborPizza = limiteSaborPizza;
    }

    public static int getUsrId() {
        return UsrId;
    }

    /**
     * seta o id do usuario apartir do token recebido
     * @param token
     * token de sessão ja validado, (não faz validacao de token)
     */
    public static void setUsrId(String token) {
        try {
            BuscarUserId iduserBuscarUserId = new BuscarUserId();
            int id = iduserBuscarUserId.BuscarId(token);
            UsrId = id;
        } catch (Exception e) {
            CaptureMessageAndCode.setMessage(e.getMessage());
            CaptureMessageAndCode.setCodeErro(405);
        }
        
    }

    public static String getNomeUsr() {
        return nomeUsr;
    }

    public static void setNomeUsr(String nomeUsr) {
        Usuario.nomeUsr = nomeUsr;
    }

    public static String getCargoUsr() {
        return cargoUsr;
    }

    public static void setCargoUsr(String cargoUsr) {
        Usuario.cargoUsr = cargoUsr;
    }

    public static String getNomePizzariaUsr() {
        return nomePizzariaUsr;
    }

    public static void setNomePizzariaUsr(String nomePizzariaUsr) {
        Usuario.nomePizzariaUsr = nomePizzariaUsr;
    }

    public static String getEnderecoUsr() {
        return enderecoUsr;
    }

    public static void setEnderecoUsr(String enderecoUsr) {
        Usuario.enderecoUsr = enderecoUsr;
    }

    public static String getTelefoneUsr() {
        return telefoneUsr;
    }

    public static void setTelefoneUsr(String telefoneUsr) {
        Usuario.telefoneUsr = telefoneUsr;
    }
}
