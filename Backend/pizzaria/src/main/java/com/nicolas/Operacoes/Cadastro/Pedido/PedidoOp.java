package com.nicolas.Operacoes.Cadastro.Pedido;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.nicolas.Entities.Acompanhamento;
import com.nicolas.Entities.Cliente;
import com.nicolas.Entities.Item;
import com.nicolas.Entities.Pedido;
import com.nicolas.Entities.Pizza;
import com.nicolas.Exceptions.CampoVazioException;
import com.nicolas.HttpReq.CaptureMessageAndCode;
import com.nicolas.Sql.Buscar.BuscarAcompanhamento;
import com.nicolas.Sql.Buscar.BuscarPedido;
import com.nicolas.Sql.Buscar.BuscarPizza;
import com.nicolas.Sql.Inserir.InserirItemPedido;
import com.nicolas.Sql.Inserir.InserirPedido;
import com.nicolas.Verificacoes.VerificaCampo;

public class PedidoOp extends InserirPedido{

    public boolean VerificaCampoPedido(){
        try{
            if(super.getPd() == null){
                throw new Exception("Algo deu errado na manipulação tente novamente");
            }

            if(VerificaCampo.CampoVazio(new String[] {super.getPd().getFormaDePagamento()})){
                throw new CampoVazioException();
            }

            return true;
        }catch(CampoVazioException e){
            CaptureMessageAndCode.setCodeErro(405);
            CaptureMessageAndCode.setMessage(e.getMessage());
            return false;
        }catch(Exception e){
            CaptureMessageAndCode.setCodeErro(405);
            CaptureMessageAndCode.setMessage(e.getMessage());
            return false;
        }
    }

    public void ItensPedidoTratamento(JsonArray ar){
        Acompanhamento acom = null;
        Pizza pz = null;

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
            } else{
                acom = new Acompanhamento();
                acom.setNome(nome);
                acom.setTipo(tipoEE);
                acom.setObs(obs);
                BuscarAcompanhamento bc = new BuscarAcompanhamento();
                int idA = bc.BuscarIdAcompanhamentoPorNome(nome);
                acom.setId(idA);
                super.getPd().setItens(acom);   
                System.err.println("nfkns"+acom.getId());
            }

            acom = null;
            pz = null;
        }
    }

    public boolean Cadastrar(){
        boolean cad =  super.CadastrarPedido();

        if(cad){
            BuscarPedido ped = new BuscarPedido();
            int idPed = ped.PedidoId(Cliente.getClienteId());
            Pedido.setIdPedido(idPed);
        }

        return cad;
    }

    public void InserirItensDoPedido(){
        InserirItemPedido inserirItemPedido = new InserirItemPedido();

        for(Item it : super.getPd().getItens()){
            if(it.getTipo().equals("pizza")){
                inserirItemPedido.setPzId(it.getId());
                inserirItemPedido.setObs(it.getObs());
                inserirItemPedido.InserirPizza();
            }
            else{
                inserirItemPedido.setAcomId(it.getId());
                inserirItemPedido.setObs(it.getObs());
                inserirItemPedido.InserirAcom();
            }

            inserirItemPedido.setAcomId(0);
            inserirItemPedido.setPzId(0);
        }

    }
}
