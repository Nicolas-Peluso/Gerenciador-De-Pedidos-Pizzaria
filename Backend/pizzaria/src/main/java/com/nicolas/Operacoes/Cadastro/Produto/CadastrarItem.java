package com.nicolas.Operacoes.Cadastro.Produto;
import com.nicolas.Entities.Usuario;
import com.nicolas.Exceptions.AcompanhamentoExisteException;
import com.nicolas.Exceptions.CampoVazioException;
import com.nicolas.Exceptions.SaborDePizzaJaExisteException;
import com.nicolas.HttpReq.CaptureMessageAndCode;
import com.nicolas.Sql.Buscar.AcompanhamentoExiste;
import com.nicolas.Sql.Buscar.PizzaSaborNome;
import com.nicolas.Sql.Inserir.InserirItem;
import com.nicolas.Verificacoes.VerificaCampo;

public class CadastrarItem extends InserirItem{

    public boolean ValidarCamposItem(String[] campos, double preco){
         try {
            if(VerificaCampo.CampoVazio(campos)){
                throw new CampoVazioException();
            }

            if (super.getPizza() != null) {
                PizzaSaborNome pzv = new PizzaSaborNome();

                if(pzv.VerificaPizzaExiste(super.getPizza().getNome(), super.getPizza().getSabor())){
                    throw new SaborDePizzaJaExisteException();   
                }

                if(super.getPizza().getTipo().isEmpty()){
                    super.getPizza().setTipo("pizza");
                }
            }

            if(super.getAcompanhamento() != null){
                AcompanhamentoExiste acompanhamentoExiste = new AcompanhamentoExiste();
                if(acompanhamentoExiste.VerificaAcompanhamento(super.getAcompanhamento().getNome())){
                    throw new AcompanhamentoExisteException();
                }

                if (super.getAcompanhamento().getTipo().isEmpty()) {
                    super.getAcompanhamento().setTipo("acompanhamento");
                }
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
        }catch (AcompanhamentoExisteException ace) {
            CaptureMessageAndCode.setMessage(ace.getMessage());
            CaptureMessageAndCode.setCodeErro(405);
            return false;
        }catch(Exception e){
            CaptureMessageAndCode.setMessage(e.getMessage());
            CaptureMessageAndCode.setCodeErro(405);
            return false;
        }
        return true;
    }

    /**
     *busca o id do usuario que possui o mesmo token 
     * @param token
     *recebe o token de sessão ja validado
     */
    public void SetId(String token){
        Usuario.setUsrId(token);
    }

}
