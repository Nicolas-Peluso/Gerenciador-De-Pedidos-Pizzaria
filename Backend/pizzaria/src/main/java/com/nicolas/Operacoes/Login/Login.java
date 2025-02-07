package com.nicolas.Operacoes.Login;
import com.nicolas.Exceptions.EmailException;
import com.nicolas.Sql.Buscar.UsuarioLogin;
import com.nicolas.Verificacoes.Email;

public final class Login extends UsuarioLogin{
    
    public boolean VerificaCamposLogin(){
        try{
            if(!Email.VerificarEmail(super.getEmailLogin())){
                throw new EmailException();
            }
            if(super.getSenhaLogin().isEmpty()){
                throw new Exception("Senha nao pode estar Vazia");
            }
        }catch(EmailException emaEx){
            System.err.println(emaEx.getMessage());
            return false;
        } catch(Exception senEx){
            senEx.printStackTrace();
            return false;
        }

        return true;
    }
}
