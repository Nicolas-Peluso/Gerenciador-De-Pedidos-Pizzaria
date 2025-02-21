package com.nicolas.Operacoes.Deletar.Acompanhamento;

import com.nicolas.Entities.Usuario;
import com.nicolas.Exceptions.AcomNaoExisteException;
import com.nicolas.HttpReq.CaptureMessageAndCode;
import com.nicolas.Sql.Buscar.BuscarAcompanhamento;
import com.nicolas.Sql.Deletar.DeletarAcompanhamento;
import com.nicolas.Verificacoes.VerificaCampo;

public class DeletarAcomOp extends DeletarAcompanhamento{
    private String nomeAcom = "";

    public String getNomeAcom() {
        return nomeAcom;
    }

    public void setNomeAcom(String nomeAcom) {
        this.nomeAcom = nomeAcom;
    }

    public boolean VerificaNome(String[] campos){

        if(VerificaCampo.CampoVazio(campos)){
            return false;
        }

        return true;
    }

    public boolean DeletarAcomOP(String token) {
        Usuario.setUsrId(token);
        
        try {
            BuscarAcompanhamento bcA = new BuscarAcompanhamento();

            int id = bcA.BuscarIdAcompanhamentoPorNome(getNomeAcom());

            if (id == -1) {
                throw new AcomNaoExisteException();
            }

            super.setIdAcom(id);
            boolean del = super.DeletarAcom();
            return del;

        } catch (AcomNaoExisteException pze) {
            CaptureMessageAndCode.setMessage(pze.getMessage());
            CaptureMessageAndCode.setCodeErro(404);
            return false;
        }

    }
}
