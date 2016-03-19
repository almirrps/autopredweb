package com.web.model;

import com.web.repository.RPSetor;
import java.util.ArrayList;

public class MDSetor {

    private Integer SET_ID;
    private String SET_NOME;

    public MDSetor() {
        this.SET_ID      = -1;
        this.SET_NOME    = "";
    }

    public Integer getSET_ID() {
        return SET_ID;
    }

    public String getSET_NOME() {
        return SET_NOME;
    }
    
    public void setSET_ID(Integer SET_ID) {
        this.SET_ID = SET_ID;
    }

    public void setSET_NOME(String SET_NOME) {

        this.SET_NOME = SET_NOME.toUpperCase();

    }

    public void setProximoCodigoInsercao() {
        int ProximoCodigo = RPSetor.PegaCodigoPelaNavegacao(RPSetor.cNavUltimo, 0) + 1;
        this.SET_ID = ProximoCodigo;
    }

    public void Salvar() {
        RPSetor.salvar(this);
    }

    public void Atualizar() {
        RPSetor.atualizar(this);
    }

    public void RecuperaObjeto(int iCodigo) {
        MDSetor admTemp = RPSetor.RecuperaObjCodigo(iCodigo);
        this.setSET_ID(admTemp.getSET_ID());
        this.setSET_NOME(admTemp.getSET_NOME());
    }

    public ArrayList<MDSetor> RecuperaObjeto(String sCampo, String sValor, boolean bEmQualquerParte ) {
        String NomeCampo = "";
        if (sCampo.equals("0"))
            NomeCampo = "SET_ID";
        else
            NomeCampo = "SET_NOME";
        
        return RPSetor.PesquisaObjeto(NomeCampo, sValor, bEmQualquerParte);
    }   
    
    public void Excluir() {
        RPSetor.excluir(this);
    }

    public void RecuperaObjetoNavegacao(int iOpcao, int icodigoAtual) {
        int codNav = RPSetor.PegaCodigoPelaNavegacao(iOpcao, icodigoAtual);
        RecuperaObjeto(codNav);
    }
}