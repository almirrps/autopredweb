package com.web.model;

import com.web.repository.RPBanco;
import java.util.ArrayList;

public class MDBanco {

    private Integer BAN_ID, BAN_NUMERO;
    private String BAN_NOME, BAN_AGENCIA, BAN_CONTA;

    public MDBanco() {
        this.BAN_ID      = -1;
        this.BAN_NUMERO  = -1;
        this.BAN_NOME    = "";
        this.BAN_AGENCIA = "";
        this.BAN_CONTA   = "";
    }

    public Integer getBAN_ID() {
        return BAN_ID;
    }

    public Integer getBAN_NUMERO() {
        return BAN_NUMERO;
    }

    public String getBAN_NOME() {
        return BAN_NOME;
    }

    public String getBAN_AGENCIA() {
        return BAN_AGENCIA;
    }

    public String getBAN_CONTA() {
        return BAN_CONTA;
    }
    
    public void setBAN_ID(Integer BAN_ID) {
        this.BAN_ID = BAN_ID;
    }

    public void setBAN_NUMERO(Integer BAN_NUMERO) {
        this.BAN_NUMERO = BAN_NUMERO;
    }

    public void setBAN_NOME(String BAN_NOME) {
        this.BAN_NOME = BAN_NOME.toUpperCase();
    }

    public void setBAN_AGENCIA(String BAN_AGENCIA) {
        this.BAN_AGENCIA = BAN_AGENCIA;
    }

    public void setBAN_CONTA(String BAN_CONTA) {
        this.BAN_CONTA = BAN_CONTA;
    }
    
    public void setProximoCodigoInsercao() {
        int ProximoCodigo = RPBanco.PegaCodigoPelaNavegacao(RPBanco.cNavUltimo, 0) + 1;
        this.BAN_ID = ProximoCodigo;
    }

    public void Salvar() {
        RPBanco.salvar(this);
    }

    public void Atualizar() {
        RPBanco.atualizar(this);
    }

    public void RecuperaObjeto(int iCodigo) {
        MDBanco banTemp = RPBanco.RecuperaObjCodigo(iCodigo);
        this.setBAN_ID(banTemp.getBAN_ID());
        this.setBAN_NUMERO(banTemp.getBAN_NUMERO());
        this.setBAN_NOME(banTemp.getBAN_NOME());
        this.setBAN_AGENCIA(banTemp.getBAN_AGENCIA());
        this.setBAN_CONTA(banTemp.getBAN_CONTA());
    }

    public ArrayList<MDBanco> RecuperaObjeto(String sCampo, String sValor, boolean bEmQualquerParte ) {
        String NomeCampo = "";
        if (sCampo.equals("0"))
            NomeCampo = "BAN_ID";
        else if (sCampo.equals("1"))
            NomeCampo = "BAN_NUMERO";
        else
            NomeCampo = "BAN_NOME";
        
        return RPBanco.PesquisaObjeto(NomeCampo, sValor, bEmQualquerParte);
    }   
    
    public void Excluir() {
        RPBanco.excluir(this);
    }

    public void RecuperaObjetoNavegacao(int iOpcao, int icodigoAtual) {
        int codNav = RPBanco.PegaCodigoPelaNavegacao(iOpcao, icodigoAtual);
        RecuperaObjeto(codNav);
    }
}