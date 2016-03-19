package com.web.controller;

import com.web.model.MDSetor;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

public class CLSetor {
    private final MDSetor objSetor;
    
    public CLSetor() {
        this.objSetor = new MDSetor();
    }

    public int Salvar(ArrayList<String> pLista) {
        this.objSetor.setSET_NOME(pLista.get(1));
        this.objSetor.setProximoCodigoInsercao();

        this.objSetor.Salvar();

        return this.objSetor.getSET_ID();
    }

    public void Atualizar(ArrayList<String> pLista) {
        this.objSetor.setSET_ID(Integer.parseInt(pLista.get(0)));
        this.objSetor.setSET_NOME(pLista.get(1));

        this.objSetor.Atualizar();
    }

    private ArrayList<String> converterObjetoParaArray() {
        ArrayList<String> vetCampos = new ArrayList<String>();
        vetCampos.add(String.valueOf(this.objSetor.getSET_ID()));
        vetCampos.add(this.objSetor.getSET_NOME());

        return vetCampos;
    }

    public ArrayList<String> RecuperaObjeto(int iCodigo) {
        this.objSetor.RecuperaObjeto(iCodigo);
        return converterObjetoParaArray();
    }

    public ArrayList<String> RecuperaObjeto(String sCampo, String sValor, boolean bEmQualquerParte) {
        this.objSetor.RecuperaObjeto(sCampo, sValor, bEmQualquerParte);
        return converterObjetoParaArray();
    }
    
    public ArrayList<String> RecuperaObjetoNavegacao(int iOpcao, int iCodigo) {
        this.objSetor.RecuperaObjetoNavegacao(iOpcao, iCodigo);
        return converterObjetoParaArray();
    }

    public void Excluir(int iChave) {
        this.objSetor.setSET_ID(iChave);
        this.objSetor.Excluir();

    }
    
    public DefaultTableModel PesquisaObjeto(ArrayList<String> Parametros, DefaultTableModel NomeTabela) {
        // Decodificando o arraylist em variaveis
        String Campo = Parametros.get(0);
        String Valor = Parametros.get(1);
        boolean EmQualquerParte = Parametros.get(2).equals("S");
        
        // Efetuamos a pesquisa de objetos no campo de dados
        ArrayList<MDSetor> CargosFuncoes = this.objSetor.RecuperaObjeto(Campo, Valor, EmQualquerParte);
        
        
        // Preenchendo a tabela com os dados retornados
        Vector<String> vetVetor;
        MDSetor objSetorBuffer;
        for (int i=0; i<CargosFuncoes.size(); i++) {
           vetVetor = new Vector<String>();
           objSetorBuffer = CargosFuncoes.get(i);
            
           vetVetor.addElement(String.valueOf( objSetorBuffer.getSET_ID()) );
           vetVetor.addElement(objSetorBuffer.getSET_NOME() );
           NomeTabela.addRow(vetVetor);
        }
        return NomeTabela;
    }
}