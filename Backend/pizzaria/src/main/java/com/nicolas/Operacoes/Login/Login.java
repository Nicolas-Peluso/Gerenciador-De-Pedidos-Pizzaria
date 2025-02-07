package com.nicolas.Operacoes.Login;
import com.nicolas.Exceptions.CampoVazioException;
import com.nicolas.Exceptions.EmailException;
import com.nicolas.Sql.Buscar.UsuarioLogin;
import com.nicolas.Verificacoes.Email;
import com.nicolas.Verificacoes.VerificaCampo;

public final class Login extends UsuarioLogin{
    
    public boolean VerificaCamposLogin(){
        try{
            if(!Email.VerificarEmail(super.getEmailLogin())){
                throw new EmailException();
            }
            if(VerificaCampo.CampoVazio(new String[] {super.getSenhaLogin()})){
                throw new CampoVazioException();
            }
        }catch(EmailException emaEx){
            System.err.println(emaEx.getMessage());
            return false;
        } catch(CampoVazioException senEx){
            System.out.println(senEx.getMessage());
            return false;
        }

        return true;
    }
}
