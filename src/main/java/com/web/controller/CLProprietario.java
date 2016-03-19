package com.web.controller;

import com.web.model.MDProprietario;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

public class CLProprietario {
    private final MDProprietario objProprietario;
    
    public CLProprietario() {
        this.objProprietario = new MDProprietario();
    }

    public int Salvar(ArrayList<String> pLista) {
        this.objProprietario.setPRO_CPF(pLista.get(1));
        this.objProprietario.setPRO_NOME(pLista.get(2));
        this.objProprietario.setPRO_ENDERECO(pLista.get(3));
        this.objProprietario.setPRO_NUMERO(pLista.get(4));
        this.objProprietario.setPRO_BAIRRO(pLista.get(5));
        this.objProprietario.setPRO_COMPLEMENTO(pLista.get(6));
        this.objProprietario.setCID_ID(Integer.parseInt(pLista.get(7)));
        this.objProprietario.setPRO_TELEFONE(pLista.get(8));
        this.objProprietario.setBAN_ID(Integer.parseInt(pLista.get(9)));
        this.objProprietario.setADM_ID(Integer.parseInt(pLista.get(10)));
        
        this.objProprietario.setProximoCodigoInsercao();

        this.objProprietario.Salvar();

        return this.objProprietario.getPRO_ID();
    }

    public void Atualizar(ArrayList<String> pLista) {
        this.objProprietario.setPRO_ID(Integer.parseInt(pLista.get(0)));
        this.objProprietario.setPRO_CPF(pLista.get(1));
        this.objProprietario.setPRO_NOME(pLista.get(2));
        this.objProprietario.setPRO_ENDERECO(pLista.get(3));
        this.objProprietario.setPRO_NUMERO(pLista.get(4));
        this.objProprietario.setPRO_BAIRRO(pLista.get(5));
        this.objProprietario.setPRO_COMPLEMENTO(pLista.get(6));
        this.objProprietario.setCID_ID(Integer.parseInt(pLista.get(7)));
        this.objProprietario.setPRO_TELEFONE(pLista.get(8));
        this.objProprietario.setBAN_ID(Integer.parseInt(pLista.get(9)));
        this.objProprietario.setADM_ID(Integer.parseInt(pLista.get(10)));

        this.objProprietario.Atualizar();
    }

    private ArrayList<String> converterObjetoParaArray() {
        ArrayList<String> vetCampos = new ArrayList<String>();
        vetCampos.add(String.valueOf(this.objProprietario.getPRO_ID()));
        vetCampos.add(this.objProprietario.getPRO_CPF());
        vetCampos.add(this.objProprietario.getPRO_NOME());
        vetCampos.add(this.objProprietario.getPRO_ENDERECO());
        vetCampos.add(this.objProprietario.getPRO_NUMERO());
        vetCampos.add(this.objProprietario.getPRO_BAIRRO());
        vetCampos.add(this.objProprietario.getPRO_COMPLEMENTO());
        vetCampos.add(String.valueOf(this.objProprietario.getCID_ID()));
        vetCampos.add(this.objProprietario.getPRO_TELEFONE());
        vetCampos.add(String.valueOf(this.objProprietario.getBAN_ID()));
        vetCampos.add(String.valueOf(this.objProprietario.getADM_ID()));

        return vetCampos;
    }

    public ArrayList<String> RecuperaObjeto(int iCodigo) {
        this.objProprietario.RecuperaObjeto(iCodigo);
        return converterObjetoParaArray();
    }

    public ArrayList<String> RecuperaObjeto(String sCampo, String sValor, boolean bEmQualquerParte) {
        this.objProprietario.RecuperaObjeto(sCampo, sValor, bEmQualquerParte);
        return converterObjetoParaArray();
    }
    
    public ArrayList<String> RecuperaObjetoNavegacao(int iOpcao, int iCodigo) {
        this.objProprietario.RecuperaObjetoNavegacao(iOpcao, iCodigo);
        return converterObjetoParaArray();
    }

    public void Excluir(int iChave) {
        this.objProprietario.setPRO_ID(iChave);
        this.objProprietario.Excluir();

    }
    
    public DefaultTableModel PesquisaObjeto(ArrayList<String> Parametros, DefaultTableModel NomeTabela) {
        // Decodificando o arraylist em variaveis
        String Campo = Parametros.get(0);
        String Valor = Parametros.get(1);
        boolean EmQualquerParte = Parametros.get(2).equals("S");
        
        // Efetuamos a pesquisa de objetos no campo de dados
        ArrayList<MDProprietario> Proprietarios = this.objProprietario.RecuperaObjeto(Campo, Valor, EmQualquerParte);
        
        
        // Preenchendo a tabela com os dados retornados
        Vector<String> vetVetor;
        MDProprietario objProprietarioBuffer;
        for (int i=0; i<Proprietarios.size(); i++) {
           vetVetor = new Vector<String>();
           objProprietarioBuffer = Proprietarios.get(i);
            
           vetVetor.addElement(String.valueOf( objProprietarioBuffer.getPRO_ID()) );
           vetVetor.addElement(objProprietarioBuffer.getPRO_CPF());
           vetVetor.addElement(objProprietarioBuffer.getPRO_NOME());
           vetVetor.addElement(objProprietarioBuffer.getPRO_ENDERECO());
           vetVetor.addElement(objProprietarioBuffer.getPRO_NUMERO());
           vetVetor.addElement(objProprietarioBuffer.getPRO_BAIRRO());
           vetVetor.addElement(objProprietarioBuffer.getPRO_COMPLEMENTO());
           vetVetor.addElement(String.valueOf( objProprietarioBuffer.getCID_ID()));
           vetVetor.addElement(objProprietarioBuffer.getPRO_TELEFONE());
           vetVetor.addElement(String.valueOf( objProprietarioBuffer.getBAN_ID()));
           vetVetor.addElement(String.valueOf( objProprietarioBuffer.getADM_ID()));
           
           NomeTabela.addRow(vetVetor);
        }
        return NomeTabela;
    }
}