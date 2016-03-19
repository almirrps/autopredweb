package com.web.controller;

import com.web.model.MDCidade;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

public class CLCidade {
    private final MDCidade objCidade;
    
    public CLCidade() {
        this.objCidade = new MDCidade();
    }

    public int Salvar(ArrayList<String> pLista) {
        this.objCidade.setCID_NOME(pLista.get(1));
        this.objCidade.setEST_ID(Integer.parseInt(pLista.get(2)));
        this.objCidade.setProximoCodigoInsercao();

        this.objCidade.Salvar();

        return this.objCidade.getCID_ID();
    }

    public void Atualizar(ArrayList<String> pLista) {
        this.objCidade.setCID_ID(Integer.parseInt(pLista.get(0)));
        this.objCidade.setCID_NOME(pLista.get(1));
        this.objCidade.setEST_ID(Integer.parseInt(pLista.get(2)));

        this.objCidade.Atualizar();
    }

    private ArrayList<String> converterObjetoParaArray() {
        ArrayList<String> vetCampos = new ArrayList<String>();
        vetCampos.add(String.valueOf(this.objCidade.getCID_ID()));
        vetCampos.add(this.objCidade.getCID_NOME());
        vetCampos.add(String.valueOf(this.objCidade.getEST_ID()));

        return vetCampos;
    }

    public ArrayList<String> RecuperaObjeto(int iCodigo) {
        this.objCidade.RecuperaObjeto(iCodigo);
        return converterObjetoParaArray();
    }

    public ArrayList<String> RecuperaObjeto(String sCampo, String sValor, boolean bEmQualquerParte) {
        this.objCidade.RecuperaObjeto(sCampo, sValor, bEmQualquerParte);
        return converterObjetoParaArray();
    }
    
    public ArrayList<String> RecuperaObjetoNavegacao(int iOpcao, int iCodigo) {
        this.objCidade.RecuperaObjetoNavegacao(iOpcao, iCodigo);
        return converterObjetoParaArray();
    }

    public void Excluir(int iChave) {
        this.objCidade.setCID_ID(iChave);
        this.objCidade.Excluir();

    }
    
    public DefaultTableModel PesquisaObjeto(ArrayList<String> Parametros, DefaultTableModel NomeTabela) {
        // Decodificando o arraylist em variaveis
        String Campo = Parametros.get(0);
        String Valor = Parametros.get(1);
        boolean EmQualquerParte = Parametros.get(2).equals("S");
        
        // Efetuamos a pesquisa de objetos no campo de dados
        ArrayList<MDCidade> Cidades = this.objCidade.RecuperaObjeto(Campo, Valor, EmQualquerParte);
        
        
        // Preenchendo a tabela com os dados retornados
        Vector<String> vetVetor;
        MDCidade objCidadeBuffer;
        for (int i=0; i<Cidades.size(); i++) {
           vetVetor = new Vector<String>();
           objCidadeBuffer = Cidades.get(i);
            
           vetVetor.addElement(String.valueOf( objCidadeBuffer.getCID_ID()) );
           vetVetor.addElement(objCidadeBuffer.getCID_NOME() );
           NomeTabela.addRow(vetVetor);
        }
        return NomeTabela;
    }
}