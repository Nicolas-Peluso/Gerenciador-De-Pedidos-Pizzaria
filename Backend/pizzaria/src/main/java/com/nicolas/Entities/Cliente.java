package com.nicolas.Entities;
import com.nicolas.Operacoes.Cadastro.Cliente.ClienteOp;

public class Cliente extends ClienteOp{
    private String nome = "";
    private int numeroResidencia;
    private String obs = "";
    private String nomeRua = "";
    private int aptoNumero;
    private String bairro = "";
    private static int clienteId;
    
    public static int getClienteId() {
        return clienteId;
    }
    public static void setClienteId(int clienteId_) {
        clienteId = clienteId_;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public int getNumeroResidencia() {
        return numeroResidencia;
    }
    public void setNumeroResidencia(int numeroResidencia) {
        this.numeroResidencia = numeroResidencia;
    }
    public String getObs() {
        return obs;
    }
    public void setObs(String obs) {
        this.obs = obs;
    }
    public String getNomeRua() {
        return nomeRua;
    }
    public void setNomeRua(String nomeRua) {
        this.nomeRua = nomeRua;
    }
    public int getAptoNumero() {
        return aptoNumero;
    }
    public void setAptoNumero(int aptoNumero) {
        this.aptoNumero = aptoNumero;
    }
    public String getBairro() {
        return bairro;
    }
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

}
