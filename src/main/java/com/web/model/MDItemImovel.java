package com.web.model;

import com.web.repository.RPItemImovel;
import java.util.ArrayList;

public class MDItemImovel {

    private Integer ITE_ID, ITE_UNIDADE, ITE_QUANTIDADE, ITE_VIDA_UTIL, ITE_CUSTO_MEDIO, IMO_ID;
    private String ITE_DESCRICAO;

    public MDItemImovel() {
        this.ITE_ID          = -1;
        this.ITE_DESCRICAO   = "";
        this.ITE_UNIDADE     = -1;
        this.ITE_QUANTIDADE  = -1;
        this.ITE_VIDA_UTIL   = -1;
        this.ITE_CUSTO_MEDIO = -1;
        this.IMO_ID          = -1;
    }

    public Integer getITE_ID() {
        return ITE_ID;
    }

    public String getITE_DESCRICAO() {
        return ITE_DESCRICAO;
    }

    public Integer getITE_UNIDADE() {
        return ITE_UNIDADE;
    }

    public Integer getITE_QUANTIDADE() {
        return ITE_QUANTIDADE;
    }
    
    public Integer getITE_VIDA_UTIL() {
        return ITE_VIDA_UTIL;
    }

    public Integer getITE_CUSTO_MEDIO() {
        return ITE_CUSTO_MEDIO;
    }

    public Integer getIMO_ID() {
        return IMO_ID;
    }

    public void setITE_ID(Integer ITE_ID) {
        this.ITE_ID = ITE_ID;
    }

    public void setITE_DESCRICAO(String ITE_DESCRICAO) {
        this.ITE_DESCRICAO = ITE_DESCRICAO.toUpperCase();
    }

    public void setITE_UNIDADE(Integer ITE_UNIDADE) {
        this.ITE_UNIDADE = ITE_UNIDADE;
    }

    public void setITE_QUANTIDADE(Integer ITE_QUANTIDADE) {
        this.ITE_QUANTIDADE = ITE_QUANTIDADE;
    }
    
    public void setITE_VIDA_UTIL(Integer ITE_VIDA_UTIL) {
        this.ITE_VIDA_UTIL = ITE_VIDA_UTIL;
    }

    public void setITE_CUSTO_MEDIO(Integer ITE_CUSTO_MEDIO) {
        this.ITE_CUSTO_MEDIO = ITE_CUSTO_MEDIO;
    }

    public void setIMO_ID(Integer IMO_ID) {
        this.IMO_ID = IMO_ID;
    }

    public void setProximoCodigoInsercao() {
        int ProximoCodigo = RPItemImovel.PegaCodigoPelaNavegacao(RPItemImovel.cNavUltimo, 0) + 1;
        this.ITE_ID = ProximoCodigo;
    }

    public void Salvar() {
        RPItemImovel.salvar(this);
    }

    public void Atualizar() {
        RPItemImovel.atualizar(this);
    }

    public void RecuperaObjeto(int iCodigo) {
        MDItemImovel iteTemp = RPItemImovel.RecuperaObjCodigo(iCodigo);
        this.setITE_ID(iteTemp.getITE_ID());
        this.setITE_DESCRICAO(iteTemp.getITE_DESCRICAO());
        this.setITE_UNIDADE(iteTemp.getITE_UNIDADE());
        this.setITE_QUANTIDADE(iteTemp.getITE_QUANTIDADE());
        this.setITE_VIDA_UTIL(iteTemp.getITE_VIDA_UTIL());
        this.setITE_CUSTO_MEDIO(iteTemp.getITE_CUSTO_MEDIO());
        this.setIMO_ID(iteTemp.getIMO_ID());
    }

    public ArrayList<MDItemImovel> RecuperaObjeto(String sCampo, String sValor, boolean bEmQualquerParte ) {
        String NomeCampo = "";
        if (sCampo.equals("0"))
            NomeCampo = "ITE_ID";
        else
            NomeCampo = "ITE_DESCRICAO";
        
        return RPItemImovel.PesquisaObjeto(NomeCampo, sValor, bEmQualquerParte);
    }   
    
    public void Excluir() {
        RPItemImovel.excluir(this);
    }

    public void RecuperaObjetoNavegacao(int iOpcao, int icodigoAtual) {
        int codNav = RPItemImovel.PegaCodigoPelaNavegacao(iOpcao, icodigoAtual);
        RecuperaObjeto(codNav);
    }
}