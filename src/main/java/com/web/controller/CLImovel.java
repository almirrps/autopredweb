package com.web.controller;

import com.web.model.MDImovel;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

public class CLImovel {
    private final MDImovel objImovel;
    
    public CLImovel() {
        this.objImovel = new MDImovel();
    }

    public int Salvar(ArrayList<String> pLista) {
        this.objImovel.setIMO_TIPO(Integer.parseInt(pLista.get(1)));
        this.objImovel.setIMO_ENDERECO(pLista.get(2));
        this.objImovel.setIMO_NUMERO(Integer.parseInt(pLista.get(3)));
        this.objImovel.setIMO_BAIRRO(pLista.get(4));
        this.objImovel.setIMO_COMPLEMENTO(pLista.get(5));
        this.objImovel.setCID_ID(Integer.parseInt(pLista.get(6)));
        this.objImovel.setIMO_TELEFONE(pLista.get(7));        
        this.objImovel.setIMO_METRO_CONS(Integer.parseInt(pLista.get(8)));
        this.objImovel.setIMO_ANO_CONS(Integer.parseInt(pLista.get(9)));
        this.objImovel.setIMO_PLANTA_BAIXA(Byte.parseByte(pLista.get(10)));
        this.objImovel.setIMO_PLANTA_ELETRICA(Byte.parseByte(pLista.get(11)));
        this.objImovel.setIMO_PLANTA_HIDRA(Byte.parseByte(pLista.get(12)));
        this.objImovel.setPRO_ID(Integer.parseInt(pLista.get(13)));
        this.objImovel.setADM_ID(Integer.parseInt(pLista.get(14)));
        this.objImovel.setProximoCodigoInsercao();

        this.objImovel.Salvar();

        return this.objImovel.getIMO_ID();
    }

    public void Atualizar(ArrayList<String> pLista) {
        this.objImovel.setIMO_ID(Integer.parseInt(pLista.get(0)));
        this.objImovel.setIMO_TIPO(Integer.parseInt(pLista.get(1)));
        this.objImovel.setIMO_ENDERECO(pLista.get(2));
        this.objImovel.setIMO_NUMERO(Integer.parseInt(pLista.get(3)));
        this.objImovel.setIMO_BAIRRO(pLista.get(4));
        this.objImovel.setIMO_COMPLEMENTO(pLista.get(5));
        this.objImovel.setCID_ID(Integer.parseInt(pLista.get(6)));
        this.objImovel.setIMO_TELEFONE(pLista.get(7));        
        this.objImovel.setIMO_METRO_CONS(Integer.parseInt(pLista.get(8)));
        this.objImovel.setIMO_ANO_CONS(Integer.parseInt(pLista.get(9)));
        this.objImovel.setIMO_PLANTA_BAIXA(Byte.parseByte(pLista.get(10)));
        this.objImovel.setIMO_PLANTA_ELETRICA(Byte.parseByte(pLista.get(11)));
        this.objImovel.setIMO_PLANTA_HIDRA(Byte.parseByte(pLista.get(12)));
        this.objImovel.setPRO_ID(Integer.parseInt(pLista.get(13)));
        this.objImovel.setADM_ID(Integer.parseInt(pLista.get(14)));

        this.objImovel.Atualizar();
    }

