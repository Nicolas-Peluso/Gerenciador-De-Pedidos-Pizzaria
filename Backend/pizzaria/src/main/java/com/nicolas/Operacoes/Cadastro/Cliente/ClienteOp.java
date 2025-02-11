package com.nicolas.Operacoes.Cadastro.Cliente;
import com.nicolas.Entities.Cliente;
import com.nicolas.Exceptions.CampoVazioException;
import com.nicolas.Exceptions.UsuarioLogadoException;
import com.nicolas.HttpReq.CaptureMessageAndCode;
import com.nicolas.Sql.Buscar.ClienteBusca;
import com.nicolas.Sql.Inserir.InserirCliente;
import com.nicolas.Verificacoes.VerificaCampo;

public class ClienteOp extends InserirCliente{
    
    /**
     * @return
     * retorna true se nao houver erro
     */
    public boolean VerificaCampoCliente(){
        try{
            if(super.getCliente() == null){
                throw new UsuarioLogadoException();
            }

            if(VerificaCampo.CampoVazio(new String[] {
                super.getCliente().getNome(),
                super.getCliente().getBairro(),
                super.getCliente().getNomeRua()        
            }) || VerificaCampo.CampoVazio(new int[] {super.getCliente().getNumeroResidencia()})){
                throw new CampoVazioException();
            }
            return true;
        }catch(UsuarioLogadoException uslo){
            CaptureMessageAndCode.setMessage(uslo.getMessage());
            CaptureMessageAndCode.setCodeErro(405);
            return false;
        } catch(CampoVazioException czE){
            CaptureMessageAndCode.setMessage(czE.getMessage());
            CaptureMessageAndCode.setCodeErro(405);
            return false;
        }
    }

    public boolean Cadastrar(){
        try {
             if(!super.CadastrarCliente()){
                throw new Exception("Erro Na hora de cadastrar o cliente tente novamente");
            }
            
            ClienteBusca cb = new ClienteBusca();
            cb.setNome(super.getCliente().getNome());
            int id = cb.ClienteId();

            if(id == -1){
                throw new Exception("Erro Na hora de cadastrar o cliente tente novamente");
            }
        
            Cliente.setClienteId(id);
            return true;
        } catch (Exception e) {
            CaptureMessageAndCode.setCodeErro(405);
            CaptureMessageAndCode.setMessage(e.getMessage());
            return false;
        }
    }

}
