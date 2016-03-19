package com.web.controller;

import com.web.model.MDEspaco;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

public class CLEspaco {
    private final MDEspaco objEspaco;
    
    public CLEspaco() {
        this.objEspaco = new MDEspaco();
    }

    public int Salvar(ArrayList<String> pLista) {
        this.objEspaco.setESP_DESCRICAO(pLista.get(1));
        this.objEspaco.setTIP_ID(Integer.parseInt(pLista.get(2)));
        this.objEspaco.setESP_ANO(Integer.parseInt(pLista.get(3)));
        this.objEspaco.setESP_COMPRIMENTO(Integer.parseInt(pLista.get(4)));
        this.objEspaco.setESP_LARGURA(Integer.parseInt(pLista.get(5)));
        this.objEspaco.setESP_ALTURA(Integer.parseInt(pLista.get(6)));
        this.objEspaco.setESP_AREA(Integer.parseInt(pLista.get(7)));
        this.objEspaco.setIMO_ID(Integer.parseInt(pLista.get(8)));
        this.objEspaco.setProximoCodigoInsercao();

        this.objEspaco.Salvar();

        return this.objEspaco.getESP_ID();
    }

    public void Atualizar(ArrayList<String> pLista) {
        this.objEspaco.setESP_ID(Integer.parseInt(pLista.get(0)));
        this.objEspaco.setESP_DESCRICAO(pLista.get(1));
        this.objEspaco.setTIP_ID(Integer.parseInt(pLista.get(2)));
        this.objEspaco.setESP_ANO(Integer.parseInt(pLista.get(3)));
        this.objEspaco.setESP_COMPRIMENTO(Integer.parseInt(pLista.get(4)));
        this.objEspaco.setESP_LARGURA(Integer.parseInt(pLista.get(5)));
        this.objEspaco.setESP_ALTURA(Integer.parseInt(pLista.get(6)));
        this.objEspaco.setESP_AREA(Integer.parseInt(pLista.get(7)));
        this.objEspaco.setIMO_ID(Integer.parseInt(pLista.get(8)));

        this.objEspaco.Atualizar();
    }

    private ArrayList<String> converterObjetoParaArray() {
        ArrayList<String> vetCampos = new ArrayList<String>();
        vetCampos.add(String.valueOf(this.objEspaco.getESP_ID()));
        vetCampos.add(this.objEspaco.getESP_DESCRICAO());
        vetCampos.add(String.valueOf(this.objEspaco.getTIP_ID()));
        vetCampos.add(String.valueOf(this.objEspaco.getESP_ANO()));
        vetCampos.add(String.valueOf(this.objEspaco.getESP_COMPRIMENTO()));
        vetCampos.add(String.valueOf(this.objEspaco.getESP_LARGURA()));
        vetCampos.add(String.valueOf(this.objEspaco.getESP_ALTURA()));
        vetCampos.add(String.valueOf(this.objEspaco.getESP_AREA()));
        vetCampos.add(String.valueOf(this.objEspaco.getIMO_ID()));

        return vetCampos;
    }

    public ArrayList<String> RecuperaObjeto(int iCodigo) {
        this.objEspaco.RecuperaObjeto(iCodigo);
        return converterObjetoParaArray();
    }

    public ArrayList<String> RecuperaObjeto(String sCampo, String sValor, boolean bEmQualquerParte) {
        this.objEspaco.RecuperaObjeto(sCampo, sValor, bEmQualquerParte);
        return converterObjetoParaArray();
    }
    
    public ArrayList<String> RecuperaObjetoNavegacao(int iOpcao, int iCodigo) {
        this.objEspaco.RecuperaObjetoNavegacao(iOpcao, iCodigo);
        return converterObjetoParaArray();
    }

    public void Excluir(int iChave) {
        this.objEspaco.setESP_ID(iChave);
        this.objEspaco.Excluir();

    }
    
    public DefaultTableModel PesquisaObjeto(ArrayList<String> Parametros, DefaultTableModel NomeTabela) {
        // Decodificando o arraylist em variaveis
        String Campo = Parametros.get(0);
        String Valor = Parametros.get(1);
        boolean EmQualquerParte = Parametros.get(2).equals("S");
        
        // Efetuamos a pesquisa de objetos no campo de dados
        ArrayList<MDEspaco> Espacos = this.objEspaco.RecuperaObjeto(Campo, Valor, EmQualquerParte);
        
        
        // Preenchendo a tabela com os dados retornados
        Vector<String> vetVetor;
        MDEspaco objEspacoBuffer;
        for (int i=0; i<Espacos.size(); i++) {
           vetVetor = new Vector<String>();
           objEspacoBuffer = Espacos.get(i);
            
           vetVetor.addElement(String.valueOf( objEspacoBuffer.getESP_ID()) );
           vetVetor.addElement(objEspacoBuffer.getESP_DESCRICAO() );
           vetVetor.addElement(String.valueOf( objEspacoBuffer.getESP_COMPRIMENTO()) );
           vetVetor.addElement(String.valueOf( objEspacoBuffer.getESP_LARGURA()) );
           vetVetor.addElement(String.valueOf( objEspacoBuffer.getESP_ALTURA()) );
           vetVetor.addElement(String.valueOf( objEspacoBuffer.getESP_AREA()) );
           vetVetor.addElement(String.valueOf( objEspacoBuffer.getIMO_ID()) );
           NomeTabela.addRow(vetVetor);
        }
        return NomeTabela;
    }
}