package com.web.model;

import com.web.repository.RPCidade;
import java.util.ArrayList;

public class MDCidade {

    private Integer CID_ID, EST_ID;
    private String CID_NOME;

    public MDCidade() {
        this.CID_ID      = -1;
        this.CID_NOME    = "";
        this.EST_ID      = -1;
    }

    public Integer getCID_ID() {
        return CID_ID;
    }

    public String getCID_NOME() {
        return CID_NOME;
    }

    public Integer getEST_ID() {
        return EST_ID;
    }
    
    public void setCID_ID(Integer CID_ID) {
        this.CID_ID = CID_ID;
    }

    public void setCID_NOME(String CID_NOME) {

        this.CID_NOME = CID_NOME.toUpperCase();

    }

    public void setEST_ID(Integer EST_ID) {
        this.EST_ID = EST_ID;
    }

    public void setProximoCodigoInsercao() {
        int ProximoCodigo = RPCidade.PegaCodigoPelaNavegacao(RPCidade.cNavUltimo, 0) + 1;
        this.CID_ID = ProximoCodigo;
    }

    public void Salvar() {
        RPCidade.salvar(this);
    }

    public void Atualizar() {
        RPCidade.atualizar(this);
    }

    public void RecuperaObjeto(int iCodigo) {
        MDCidade cidTemp = RPCidade.RecuperaObjCodigo(iCodigo);
        this.setCID_ID(cidTemp.getCID_ID());
        this.setCID_NOME(cidTemp.getCID_NOME());
        this.setEST_ID(cidTemp.getEST_ID());
    }

    public ArrayList<MDCidade> RecuperaObjeto(String sCampo, String sValor, boolean bEmQualquerParte ) {
        String NomeCampo = "";
        if (sCampo.equals("0"))
            NomeCampo = "CID_ID";
        else 
            NomeCampo = "CID_NOME";
        
        return RPCidade.PesquisaObjeto(NomeCampo, sValor, bEmQualquerParte);
    }   
    
    public void Excluir() {
        RPCidade.excluir(this);
    }

    public void RecuperaObjetoNavegacao(int iOpcao, int icodigoAtual) {
        int codNav = RPCidade.PegaCodigoPelaNavegacao(iOpcao, icodigoAtual);
        RecuperaObjeto(codNav);
    }
}