package com.web.model;

import com.web.repository.RPTipoEspaco;
import java.util.ArrayList;

public class MDTipoEspaco {

    private Integer TIP_ID;
    private String TIP_NOME;

    public MDTipoEspaco() {
        this.TIP_ID      = -1;
        this.TIP_NOME    = "";
    }

    public Integer getTIP_ID() {
        return TIP_ID;
    }

    public String getTIP_NOME() {
        return TIP_NOME;
    }
    
    public void setTIP_ID(Integer TIP_ID) {
        this.TIP_ID = TIP_ID;
    }

    public void setTIP_NOME(String TIP_NOME) {

        this.TIP_NOME = TIP_NOME.toUpperCase();

    }

    public void setProximoCodigoInsercao() {
        int ProximoCodigo = RPTipoEspaco.PegaCodigoPelaNavegacao(RPTipoEspaco.cNavUltimo, 0) + 1;
        this.TIP_ID = ProximoCodigo;
    }

    public void Salvar() {
        RPTipoEspaco.salvar(this);
    }

    public void Atualizar() {
        RPTipoEspaco.atualizar(this);
    }

    public void RecuperaObjeto(int iCodigo) {
        MDTipoEspaco admTemp = RPTipoEspaco.RecuperaObjCodigo(iCodigo);
        this.setTIP_ID(admTemp.getTIP_ID());
        this.setTIP_NOME(admTemp.getTIP_NOME());
    }

    public ArrayList<MDTipoEspaco> RecuperaObjeto(String sCampo, String sValor, boolean bEmQualquerParte ) {
        String NomeCampo = "";
        if (sCampo.equals("0"))
            NomeCampo = "TIP_ID";
        else
            NomeCampo = "TIP_NOME";
        
        return RPTipoEspaco.PesquisaObjeto(NomeCampo, sValor, bEmQualquerParte);
    }   
    
    public void Excluir() {
        RPTipoEspaco.excluir(this);
    }

    public void RecuperaObjetoNavegacao(int iOpcao, int icodigoAtual) {
        int codNav = RPTipoEspaco.PegaCodigoPelaNavegacao(iOpcao, icodigoAtual);
        RecuperaObjeto(codNav);
    }
}