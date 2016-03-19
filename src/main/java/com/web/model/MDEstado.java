package com.web.model;

import com.web.repository.RPEstado;
import java.util.ArrayList;

public class MDEstado {

    private Integer EST_ID;
    private String EST_NOME, EST_SIGLA;

    public MDEstado() {
        this.EST_ID    = -1;
        this.EST_NOME  = "";
        this.EST_SIGLA = "";
    }

    public Integer getEST_ID() {
        return EST_ID;
    }

    public String getEST_NOME() {
        return EST_NOME;
    }

    public String getEST_SIGLA() {
        return EST_SIGLA;
    }
    
    public void setEST_ID(Integer EST_ID) {
        this.EST_ID = EST_ID;
    }

    public void setEST_NOME(String EST_NOME) {
        this.EST_NOME = EST_NOME.toUpperCase();
    }

    public void setEST_SIGLA(String EST_SIGLA) {
        this.EST_SIGLA = EST_SIGLA.toUpperCase();
    }

    public void setProximoCodigoInsercao() {
        int ProximoCodigo = RPEstado.PegaCodigoPelaNavegacao(RPEstado.cNavUltimo, 0) + 1;
        this.EST_ID = ProximoCodigo;
    }

    public void Salvar() {
        RPEstado.salvar(this);
    }

    public void Atualizar() {
        RPEstado.atualizar(this);
    }

    public void RecuperaObjeto(int iCodigo) {
        MDEstado estTemp = RPEstado.RecuperaObjCodigo(iCodigo);
        this.setEST_ID(estTemp.getEST_ID());
        this.setEST_NOME(estTemp.getEST_NOME());
        this.setEST_SIGLA(estTemp.getEST_SIGLA());
    }

    public ArrayList<MDEstado> RecuperaObjeto(String sCampo, String sValor, boolean bEmQualquerParte ) {
        String NomeCampo = "";
        if (sCampo.equals("0"))
            NomeCampo = "EST_ID";
        else if (sCampo.equals("1"))
            NomeCampo = "EST_NOME";
        else
            NomeCampo = "EST_SIGLA";
        
        return RPEstado.PesquisaObjeto(NomeCampo, sValor, bEmQualquerParte);
    }   
    
    public void Excluir() {
        RPEstado.excluir(this);
    }

    public void RecuperaObjetoNavegacao(int iOpcao, int icodigoAtual) {
        int codNav = RPEstado.PegaCodigoPelaNavegacao(iOpcao, icodigoAtual);
        RecuperaObjeto(codNav);
    }
}