    private ArrayList<String> converterObjetoParaArray() {
        ArrayList<String> vetCampos = new ArrayList<String>();
        vetCampos.add(String.valueOf(this.objImovel.getIMO_ID()));
        vetCampos.add(String.valueOf(this.objImovel.getIMO_TIPO()));
        vetCampos.add(this.objImovel.getIMO_ENDERECO());
        vetCampos.add(String.valueOf(this.objImovel.getIMO_NUMERO()));
        vetCampos.add(this.objImovel.getIMO_BAIRRO());
        vetCampos.add(this.objImovel.getIMO_COMPLEMENTO());        
        vetCampos.add(String.valueOf(this.objImovel.getCID_ID()));
        vetCampos.add(this.objImovel.getIMO_TELEFONE());
        vetCampos.add(String.valueOf(this.objImovel.getIMO_METRO_CONS()));
        vetCampos.add(String.valueOf(this.objImovel.getIMO_ANO_CONS()));
        vetCampos.add(String.valueOf(this.objImovel.getIMO_PLANTA_BAIXA()));
        vetCampos.add(String.valueOf(this.objImovel.getIMO_PLANTA_ELETRICA()));
        vetCampos.add(String.valueOf(this.objImovel.getIMO_PLANTA_HIDRA()));
        vetCampos.add(String.valueOf(this.objImovel.getPRO_ID()));
        vetCampos.add(String.valueOf(this.objImovel.getADM_ID()));

        return vetCampos;
    }

    public ArrayList<String> RecuperaObjeto(int iCodigo) {
        this.objImovel.RecuperaObjeto(iCodigo);
        return converterObjetoParaArray();
    }

    public ArrayList<String> RecuperaObjeto(String sCampo, String sValor, boolean bEmQualquerParte) {
        this.objImovel.RecuperaObjeto(sCampo, sValor, bEmQualquerParte);
        return converterObjetoParaArray();
    }
    
    public ArrayList<String> RecuperaObjetoNavegacao(int iOpcao, int iCodigo) {
        this.objImovel.RecuperaObjetoNavegacao(iOpcao, iCodigo);
        return converterObjetoParaArray();
    }

    public void Excluir(int iChave) {
        this.objImovel.setIMO_ID(iChave);
        this.objImovel.Excluir();

    }
    
    public DefaultTableModel PesquisaObjeto(ArrayList<String> Parametros, DefaultTableModel NomeTabela) {
        // Decodificando o arraylist em variaveis
        String Campo = Parametros.get(0);
        String Valor = Parametros.get(1);
        boolean EmQualquerParte = Parametros.get(2).equals("S");
        
        // Efetuamos a pesquisa de objetos no campo de dados
        ArrayList<MDImovel> Imoveis = this.objImovel.RecuperaObjeto(Campo, Valor, EmQualquerParte);
        
        
        // Preenchendo a tabela com os dados retornados
        Vector<String> vetVetor;
        MDImovel objImovelBuffer;
        for (int i=0; i<Imoveis.size(); i++) {
           vetVetor = new Vector<String>();
           objImovelBuffer = Imoveis.get(i);

           vetVetor.addElement(String.valueOf(objImovelBuffer.getIMO_ID()));
           vetVetor.addElement(String.valueOf(objImovelBuffer.getIMO_TIPO()));
           vetVetor.addElement(objImovelBuffer.getIMO_ENDERECO());
           vetVetor.addElement(String.valueOf(objImovelBuffer.getIMO_NUMERO()));
           vetVetor.addElement(objImovelBuffer.getIMO_BAIRRO());
           vetVetor.addElement(objImovelBuffer.getIMO_COMPLEMENTO());        
           vetVetor.addElement(String.valueOf(objImovelBuffer.getCID_ID()));
           vetVetor.addElement(objImovelBuffer.getIMO_TELEFONE());
           vetVetor.addElement(String.valueOf(objImovelBuffer.getIMO_METRO_CONS()));
           vetVetor.addElement(String.valueOf(objImovelBuffer.getIMO_ANO_CONS()));
           vetVetor.addElement(String.valueOf(objImovelBuffer.getIMO_PLANTA_BAIXA()));
           vetVetor.addElement(String.valueOf(objImovelBuffer.getIMO_PLANTA_ELETRICA()));
           vetVetor.addElement(String.valueOf(objImovelBuffer.getIMO_PLANTA_HIDRA()));
           vetVetor.addElement(String.valueOf(objImovelBuffer.getPRO_ID()));
           vetVetor.addElement(String.valueOf(objImovelBuffer.getADM_ID()));
           
           NomeTabela.addRow(vetVetor);
        }
        return NomeTabela;
    }
}