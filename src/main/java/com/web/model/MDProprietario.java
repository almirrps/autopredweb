package com.web.model;

import com.web.repository.RPProprietario;
import java.util.ArrayList;

public class MDProprietario {

    private Integer PRO_ID, CID_ID, BAN_ID, ADM_ID;
    private String PRO_CPF, PRO_NOME, PRO_ENDERECO, PRO_NUMERO, 
                   PRO_BAIRRO, PRO_COMPLEMENTO, PRO_TELEFONE;

    public MDProprietario() {
        this.PRO_ID          = -1;
        this.PRO_CPF         = "";
        this.PRO_NOME        = "";
        this.PRO_ENDERECO    = "";
        this.PRO_NUMERO      = "";
        this.PRO_BAIRRO      = "";
        this.PRO_COMPLEMENTO = "";
        this.CID_ID          = -1;
        this.PRO_TELEFONE    = "";
        this.BAN_ID          = -1;
        this.ADM_ID          = -1;        
    }

    public Integer getPRO_ID() {
        return PRO_ID;
    }

    public String getPRO_CPF() {
        return PRO_CPF;
    }
    
    public String getPRO_NOME() {
        return PRO_NOME;
    }
    
    public String getPRO_ENDERECO() {
        return PRO_ENDERECO;
    }
    
    public String getPRO_NUMERO() {
        return PRO_NUMERO;
    }
    
    public String getPRO_BAIRRO() {
        return  PRO_BAIRRO;
    }
    
    public String getPRO_COMPLEMENTO() {
        return PRO_COMPLEMENTO;
    }
        
    public Integer getCID_ID() {
        return CID_ID;
    }
    
    public String getPRO_TELEFONE() {
        return PRO_TELEFONE;
    }
    
    public Integer getBAN_ID() {
        return BAN_ID;
    }
    
    public Integer getADM_ID() {
        return ADM_ID;
    }    
    
    public void setPRO_ID(Integer PRO_ID) {
        this.PRO_ID = PRO_ID;
    }

    public void setPRO_CPF(String PRO_CPF) {
        this.PRO_CPF = PRO_CPF;
    }
    
    public void setPRO_NOME(String PRO_NOME) {
        this.PRO_NOME = PRO_NOME.toUpperCase();
    }
    
    public void setPRO_ENDERECO(String PRO_ENDERECO) {
        this.PRO_ENDERECO = PRO_ENDERECO.toUpperCase();
    }
    
    public void setPRO_NUMERO(String PRO_NUMERO) {
        this.PRO_NUMERO = PRO_NUMERO;
    }
    
    public void setPRO_BAIRRO(String PRO_BAIRRO) {
        this.PRO_BAIRRO = PRO_BAIRRO.toUpperCase();
    }
    
    public void setPRO_COMPLEMENTO(String PRO_COMPLEMENTO) {
        this.PRO_COMPLEMENTO = PRO_COMPLEMENTO.toUpperCase();
    }
        
    public void setCID_ID(Integer CID_ID) {
        this.CID_ID = CID_ID;
    }
    
    public void setPRO_TELEFONE(String PRO_TELEFONE) {
        this.PRO_TELEFONE = PRO_TELEFONE;
    }
    
    public void setBAN_ID(Integer BAN_ID) {
        this.BAN_ID = BAN_ID;
    }
    
    public void setADM_ID(Integer ADM_ID) {
        this.ADM_ID = ADM_ID;
    }

    public void setProximoCodigoInsercao() {
        int ProximoCodigo = RPProprietario.PegaCodigoPelaNavegacao(RPProprietario.cNavUltimo, 0) + 1;
        this.PRO_ID = ProximoCodigo;
    }

    public void Salvar() {
        RPProprietario.salvar(this);
    }

    public void Atualizar() {
        RPProprietario.atualizar(this);
    }

    public void RecuperaObjeto(int iCodigo) {
        MDProprietario propTemp = RPProprietario.RecuperaObjCodigo(iCodigo);
        this.setPRO_ID(propTemp.getPRO_ID());

        this.setPRO_CPF(propTemp.getPRO_CPF());
        this.setPRO_NOME(propTemp.getPRO_NOME());
        this.setPRO_ENDERECO(propTemp.getPRO_ENDERECO());
        this.setPRO_NUMERO(propTemp.getPRO_NUMERO());
        this.setPRO_BAIRRO(propTemp.getPRO_BAIRRO());
        this.setPRO_COMPLEMENTO(propTemp.getPRO_COMPLEMENTO());
        this.setCID_ID(propTemp.getCID_ID());
        this.setPRO_TELEFONE(propTemp.getPRO_TELEFONE());
        this.setBAN_ID(propTemp.getBAN_ID());
        this.setADM_ID(propTemp.getADM_ID());        
    }

    public ArrayList<MDProprietario> RecuperaObjeto(String sCampo, String sValor, boolean bEmQualquerParte ) {
        String NomeCampo = "";
        if (sCampo.equals("0"))
            NomeCampo = "PRO_ID";
        else
            NomeCampo = "PRO_NOME";
        
        return RPProprietario.PesquisaObjeto(NomeCampo, sValor, bEmQualquerParte);
    }   
    
    public void Excluir() {
        RPProprietario.excluir(this);
    }

    public void RecuperaObjetoNavegacao(int iOpcao, int icodigoAtual) {
        int codNav = RPProprietario.PegaCodigoPelaNavegacao(iOpcao, icodigoAtual);
        RecuperaObjeto(codNav);
    }
}