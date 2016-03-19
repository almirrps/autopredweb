package com.web.model;

import com.web.repository.RPAdministrador;
import java.util.ArrayList;

public class MDAdministrador {

    private Integer ADM_ID, CAF_ID, SET_ID;
    private String ADM_NOME, ADM_EMAIL, ADM_USUARIO, ADM_SENHA;

    public MDAdministrador() {
        this.ADM_ID      = -1;
        this.ADM_NOME    = "";
        this.CAF_ID      = -1;
        this.SET_ID      = -1;
        this.ADM_EMAIL   = "";
        this.ADM_USUARIO = "";
        this.ADM_SENHA   = "";
    }

    public Integer getADM_ID() {
        return ADM_ID;
    }

    public String getADM_NOME() {
        return ADM_NOME;
    }

    public Integer getCAF_ID() {
        return CAF_ID;
    }

    public Integer getSET_ID() {
        return SET_ID;
    }

    public String getADM_EMAIL() {
        return ADM_EMAIL;
    }

    public String getADM_USUARIO() {
        return ADM_USUARIO;
    }
    
    public String getADM_SENHA() {
        return ADM_SENHA;
    }
    
    public void setADM_ID(Integer ADM_ID) {
        this.ADM_ID = ADM_ID;
    }

    public void setADM_NOME(String ADM_NOME) {

        this.ADM_NOME = ADM_NOME.toUpperCase();

    }

    public void setCAF_ID(Integer CAF_ID) {
        this.CAF_ID = CAF_ID;
    }

    public void setSET_ID(Integer SET_ID) {
        this.SET_ID = SET_ID;
    }

    public void setADM_EMAIL(String ADM_EMAIL) {
        this.ADM_EMAIL = ADM_EMAIL.toUpperCase();
    }

    public void setADM_USUARIO(String ADM_USUARIO) {
        this.ADM_USUARIO = ADM_USUARIO;
    }
    
    public void setADM_SENHA(String ADM_SENHA) {
        this.ADM_SENHA = ADM_SENHA;
    }

    public void setProximoCodigoInsercao() {
        int ProximoCodigo = RPAdministrador.PegaCodigoPelaNavegacao(RPAdministrador.cNavUltimo, 0) + 1;
        this.ADM_ID = ProximoCodigo;
    }

    public void Salvar() {
        RPAdministrador.salvar(this);
    }

    public void Atualizar() {
        RPAdministrador.atualizar(this);
    }

    public void RecuperaObjeto(int iCodigo) {
        MDAdministrador admTemp = RPAdministrador.RecuperaObjCodigo(iCodigo);
        this.setADM_ID(admTemp.getADM_ID());
        this.setADM_NOME(admTemp.getADM_NOME());
        this.setCAF_ID(admTemp.getCAF_ID());
        this.setSET_ID(admTemp.getSET_ID());
        this.setADM_EMAIL(admTemp.getADM_EMAIL());
        this.setADM_USUARIO(admTemp.getADM_USUARIO());
        this.setADM_SENHA(admTemp.getADM_SENHA());
    }

    public ArrayList<MDAdministrador> RecuperaObjeto(String sCampo, String sValor, boolean bEmQualquerParte ) {
        String NomeCampo = "";
        if (sCampo.equals("0"))
            NomeCampo = "ADM_ID";
        else if (sCampo.equals("1"))
            NomeCampo = "ADM_NOME";
        else
            NomeCampo = "ADM_USUARIO";
        
        return RPAdministrador.PesquisaObjeto(NomeCampo, sValor, bEmQualquerParte);
    }   
    
    public void Excluir() {
        RPAdministrador.excluir(this);
    }

    public void RecuperaObjetoNavegacao(int iOpcao, int icodigoAtual) {
        int codNav = RPAdministrador.PegaCodigoPelaNavegacao(iOpcao, icodigoAtual);
        RecuperaObjeto(codNav);
    }
}