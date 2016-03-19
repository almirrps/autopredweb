package com.web.model;

import com.web.repository.RPEspaco;
import java.util.ArrayList;

public class MDEspaco {

    private Integer ESP_ID, TIP_ID, ESP_ANO, ESP_COMPRIMENTO, ESP_LARGURA, ESP_ALTURA, ESP_AREA, IMO_ID;
    private String ESP_DESCRICAO;

    public MDEspaco() {
        this.ESP_ID          = -1;
        this.ESP_DESCRICAO   = "";
        this.TIP_ID          = -1;
        this.ESP_ANO         = -1;
        this.ESP_COMPRIMENTO = -1;
        this.ESP_LARGURA     = -1;
        this.ESP_ALTURA      = -1;
        this.ESP_AREA        = -1;
        this.IMO_ID          = -1;
    }

    public Integer getESP_ID() {
        return ESP_ID;
    }

    public String getESP_DESCRICAO() {
        return ESP_DESCRICAO;
    }

    public Integer getTIP_ID() {
        return TIP_ID;
    }

    public Integer getESP_ANO() {
        return ESP_ANO;
    }

    public Integer getESP_COMPRIMENTO() {
        return ESP_COMPRIMENTO;
    }

    public Integer getESP_LARGURA() {
        return ESP_LARGURA;
    }
    
    public Integer getESP_ALTURA() {
        return ESP_ALTURA;
    }

    public Integer getESP_AREA() {
        return ESP_AREA;
    }

    public Integer getIMO_ID() {
        return IMO_ID;
    }

    public void setESP_ID(Integer ESP_ID) {
        this.ESP_ID = ESP_ID;
    }

    public void setESP_DESCRICAO(String ESP_DESCRICAO) {

        this.ESP_DESCRICAO = ESP_DESCRICAO.toUpperCase();

    }

    public void setTIP_ID(Integer TIP_ID) {
        this.TIP_ID = TIP_ID;
    }

    public void setESP_ANO(Integer ESP_ANO) {
        this.ESP_ANO = ESP_ANO;
    }

    public void setESP_COMPRIMENTO(Integer ESP_COMPRIMENTO) {
        this.ESP_COMPRIMENTO = ESP_COMPRIMENTO;
    }

    public void setESP_LARGURA(Integer ESP_LARGURA) {
        this.ESP_LARGURA = ESP_LARGURA;
    }
    
    public void setESP_ALTURA(Integer ESP_ALTURA) {
        this.ESP_ALTURA = ESP_ALTURA;
    }

    public void setESP_AREA(Integer ESP_AREA) {
        this.ESP_AREA = ESP_AREA;
    }

    public void setIMO_ID(Integer IMO_ID) {
        this.IMO_ID = IMO_ID;
    }

    public void setProximoCodigoInsercao() {
        int ProximoCodigo = RPEspaco.PegaCodigoPelaNavegacao(RPEspaco.cNavUltimo, 0) + 1;
        this.ESP_ID = ProximoCodigo;
    }

    public void Salvar() {
        RPEspaco.salvar(this);
    }

    public void Atualizar() {
        RPEspaco.atualizar(this);
    }

    public void RecuperaObjeto(int iCodigo) {
        MDEspaco espTemp = RPEspaco.RecuperaObjCodigo(iCodigo);
        this.setESP_ID(espTemp.getESP_ID());
        this.setESP_DESCRICAO(espTemp.getESP_DESCRICAO());
        this.setTIP_ID(espTemp.getTIP_ID());
        this.setESP_ANO(espTemp.getESP_ANO());
        this.setESP_COMPRIMENTO(espTemp.getESP_COMPRIMENTO());
        this.setESP_LARGURA(espTemp.getESP_LARGURA());
        this.setESP_ALTURA(espTemp.getESP_ALTURA());
        this.setESP_AREA(espTemp.getESP_AREA());
        this.setIMO_ID(espTemp.getIMO_ID());
    }

    public ArrayList<MDEspaco> RecuperaObjeto(String sCampo, String sValor, boolean bEmQualquerParte ) {
        String NomeCampo = "";
        if (sCampo.equals("0"))
            NomeCampo = "ESP_ID";
        else
            NomeCampo = "ESP_DESCRICAO";
        
        return RPEspaco.PesquisaObjeto(NomeCampo, sValor, bEmQualquerParte);
    }   
    
    public void Excluir() {
        RPEspaco.excluir(this);
    }

    public void RecuperaObjetoNavegacao(int iOpcao, int icodigoAtual) {
        int codNav = RPEspaco.PegaCodigoPelaNavegacao(iOpcao, icodigoAtual);
        RecuperaObjeto(codNav);
    }
}