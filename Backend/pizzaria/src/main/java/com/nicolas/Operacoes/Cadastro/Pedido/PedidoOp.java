package com.nicolas.Operacoes.Cadastro.Pedido;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.nicolas.Entities.Acompanhamento;
import com.nicolas.Entities.Cliente;
import com.nicolas.Entities.Item;
import com.nicolas.Entities.Pedido;
import com.nicolas.Entities.Pizza;
import com.nicolas.Entities.Usuario;
import com.nicolas.Exceptions.CampoVazioException;
import com.nicolas.Exceptions.ExceptionGenerica;
import com.nicolas.Exceptions.PagamentoEmDinheiroException;
import com.nicolas.HttpReq.CaptureMessageAndCode;
import com.nicolas.Sql.Buscar.BuscarAcompanhamento;
import com.nicolas.Sql.Buscar.BuscarPizza;
import com.nicolas.Sql.Inserir.InserirItemPedido;
import com.nicolas.Sql.Inserir.InserirPedido;
import com.nicolas.Verificacoes.VerificaCampo;

public class PedidoOp extends InserirPedido{

    /**
     * funcao calcula o preco total buscando no banco o preco de cada item.
     * Só deve ser chamanda após a transação ser concluida
     */
    public void CalculaValorTotal(){
        double ValorTotal = 0;
        BuscarPizza bpz = new BuscarPizza();
        BuscarAcompanhamento bac = new BuscarAcompanhamento();

        for (Item it : super.getPd().getItens()) {
            if (it.getTipo().equals("pizza")) {
                ValorTotal += bpz.BuscarPrecoDaPizzaPorNome(it.getNome());
            } else {
                ValorTotal += bac.BuscarPrecoAcompanhamentoPorNome(it.getNome());
            }
        }

        if(ValorTotal <= 0){
            super.getPd().setValorTotal(0.0);
            return;
        }
        super.getPd().setValorTotal(ValorTotal);
    }

    /**
     * Calcula o troco baseado no valor do cliente e o valor total.
     * Só deve ser chamada após o valor total ter sido calculado. 
     */
    public void CalculaTroco(){
        if (super.getPd().isDinheiro() && super.getPd().getValorDoCliente() != 0.0) {
            double troco = super.getPd().getValorDoCliente() - super.getPd().getValorTotal();
            if (troco <= 0) {
                super.getPd().setTroco(0.0);
            }
            super.getPd().setTroco(troco);
        } else {
            super.getPd().setTroco(0.0);
        }
    }

    public boolean VerificaCampoPedido(){
        try{
            if(super.getPd() == null){
                throw new ExceptionGenerica();
            }

            if(VerificaCampo.CampoVazio(new String[] {super.getPd().getFormaDePagamento()})){
                throw new CampoVazioException();
            }

            if(super.getPd().isDinheiro() && super.getPd().getValorDoCliente() == 0.0){
                throw new PagamentoEmDinheiroException();
            }
            
            return true;
        }catch(CampoVazioException e){
            CaptureMessageAndCode.setCodeErro(405);
            CaptureMessageAndCode.setMessage(e.getMessage());
            return false;
        }catch(ExceptionGenerica e){
            CaptureMessageAndCode.setCodeErro(405);
            CaptureMessageAndCode.setMessage(e.getMessage());
            return false;
        } catch(PagamentoEmDinheiroException pgE){
            CaptureMessageAndCode.setCodeErro(405);
            CaptureMessageAndCode.setMessage(pgE.getMessage());
            return false;
        }
    }

