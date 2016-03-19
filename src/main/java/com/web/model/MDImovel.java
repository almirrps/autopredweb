package com.web.model;

import com.web.repository.RPImovel;
import java.util.ArrayList;

public class MDImovel {

    private Integer IMO_ID, IMO_NUMERO, IMO_TIPO, IMO_METRO_CONS, IMO_ANO_CONS, 
                    CID_ID, PRO_ID, ADM_ID;
    private byte IMO_PLANTA_BAIXA, IMO_PLANTA_ELETRICA, IMO_PLANTA_HIDRA;
    private String IMO_ENDERECO, IMO_BAIRRO, IMO_COMPLEMENTO, IMO_TELEFONE;

    public MDImovel() {
        this.IMO_ID              = -1;
        this.IMO_TIPO            = -1;
        this.IMO_ENDERECO        = "";
        this.IMO_NUMERO          = -1;
        this.IMO_BAIRRO          = "";
        this.IMO_COMPLEMENTO     = "";
        this.CID_ID              = -1;
        this.IMO_TELEFONE        = "";
        this.IMO_METRO_CONS      = -1;
        this.IMO_ANO_CONS        = -1;
        this.IMO_PLANTA_BAIXA    = 0;
        this.IMO_PLANTA_ELETRICA = 0;
        this.IMO_PLANTA_HIDRA    = 0;
        this.PRO_ID              = -1;
        this.ADM_ID              = -1;
    }

    public Integer getIMO_ID() {
        return IMO_ID;
    }

    public Integer getIMO_TIPO() {
        return IMO_TIPO;
    }

    public String getIMO_ENDERECO() {
        return IMO_ENDERECO;
    }

    public Integer getIMO_NUMERO() {
        return IMO_NUMERO;
    }
    
    public String getIMO_BAIRRO() {
        return IMO_BAIRRO;
    }
    
    public String getIMO_COMPLEMENTO() {
        return IMO_COMPLEMENTO;
    }

    public Integer getCID_ID() {
        return CID_ID;
    }
    
    public String getIMO_TELEFONE() {
        return IMO_TELEFONE;
    }
    
    public Integer getIMO_METRO_CONS() {
        return IMO_METRO_CONS;
    }
    
    public Integer getIMO_ANO_CONS() {
        return IMO_ANO_CONS;
    }
    
    
    public Byte getIMO_PLANTA_BAIXA() {
        return IMO_PLANTA_BAIXA;
    }

    public Byte getIMO_PLANTA_ELETRICA() {
        return IMO_PLANTA_ELETRICA;
    }

    public Byte getIMO_PLANTA_HIDRA() {
        return IMO_PLANTA_HIDRA;
    }
    
    public Integer getPRO_ID() {
        return PRO_ID;
    }
    
    public Integer getADM_ID() {
        return ADM_ID;
    }
    
    public void setIMO_ID(Integer IMO_ID) {
        this.IMO_ID = IMO_ID;
    }

    public void setIMO_TIPO(Integer IMO_TIPO) {
        this.IMO_TIPO = IMO_TIPO;
    }

    public void setIMO_ENDERECO(String IMO_ENDERECO) {
        this.IMO_ENDERECO = IMO_ENDERECO.toUpperCase();
    }

    public void setIMO_NUMERO(Integer IMO_NUMERO) {
        this.IMO_NUMERO = IMO_NUMERO;
    }

    public void setIMO_BAIRRO(String IMO_BAIRRO) {
        this.IMO_BAIRRO = IMO_BAIRRO.toUpperCase();
    }

    public void setIMO_COMPLEMENTO(String IMO_COMPLEMENTO) {
        this.IMO_COMPLEMENTO = IMO_COMPLEMENTO.toUpperCase();
    }

    public void setCID_ID(Integer CID_ID) {
        this.CID_ID = CID_ID;
    }

