package com.nicolas.Exceptions;

public class PagamentoEmDinheiroException extends Exception{
    
    @Override
    public String getMessage(){
        return "Para pagamento em dinheiro deve ser inserido o valor com que o pedido sera pago"; 
    }
}