    /**
     * recebe uma array do tipo JsonArray.
     * adiciona os itens do pedidos em objetos do tipo respectivo.
     * @param ar
     */
    public boolean ItensPedidoTratamento(JsonArray ar){
        Acompanhamento acom = null;
        Pizza pz = null;

        //verifica se a lista de pedidos possue pedidos
        if(ar.isEmpty()){
            CaptureMessageAndCode.setMessage("Não é possivel cadastrar u pedido sem os itens");
            CaptureMessageAndCode.setCodeErro(405);
            return false;
        }

        for(JsonElement it : ar){
            JsonObject itemObj = it.getAsJsonObject();

            String nome = itemObj.get("nome").getAsString();
            String tipoEE = itemObj.get("tipo").getAsString();
            String obs = itemObj.get("obs").getAsString();

            if(tipoEE.equals("pizza")){
                pz = new Pizza();
                pz.setNome(nome);
                pz.setTipo(tipoEE);
                pz.setObs(obs);
                BuscarPizza bc = new BuscarPizza();
                int id = bc.BuscarIdPizzaPorNome(nome);
                pz.setId(id);
                super.getPd().setItens(pz);
            } else if(tipoEE.equals("Acompanhamento")){
                acom = new Acompanhamento();
                acom.setNome(nome);
                acom.setTipo(tipoEE);
                acom.setObs(obs);
                BuscarAcompanhamento bc = new BuscarAcompanhamento();
                int idA = bc.BuscarIdAcompanhamentoPorNome(nome);
                acom.setId(idA);
                super.getPd().setItens(acom);   
            }
            else{
                return false;
            }
            acom = null;
            pz = null;
        }
        return true;
    }

    //Pedido nao pode existir sem um cliente vise-versa, item nao pode ser cadastrado sem um pedido.
    //Transação Atomica ou todos as tabelas (Cliente, item, Pedido) são preenchidas ou nenhuma.
    /**
     * @param cl
     *           recebe o cliente para usar o seu metodo de cadastro
     * @param token
     *      recebe o token de sessão ja validado para buscar o idDoUsuario ao qual o pedido sera cadastrado
     * @return
     *         retorna true se o pedido foi cadastrado com sucesso e false se não
     */
    public boolean CadastrarPedidoCompleto(Cliente cl, String token){
            Connection ConectionTrasactionPedido = null;
            Usuario.setUsrId(token);
        try{
            ConectionTrasactionPedido = DriverManager.getConnection(MYSQL_URL);

            //desabilitar o auto commit
            ConectionTrasactionPedido.setAutoCommit(false);

            if(!cl.Cadastrar(ConectionTrasactionPedido)){
                throw new SQLException();
            }

            int idPed = super.CadastrarPedido(ConectionTrasactionPedido);

            if(idPed == -1){
                throw new SQLException();
            }

            Pedido.setIdPedido(idPed);

            if(!this.InserirItensDoPedido(ConectionTrasactionPedido)){
                throw new SQLException();
            }

            ConectionTrasactionPedido.commit();
            return true;
        }catch(SQLException ec){
            ec.printStackTrace();
            if(ConectionTrasactionPedido != null){
                try {
                    ConectionTrasactionPedido.rollback();
                    return false;
                } catch (SQLException rollbackEx) {
                    rollbackEx.printStackTrace();
                }
            }
        } finally{
            if(ConectionTrasactionPedido != null){
                try{
                    ConectionTrasactionPedido.close();
                } catch(SQLException d){
                    d.printStackTrace();
                }
            }
        }
        return false;
    }

    public boolean InserirItensDoPedido(Connection Con){
        InserirItemPedido inserirItemPedido = new InserirItemPedido();

        for(Item it : super.getPd().getItens()){
            if(it.getTipo().equals("pizza")){
                inserirItemPedido.setPzId(it.getId());
                inserirItemPedido.setObs(it.getObs());
                if(!inserirItemPedido.InserirPizza(Con)){
                    CaptureMessageAndCode.setMessage("Nao foi possivel encontar esse item no sistema verifique o nome e o tipo do item");
                    CaptureMessageAndCode.setCodeErro(405);
                    return false;
                }
            }
            else{
                inserirItemPedido.setAcomId(it.getId());
                inserirItemPedido.setObs(it.getObs());
                if (!inserirItemPedido.InserirAcom(Con)) {
                    CaptureMessageAndCode.setMessage("Nao foi possivel encontar esse item no sistema verifique o nome e o tipo do item");
                    CaptureMessageAndCode.setCodeErro(405);
                    return false;
                }
            }
            inserirItemPedido.setAcomId(0);
            inserirItemPedido.setPzId(0);
        }
        return true;
    }
}
