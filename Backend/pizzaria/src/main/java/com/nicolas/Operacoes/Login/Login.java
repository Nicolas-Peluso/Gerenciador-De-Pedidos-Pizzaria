package com.nicolas.Operacoes.Login;
import com.nicolas.Entities.Usuario;
import com.nicolas.Exceptions.CampoVazioException;
import com.nicolas.Exceptions.EmailException;
import com.nicolas.HttpReq.CaptureMessageAndCode;
import com.nicolas.Sql.Buscar.UsuarioLogin;
import com.nicolas.Verificacoes.Email;
import com.nicolas.Verificacoes.VerificaCampo;

public final class Login extends UsuarioLogin{
    
    public void UserLogado(){
        Usuario.setLogado(true);
    }

    public boolean VerificaCamposLogin(){
        try{
            if(!Email.VerificarEmail(super.getEmailLogin())){
                throw new EmailException();
            }
            if(VerificaCampo.CampoVazio(new String[] {super.getSenhaLogin()})){
                throw new CampoVazioException();
            }
        }catch(EmailException emaEx){
            CaptureMessageAndCode.setMessage(emaEx.getMessage());
            CaptureMessageAndCode.setCodeErro(405);
            return false;
        } catch(CampoVazioException senEx){
            CaptureMessageAndCode.setMessage(senEx.getMessage());
            CaptureMessageAndCode.setCodeErro(405);
            return false;
        }

        return true;
    }
}
