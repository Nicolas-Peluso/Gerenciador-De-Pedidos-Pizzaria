package com.nicolas.Operacoes.Cadastro.Usuario;
import com.nicolas.Sql.Inserir.InserirUsuario;

public class CadastraUsuario extends InserirUsuario {
    //classe usuario.

    public CadastraUsuario(String nome, String cargo, int limiteSaborPizza){
        super.setNome(SetNome(nome));
        super.setCargo(SetCargo(cargo));
        super.setLimiteSaborPizza(SetLimiteSaborPorPizza(limiteSaborPizza));
    }

    public void Cadastro(){
        boolean CadastroRealizado = CadastrarUsuario();
        if (!CadastroRealizado) {

        }
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
    
}
