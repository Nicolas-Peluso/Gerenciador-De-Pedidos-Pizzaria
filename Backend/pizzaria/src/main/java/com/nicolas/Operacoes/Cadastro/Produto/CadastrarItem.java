package com.nicolas.Operacoes.Cadastro.Produto;
import com.nicolas.Exceptions.CampoVazioException;
import com.nicolas.Exceptions.SaborDePizzaJaExisteException;
import com.nicolas.HttpReq.CaptureMessageAndCode;
import com.nicolas.Sql.Buscar.PizzaSaborNome;
import com.nicolas.Sql.Inserir.InserirItem;
import com.nicolas.Verificacoes.VerificaCampo;

public class CadastrarItem extends InserirItem{

    public boolean ValidarCamposItem(String nome, String sabor, double preco){
         try {
            if(VerificaCampo.CampoVazio(new String[]{sabor, nome})){
                throw new CampoVazioException();
            }
            PizzaSaborNome pzv = new PizzaSaborNome();

            if (pzv.VerificaPizzaExiste(nome, sabor)) {
                throw new SaborDePizzaJaExisteException();   
            }

            if(preco < 1){
                throw new Exception("Preco Deve ser maior que 1");
            }

        } catch(CampoVazioException CpvEx){
            CaptureMessageAndCode.setMessage(CpvEx.getMessage());
            CaptureMessageAndCode.setCodeErro(405);
            return false;
        }catch(SaborDePizzaJaExisteException pzv){
            CaptureMessageAndCode.setMessage(pzv.getMessage());
            CaptureMessageAndCode.setCodeErro(405);
            return false;
        }catch(Exception e){
            CaptureMessageAndCode.setMessage(e.getMessage());
            CaptureMessageAndCode.setCodeErro(405);
            return false;
        }
        return true;
    }

}
