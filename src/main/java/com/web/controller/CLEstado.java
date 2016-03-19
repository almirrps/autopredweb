package com.web.controller;

import com.web.model.MDEstado;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

public class CLEstado {
    private final MDEstado objEstado;
    
    public CLEstado() {
        this.objEstado = new MDEstado();
    }

    public int Salvar(ArrayList<String> pLista) {
        this.objEstado.setEST_NOME(pLista.get(1));
        this.objEstado.setEST_SIGLA(pLista.get(2));
        this.objEstado.setProximoCodigoInsercao();

        this.objEstado.Salvar();

        return this.objEstado.getEST_ID();
    }

    public void Atualizar(ArrayList<String> pLista) {
        this.objEstado.setEST_ID(Integer.parseInt(pLista.get(0)));
        this.objEstado.setEST_NOME(pLista.get(1));
        this.objEstado.setEST_SIGLA(pLista.get(2));

        this.objEstado.Atualizar();
    }

    private ArrayList<String> converterObjetoParaArray() {
        ArrayList<String> vetCampos = new ArrayList<String>();
        vetCampos.add(String.valueOf(this.objEstado.getEST_ID()));
        vetCampos.add(this.objEstado.getEST_NOME());
        vetCampos.add(this.objEstado.getEST_SIGLA());

        return vetCampos;
    }

    public ArrayList<String> RecuperaObjeto(int iCodigo) {
        this.objEstado.RecuperaObjeto(iCodigo);
        return converterObjetoParaArray();
    }

    public ArrayList<String> RecuperaObjeto(String sCampo, String sValor, boolean bEmQualquerParte) {
        this.objEstado.RecuperaObjeto(sCampo, sValor, bEmQualquerParte);
        return converterObjetoParaArray();
    }
    
    public ArrayList<String> RecuperaObjetoNavegacao(int iOpcao, int iCodigo) {
        this.objEstado.RecuperaObjetoNavegacao(iOpcao, iCodigo);
        return converterObjetoParaArray();
    }

    public void Excluir(int iChave) {
        this.objEstado.setEST_ID(iChave);
        this.objEstado.Excluir();
    }
    
    public DefaultTableModel PesquisaObjeto(ArrayList<String> Parametros, DefaultTableModel NomeTabela) {
        // Decodificando o arraylist em variaveis
        String Campo = Parametros.get(0);
        String Valor = Parametros.get(1);
        boolean EmQualquerParte = Parametros.get(2).equals("S");
        
        // Efetuamos a pesquisa de objetos no campo de dados
        ArrayList<MDEstado> Estados = this.objEstado.RecuperaObjeto(Campo, Valor, EmQualquerParte);
        
        
        // Preenchendo a tabela com os dados retornados
        Vector<String> vetVetor;
        MDEstado objEstadoBuffer;
        for (int i=0; i<Estados.size(); i++) {
           vetVetor = new Vector<String>();
           objEstadoBuffer = Estados.get(i);
            
           vetVetor.addElement(String.valueOf( objEstadoBuffer.getEST_ID()) );
           vetVetor.addElement(objEstadoBuffer.getEST_NOME() );
           vetVetor.addElement(objEstadoBuffer.getEST_SIGLA() );
           NomeTabela.addRow(vetVetor);
        }
        return NomeTabela;
    }
}