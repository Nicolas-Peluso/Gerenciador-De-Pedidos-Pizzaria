package com.nicolas.Entities;
import com.nicolas.Exceptions.UsuarioLogadoException;
import com.nicolas.HttpReq.CaptureMessageAndCode;
import com.nicolas.util.jwt.jwtAutCom;

public class Usuario {
    
    private static String nomeUsr = "";
    private static String cargoUsr = "";
    private static String nomePizzariaUsr = "";
    private static int limiteSaborPizza; 
    
    private static String enderecoUsr = "";
    private static String telefoneUsr = "";
    private static int UsrId;
    private static boolean Logado = false;
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

    public Usuario(int id, String nome, String cargo, int limiteSaborPizza, String nomePizzaria, String endereco, String telefone, String TempoMedioDeDelivery, String token_) {
        setUsrId(id);
        setNomeUsr(nome);
        setCargoUsr(cargo);
        setLimiteSaborPizza(limiteSaborPizza);
        setNomePizzariaUsr(nomePizzaria);
        setEnderecoUsr(endereco);
        setTelefoneUsr(telefone);
        setTempoMedioDeDelivery(TempoMedioDeDelivery);
        setToken(token_);
    }

    //Cadastrar Acompanhamento

    
    /**
     * @return
     * retorna true se token for valido
     */
    public static boolean isLogado(String jwtToken) {
        try{
            if(!jwtAutCom.Auteticar(jwtToken)){
                throw new UsuarioLogadoException();
            }
        }catch(UsuarioLogadoException useL){
            CaptureMessageAndCode.setMessage(useL.getMessage());
            CaptureMessageAndCode.setCodeErro(405);
            return false;
        }

        return true;
    }

    public static void setLogado(boolean logado) {
        Logado = logado;
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

    public static void setUsrId(int usrId) {
        UsrId = usrId;
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
