package com.nicolas.Operacoes.Cadastro.Usuario;
import com.nicolas.Exceptions.EmailException;
import com.nicolas.Exceptions.SenhaException;
import com.nicolas.Sql.Inserir.InserirUsuario;
import com.nicolas.Verificacoes.Email;
import com.nicolas.Verificacoes.Senha;

public class CadastraUsuario extends InserirUsuario{
    //classe usuario.

    public CadastraUsuario(String nome, String cargo, int limiteSaborPizza, String email, String senha, String NomePizzaria,
        String endereco, String telefone){
        super.setNome(SetNome(nome));
        super.setCargo(SetCargo(cargo));
        super.setLimiteSaborPizza(SetLimiteSaborPorPizza(limiteSaborPizza));

        super.setEmail(SetEmail(email));
        super.setSenha(SetSenha(senha));

        super.setNomePizzaria(SetNomeDaPizzaria(NomePizzaria));

        super.setEndereco(SetEndereco(endereco));
        super.setTelefone(SetTelefone(telefone));
    }
    
    private boolean VerificaCampos(){
        // verificação dos campos
        try {
            if (super.getNome().isEmpty() || super.getCargo().isEmpty()
                    || super.getEmail().isEmpty() || super.getSenha().isEmpty()
                    || super.getNomePizzaria().isEmpty() || super.getEndereco().isEmpty()
                    || super.getTelefone().isEmpty()) {
                throw new Exception("Nenhum campo deve estar vazio");
            }
            if (super.getLimiteSaborPizza() < 1) {
                throw new Exception("O limite de sabores em uma pizza nao pode ser 0 deve ser 1 ou mais");
            }
            try {
                if (!Email.VerificarEmail(email)) {
                    throw new EmailException();
                }
            } catch (EmailException em) {
                System.out.println(em.getMessage());
                return false;
            }

            try {
                String tempMsg = Senha.VerificarSenha(senha);
                if (!tempMsg.isEmpty()) {
                    throw new SenhaException(tempMsg);
                }
            } catch (SenhaException em) {
                System.out.println(em.getMessage());
                return false;
            }

        } catch (Exception genEx) {
            System.err.println(genEx.getMessage());
            return false;
        }

        return true;
    }

    public boolean Cadastro(){
        if(this.VerificaCampos()){
            return CadastrarUsuario();
        }
        return false;
    }

    @Override
    public String SetNome(String nome) {
        return nome;
    }

    @Override
    public String SetCargo(String cargo) {
        return cargo;
    }

    @Override
    public int SetLimiteSaborPorPizza(int limite) {
        return limite;
    }

    @Override
    public String SetEmail(String email) {
        return email;
    }

    @Override
    public String SetSenha(String senha) {
       return senha;
    }

    @Override
    public String SetNomeDaPizzaria(String nomePizzaria) {
        return nomePizzaria;
    }

    @Override
    public String SetEndereco(String Ender) {
        return Ender;
    }

    @Override
    public String SetTelefone(String tel) {
        return tel;
    }
    
}