    public void setIMO_TELEFONE(String IMO_TELEFONE) {
        this.IMO_TELEFONE = IMO_TELEFONE.toUpperCase();
    }

    public void setIMO_METRO_CONS(Integer IMO_METRO_CONS) {
        this.IMO_METRO_CONS = IMO_METRO_CONS;
    }

    public void setIMO_ANO_CONS(Integer IMO_ANO_CONS) {
        this.IMO_ANO_CONS = IMO_ANO_CONS;
    }

    public void setIMO_PLANTA_BAIXA(Byte IMO_PLANTA_BAIXA) {
        this.IMO_PLANTA_BAIXA = IMO_PLANTA_BAIXA;
    }

    public void setIMO_PLANTA_ELETRICA(Byte IMO_PLANTA_ELETRICA) {
        this.IMO_PLANTA_ELETRICA = IMO_PLANTA_ELETRICA;
    }

    public void setIMO_PLANTA_HIDRA(Byte IMO_PLANTA_HIDRA) {
        this.IMO_PLANTA_HIDRA = IMO_PLANTA_HIDRA;
    }

    public void setPRO_ID(Integer PRO_ID) {
        this.PRO_ID = PRO_ID;
    }

    public void setADM_ID(Integer ADM_ID) {
        this.ADM_ID = ADM_ID;
    }
        
    public void setProximoCodigoInsercao() {
        int ProximoCodigo = RPImovel.PegaCodigoPelaNavegacao(RPImovel.cNavUltimo, 0) + 1;
        this.IMO_ID = ProximoCodigo;
    }

    public void Salvar() {
        RPImovel.salvar(this);
    }

    public void Atualizar() {
        RPImovel.atualizar(this);
    }

    public void RecuperaObjeto(int iCodigo) {
        MDImovel imoTemp = RPImovel.RecuperaObjCodigo(iCodigo);
        this.setIMO_ID(imoTemp.getIMO_ID());
        this.setIMO_TIPO(imoTemp.getIMO_TIPO());
        this.setIMO_ENDERECO(imoTemp.getIMO_ENDERECO());
        this.setIMO_NUMERO(imoTemp.getIMO_NUMERO());
        this.setIMO_BAIRRO(imoTemp.getIMO_BAIRRO());
        this.setIMO_COMPLEMENTO(imoTemp.getIMO_COMPLEMENTO());
        this.setCID_ID(imoTemp.getCID_ID());
        this.setIMO_TELEFONE(imoTemp.getIMO_TELEFONE());
        this.setIMO_METRO_CONS(imoTemp.getIMO_METRO_CONS());
        this.setIMO_ANO_CONS(imoTemp.getIMO_ANO_CONS());
        this.setIMO_PLANTA_BAIXA(imoTemp.getIMO_PLANTA_BAIXA());
        this.setIMO_PLANTA_ELETRICA(imoTemp.getIMO_PLANTA_ELETRICA());
        this.setIMO_PLANTA_HIDRA(imoTemp.getIMO_PLANTA_HIDRA());
        this.setPRO_ID(imoTemp.getPRO_ID());
        this.setADM_ID(imoTemp.getADM_ID());
    }

    public ArrayList<MDImovel> RecuperaObjeto(String sCampo, String sValor, boolean bEmQualquerParte ) {
        String NomeCampo = "";
        if (sCampo.equals("0"))
            NomeCampo = "IMO_ID";
        else if (sCampo.equals("1"))
            NomeCampo = "IMO_TIPO";
        else
            NomeCampo = "IMO_ENDERECO";
        
        return RPImovel.PesquisaObjeto(NomeCampo, sValor, bEmQualquerParte);
    }   
    
    public void Excluir() {
        RPImovel.excluir(this);
    }

    public void RecuperaObjetoNavegacao(int iOpcao, int icodigoAtual) {
        int codNav = RPImovel.PegaCodigoPelaNavegacao(iOpcao, icodigoAtual);
        RecuperaObjeto(codNav);
    }
}