package com.nicolas.Entities;

import com.nicolas.Exceptions.CampoVazioException;
import com.nicolas.Exceptions.UsuarioLogadoException;
import com.nicolas.Operacoes.Buscar.PizzaExiste.PizzaExisteV;
import com.nicolas.Operacoes.Cadastro.Usuario.CadastraUsuario;
import com.nicolas.Operacoes.Login.Login;
import com.nicolas.Verificacoes.VerificaCampo;

public class Usuario {
    
    private static String nomeUsr = "";
    private static String cargoUsr = "";
    private static String nomePizzariaUsr = "";
    private static int limiteSaborPizza; 
    
    private static String enderecoUsr = "";
    private static String telefoneUsr = "";
    private static int UsrId;
    private static boolean Logado = false;

    public void Cadastrar(String nome, String cargo ,String email, int limiteSaborPizza, String senha ,String nomePizzaria ,String endereco , String telefone){
        CadastraUsuario usr1 = new CadastraUsuario(nome, cargo, limiteSaborPizza, email, senha, nomePizzaria, endereco, telefone);
        
        if(usr1.Cadastro()){
            this.Login(email, senha);
        }
    }

    public void Login(String emailLogin, String senhaLogin){
        Login lg = new Login();
        lg.setEmailLogin(emailLogin);
        lg.setSenhaLogin(senhaLogin);

        if(lg.VerificaCamposLogin()){
            if(lg.Login()){
                setUsrId(lg.getUsrId());
                setLogado(true);
            }
        }
    }

    public void CadastrarPizza(String sabor, String nome, double precoP, String tipo){
        try {
            if(!Usuario.Logado){
                throw new UsuarioLogadoException();
            }
            if(VerificaCampo.CampoVazio(new String[]{sabor, nome, tipo})){
                throw new CampoVazioException();
            }
            PizzaExisteV pzv = new PizzaExisteV();
            Pizza pizza = new Pizza();
            
            pizza.setTipo(tipo);
            pizza.setSabor(sabor);
            pizza.setNome(nome);
            pizza.setPreco(precoP);

            pizza.setPizza(pizza);

            if (!pzv.VerificaPizzaExiste(nome, sabor)) {
                pizza.CadastrarItem();
            }
        } catch (UsuarioLogadoException e) {
            System.err.println(e.getMessage());
        } catch(CampoVazioException CpvEx){
            System.out.println(CpvEx.getMessage());
        }
    }

    //Cadastrar Acompanhamento

    public static boolean isLogado() {
        return Logado;
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
