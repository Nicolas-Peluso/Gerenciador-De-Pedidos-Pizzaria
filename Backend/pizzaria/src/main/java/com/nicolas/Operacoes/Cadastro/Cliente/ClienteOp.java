package com.nicolas.Operacoes.Cadastro.Cliente;
import java.sql.Connection;

import com.nicolas.Entities.Cliente;
import com.nicolas.Exceptions.CampoVazioException;
import com.nicolas.Exceptions.ExceptionGenerica;
import com.nicolas.Exceptions.UsuarioLogadoException;
import com.nicolas.HttpReq.CaptureMessageAndCode;
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

    public boolean Cadastrar(Connection con){
        try {
            int id = super.CadastrarCliente(con);
             if(id == -1){
                throw new ExceptionGenerica();
            }
            
            Cliente.setClienteId(id);
            return true;
        } catch (ExceptionGenerica e) {
            CaptureMessageAndCode.setCodeErro(405);
            CaptureMessageAndCode.setMessage(e.getMessage());
            return false;
        }
    }

}
