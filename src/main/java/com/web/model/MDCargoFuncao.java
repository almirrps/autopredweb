package com.web.model;

import com.web.repository.RPCargoFuncao;
import java.util.ArrayList;

public class MDCargoFuncao {

    private Integer CAF_ID;
    private String CAF_NOME;

    public MDCargoFuncao() {
        this.CAF_ID      = -1;
        this.CAF_NOME    = "";
    }

    public Integer getCAF_ID() {
        return CAF_ID;
    }

    public String getCAF_NOME() {
        return CAF_NOME;
    }
    
    public void setCAF_ID(Integer CAF_ID) {
        this.CAF_ID = CAF_ID;
    }

    public void setCAF_NOME(String CAF_NOME) {

        this.CAF_NOME = CAF_NOME.toUpperCase();

    }

    public void setProximoCodigoInsercao() {
        int ProximoCodigo = RPCargoFuncao.PegaCodigoPelaNavegacao(RPCargoFuncao.cNavUltimo, 0) + 1;
        this.CAF_ID = ProximoCodigo;
    }

    public void Salvar() {
        RPCargoFuncao.salvar(this);
    }

    public void Atualizar() {
        RPCargoFuncao.atualizar(this);
    }

    public void RecuperaObjeto(int iCodigo) {
        MDCargoFuncao admTemp = RPCargoFuncao.RecuperaObjCodigo(iCodigo);
        this.setCAF_ID(admTemp.getCAF_ID());
        this.setCAF_NOME(admTemp.getCAF_NOME());
    }

    public ArrayList<MDCargoFuncao> RecuperaObjeto(String sCampo, String sValor, boolean bEmQualquerParte ) {
        String NomeCampo = "";
        if (sCampo.equals("0"))
            NomeCampo = "CAF_ID";
        else
            NomeCampo = "CAF_NOME";
        
        return RPCargoFuncao.PesquisaObjeto(NomeCampo, sValor, bEmQualquerParte);
    }   
    
    public void Excluir() {
        RPCargoFuncao.excluir(this);
    }

    public void RecuperaObjetoNavegacao(int iOpcao, int icodigoAtual) {
        int codNav = RPCargoFuncao.PegaCodigoPelaNavegacao(iOpcao, icodigoAtual);
        RecuperaObjeto(codNav);
    }
}