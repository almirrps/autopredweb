package com.web.model;

import com.web.repository.RPFornecedor;
import java.util.ArrayList;

public class MDFornecedor {

    private Integer FOR_ID, CID_ID;
    private String FOR_RAZAO, FOR_CNPJ, FOR_IE, FOR_IM, FOR_SEGMENTO, FOR_ENDERECO, FOR_NUMERO,  
                   FOR_BAIRRO, FOR_COMPLEMENTO, FOR_TELEFONE, FOR_RESPONSAVEL, FOR_EMAIL;

    public MDFornecedor() {
        this.FOR_ID          = -1;
        this.FOR_RAZAO       = "";
        this.FOR_CNPJ        = ""; 
        this.FOR_IE          = "";
        this.FOR_IM          = "";
        this.FOR_SEGMENTO    = "";
        this.FOR_ENDERECO    = "";
        this.FOR_NUMERO      = "";
        this.FOR_BAIRRO      = "";
        this.FOR_COMPLEMENTO = "";
        this.CID_ID          = -1; 
        this.FOR_TELEFONE    = "";
        this.FOR_RESPONSAVEL = "";
        this.FOR_EMAIL       = "";
    }

    public Integer getFOR_ID() {
        return FOR_ID;
    }

    public String getFOR_RAZAO() {
        return FOR_RAZAO;
    }

    public String getFOR_CNPJ() {
        return FOR_CNPJ;
    }

    public String getFOR_IE() {
        return FOR_IE;
    }
    
    public String getFOR_IM() {
        return FOR_IM;
    }
    
    public String getFOR_SEGMENTO() {
        return FOR_SEGMENTO;
    }

    public String getFOR_ENDERECO() {
        return FOR_ENDERECO;
    }
    
    public String getFOR_NUMERO() {
        return FOR_NUMERO;
    }
    
    public String getFOR_BAIRRO() {
        return FOR_BAIRRO;
    }
    
    public String getFOR_COMPLEMENTO() {
        return FOR_COMPLEMENTO;
    }
    
    
    public Integer getCID_ID() {
        return CID_ID;
    }

    public String getFOR_TELEFONE() {
        return FOR_TELEFONE;
    }

    public String getFOR_RESPONSAVEL() {
        return FOR_RESPONSAVEL;
    }
    
    public String getFOR_EMAIL() {
        return FOR_EMAIL;
    }
    
    
    public void setFOR_ID(Integer FOR_ID) {
        this.FOR_ID = FOR_ID;
    }

    public void setFOR_RAZAO(String FOR_RAZAO) {

        this.FOR_RAZAO = FOR_RAZAO.toUpperCase();

    }

    public void setFOR_CNPJ(String FOR_CNPJ) {
        this.FOR_CNPJ = FOR_CNPJ;
    }

    public void setFOR_IE(String FOR_IE) {
        this.FOR_IE = FOR_IE;
    }
    
    public void setFOR_IM(String FOR_IM) {
        this.FOR_IM = FOR_IM;
    }
    
    public void setFOR_SEGMENTO(String FOR_SEGMENTO) {
        this.FOR_SEGMENTO = FOR_SEGMENTO.toUpperCase();
    }

    public void setFOR_ENDERECO(String FOR_ENDERECO) {
        this.FOR_ENDERECO = FOR_ENDERECO.toUpperCase();
    }
    
    public void setFOR_NUMERO(String FOR_NUMERO) {
        this.FOR_NUMERO = FOR_NUMERO;
    }
    
    public void setFOR_BAIRRO(String FOR_BAIRRO) {
        this.FOR_BAIRRO = FOR_BAIRRO.toUpperCase();
    }

    public void setFOR_COMPLEMENTO(String FOR_COMPLEMENTO) {
        this.FOR_COMPLEMENTO = FOR_COMPLEMENTO;
    }
        
    public void setCID_ID(Integer CID_ID) {
        this.CID_ID = CID_ID;
    }

    public void setFOR_TELEFONE(String FOR_TELEFONE) {
        this.FOR_TELEFONE = FOR_TELEFONE.toUpperCase();
    }

    public void setFOR_RESPONSAVEL(String FOR_RESPONSAVEL) {
        this.FOR_RESPONSAVEL = FOR_RESPONSAVEL;
    }
    
    public void setFOR_EMAIL(String FOR_EMAIL) {
        this.FOR_EMAIL = FOR_EMAIL;
    }

    public void setProximoCodigoInsercao() {
        int ProximoCodigo = RPFornecedor.PegaCodigoPelaNavegacao(RPFornecedor.cNavUltimo, 0) + 1;
        this.FOR_ID = ProximoCodigo;
    }

    public void Salvar() {
        RPFornecedor.salvar(this);
    }

    public void Atualizar() {
        RPFornecedor.atualizar(this);
    }

    public void RecuperaObjeto(int iCodigo) {
        MDFornecedor fornTemp = RPFornecedor.RecuperaObjCodigo(iCodigo);
        this.setFOR_ID(fornTemp.getFOR_ID());
        this.setFOR_RAZAO(fornTemp.getFOR_RAZAO());
        this.setFOR_CNPJ(fornTemp.getFOR_CNPJ());
        this.setFOR_IE(fornTemp.getFOR_IE());
        this.setFOR_IM(fornTemp.getFOR_IM());
        this.setFOR_SEGMENTO(fornTemp.getFOR_SEGMENTO());
        this.setFOR_ENDERECO(fornTemp.getFOR_ENDERECO());
        this.setFOR_NUMERO(fornTemp.getFOR_NUMERO());
        this.setFOR_BAIRRO(fornTemp.getFOR_BAIRRO());
        this.setFOR_COMPLEMENTO(fornTemp.getFOR_COMPLEMENTO());
        this.setCID_ID(fornTemp.getCID_ID());
        this.setFOR_TELEFONE(fornTemp.getFOR_TELEFONE());
        this.setFOR_RESPONSAVEL(fornTemp.getFOR_RESPONSAVEL());
        this.setFOR_EMAIL(fornTemp.getFOR_EMAIL());
    }

    public ArrayList<MDFornecedor> RecuperaObjeto(String sCampo, String sValor, boolean bEmQualquerParte ) {
        String NomeCampo = "";
        if (sCampo.equals("0"))
            NomeCampo = "FOR_ID";
        else
            NomeCampo = "FOR_RAZAO";
        
        return RPFornecedor.PesquisaObjeto(NomeCampo, sValor, bEmQualquerParte);
    }   
    
    public void Excluir() {
        RPFornecedor.excluir(this);
    }

    public void RecuperaObjetoNavegacao(int iOpcao, int icodigoAtual) {
        int codNav = RPFornecedor.PegaCodigoPelaNavegacao(iOpcao, icodigoAtual);
        RecuperaObjeto(codNav);
    }
}