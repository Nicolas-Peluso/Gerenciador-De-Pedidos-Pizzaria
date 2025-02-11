package com.nicolas.Entities;

import java.util.ArrayList;
import com.nicolas.Operacoes.Cadastro.Pedido.PedidoOp;

public class Pedido extends PedidoOp{
    private String formaDePagamento = "";
    private double valorTotal;
    private String tempoEspera;
    private boolean dinheiro;
    private boolean pix;
    private boolean pagamentoConfirmado;
    private ArrayList<Item> itens = new ArrayList<>();
    private static int idPedido = 0;

    public static int getIdPedido() {
        return idPedido;
    }

    public static void setIdPedido(int idPedido_) {
        idPedido = idPedido_;
    }

    public ArrayList<Item> getItens() {
        return itens;
    }

    public void setItens(Item iten) {
        this.itens.add(iten);
    }

    public String getFormaDePagamento() {
        return formaDePagamento;
    }
    public void setFormaDePagamento(String formaDePagamento) {
        this.formaDePagamento = formaDePagamento;
    }
    public double getValorTotal() {
        return valorTotal;
    }
    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }
    public String getTempoEspera() {
        return tempoEspera;
    }
    public void setTempoEspera(String tempoEspera) {
        this.tempoEspera = tempoEspera;
    }
    public boolean isDinheiro() {
        return dinheiro;
    }
    public void setDinheiro(boolean dinheiro) {
        this.dinheiro = dinheiro;
    }
    public boolean isPix() {
        return pix;
    }
    public void setPix(boolean pix) {
        this.pix = pix;
    }
    public boolean isPagamentoConfirmado() {
        return pagamentoConfirmado;
    }
    public void setPagamentoConfirmado(boolean pagamentoConfirmado) {
        this.pagamentoConfirmado = pagamentoConfirmado;
    }
}
