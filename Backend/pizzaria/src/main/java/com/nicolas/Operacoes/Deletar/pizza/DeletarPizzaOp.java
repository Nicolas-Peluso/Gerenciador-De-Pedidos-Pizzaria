package com.nicolas.Operacoes.Deletar.pizza;
import com.nicolas.Entities.Usuario;
import com.nicolas.Exceptions.PizzaNaoExisteException;
import com.nicolas.HttpReq.CaptureMessageAndCode;
import com.nicolas.Sql.Buscar.BuscarPizza;
import com.nicolas.Sql.Deletar.DeletarPizza;
import com.nicolas.Verificacoes.VerificaCampo;

public class DeletarPizzaOp extends DeletarPizza{

    public String getNomePZ() {
        return nomePZ;
    }

    public void setNomePZ(String nomePZ) {
        this.nomePZ = nomePZ;
    }
    public String getNomeAc() {
            return nomeAc;
        }

    public void setNomeAc(String nomeAc) {
        this.nomeAc = nomeAc;
    }

    private String nomePZ = "";
    private String nomeAc = "";

    public boolean VerificaNome(String[] campos){

        if(VerificaCampo.CampoVazio(campos)){
            return false;
        }

        return true;
    }

    public boolean DeletarPzOP(String token){

        try{
            Usuario.setUsrId(token);
            BuscarPizza bcpz = new BuscarPizza();

            int id = bcpz.BuscarIdPizzaPorNome(getNomePZ());

            if(id == -1){
                throw new PizzaNaoExisteException();
            }

            super.setIdPizza(id);
            boolean del = super.Deletar();
            return del;

        } catch(PizzaNaoExisteException pze){
            CaptureMessageAndCode.setMessage(pze.getMessage());
            CaptureMessageAndCode.setCodeErro(404);
            return false;
        }

    }

}
