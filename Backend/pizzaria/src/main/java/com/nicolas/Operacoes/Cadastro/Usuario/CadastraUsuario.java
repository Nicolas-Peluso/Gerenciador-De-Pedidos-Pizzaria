package com.nicolas.Operacoes.Cadastro.Usuario;
import com.nicolas.Exceptions.CampoVazioException;
import com.nicolas.Exceptions.EmailException;
import com.nicolas.Exceptions.PizzzariaJaCadastradaException;
import com.nicolas.Exceptions.SenhaException;
import com.nicolas.Exceptions.UsuarioJaExisteException;
import com.nicolas.HttpReq.CaptureMessageAndCode;
import com.nicolas.Operacoes.Buscar.VerificarUsuario;
import com.nicolas.Operacoes.Buscar.pizzariaNome.pizzariaNome;
import com.nicolas.Sql.Inserir.InserirUsuario;
import com.nicolas.Verificacoes.Email;
import com.nicolas.Verificacoes.Senha;
import com.nicolas.Verificacoes.VerificaCampo;

public final class CadastraUsuario extends InserirUsuario{

    public boolean VerificaCampos(){
        try {
            if(VerificaCampo.CampoVazio(new String[]{super.getNome(), super.getCargo(), super.getEmail(), super.getSenha(), super.getNomePizzaria(), super.getEndereco()
            ,super.getTelefone()})){
                throw new CampoVazioException();
            }
            
            try {
                if (!Email.VerificarEmail(email)) {
                    throw new EmailException();
                }
            } catch (EmailException em) {
                CaptureMessageAndCode.setMessage(em.getMessage());
                CaptureMessageAndCode.setCodeErro(405);
                return false;
            }

            try {
                String tempMsg = Senha.VerificarSenha(senha);
                if (!tempMsg.isEmpty()) {
                    throw new SenhaException(tempMsg);
                }
            } catch (SenhaException em) {
                CaptureMessageAndCode.setMessage(em.getMessage());
                CaptureMessageAndCode.setCodeErro(200);
                return false;
            }

        } catch (CampoVazioException genEx) {
            CaptureMessageAndCode.setMessage(genEx.getMessage());
            CaptureMessageAndCode.setCodeErro(405);
            return false;
        } catch (Exception genEx) {
            CaptureMessageAndCode.setMessage(genEx.getMessage());
            CaptureMessageAndCode.setCodeErro(405);
            return false;
        }

        return true;
    }

    public boolean Cadastro(){
         try{
            VerificarUsuario vUsuario = new VerificarUsuario();
            if(vUsuario.UsuarioExiste(email)){
                throw new UsuarioJaExisteException();
            }
            try{
                pizzariaNome Pnome = new pizzariaNome();
                if(Pnome.BuscarPizzariaNome(nomePizzaria)){
                    throw new PizzzariaJaCadastradaException();
                }
                return CadastrarUsuario();
            } catch (PizzzariaJaCadastradaException exz) {
                CaptureMessageAndCode.setMessage(exz.getMessage());
                CaptureMessageAndCode.setCodeErro(405);
            }
        } catch (UsuarioJaExisteException ex) {
            CaptureMessageAndCode.setMessage(ex.getMessage());
            CaptureMessageAndCode.setCodeErro(405);
        }
        return false;
    }
}
