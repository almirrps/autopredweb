package com.web.controller;

import com.web.model.MDTipoEspaco;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

public class CLTipoEspaco {
    private final MDTipoEspaco objTipoEspaco;
    
    public CLTipoEspaco() {
        this.objTipoEspaco = new MDTipoEspaco();
    }

    public int Salvar(ArrayList<String> pLista) {
        this.objTipoEspaco.setTIP_NOME(pLista.get(1));
        this.objTipoEspaco.setProximoCodigoInsercao();

        this.objTipoEspaco.Salvar();

        return this.objTipoEspaco.getTIP_ID();
    }

    public void Atualizar(ArrayList<String> pLista) {
        this.objTipoEspaco.setTIP_ID(Integer.parseInt(pLista.get(0)));
        this.objTipoEspaco.setTIP_NOME(pLista.get(1));

        this.objTipoEspaco.Atualizar();
    }

    private ArrayList<String> converterObjetoParaArray() {
        ArrayList<String> vetCampos = new ArrayList<String>();
        vetCampos.add(String.valueOf(this.objTipoEspaco.getTIP_ID()));
        vetCampos.add(this.objTipoEspaco.getTIP_NOME());

        return vetCampos;
    }

    public ArrayList<String> RecuperaObjeto(int iCodigo) {
        this.objTipoEspaco.RecuperaObjeto(iCodigo);
        return converterObjetoParaArray();
    }

    public ArrayList<String> RecuperaObjeto(String sCampo, String sValor, boolean bEmQualquerParte) {
        this.objTipoEspaco.RecuperaObjeto(sCampo, sValor, bEmQualquerParte);
        return converterObjetoParaArray();
    }
    
    public ArrayList<String> RecuperaObjetoNavegacao(int iOpcao, int iCodigo) {
        this.objTipoEspaco.RecuperaObjetoNavegacao(iOpcao, iCodigo);
        return converterObjetoParaArray();
    }

    public void Excluir(int iChave) {
        this.objTipoEspaco.setTIP_ID(iChave);
        this.objTipoEspaco.Excluir();

    }
    
    public DefaultTableModel PesquisaObjeto(ArrayList<String> Parametros, DefaultTableModel NomeTabela) {
        // Decodificando o arraylist em variaveis
        String Campo = Parametros.get(0);
        String Valor = Parametros.get(1);
        boolean EmQualquerParte = Parametros.get(2).equals("S");
        
        // Efetuamos a pesquisa de objetos no campo de dados
        ArrayList<MDTipoEspaco> TipoEspaco = this.objTipoEspaco.RecuperaObjeto(Campo, Valor, EmQualquerParte);
        
        
        // Preenchendo a tabela com os dados retornados
        Vector<String> vetVetor;
        MDTipoEspaco objTipoEspacoBuffer;
        for (int i=0; i<TipoEspaco.size(); i++) {
           vetVetor = new Vector<String>();
           objTipoEspacoBuffer = TipoEspaco.get(i);
            
           vetVetor.addElement(String.valueOf( objTipoEspacoBuffer.getTIP_ID()) );
           vetVetor.addElement(objTipoEspacoBuffer.getTIP_NOME() );
           NomeTabela.addRow(vetVetor);
        }
        return NomeTabela;
    }
}