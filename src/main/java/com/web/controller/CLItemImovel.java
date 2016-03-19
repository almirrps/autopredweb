package com.web.controller;

import com.web.model.MDItemImovel;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

public class CLItemImovel {
    private final MDItemImovel objItemImovel;
    
    public CLItemImovel() {
        this.objItemImovel = new MDItemImovel();
    }

    public int Salvar(ArrayList<String> pLista) {
        this.objItemImovel.setITE_DESCRICAO(pLista.get(1));
        this.objItemImovel.setITE_UNIDADE(Integer.parseInt(pLista.get(2)));
        this.objItemImovel.setITE_QUANTIDADE(Integer.parseInt(pLista.get(3)));
        this.objItemImovel.setITE_VIDA_UTIL(Integer.parseInt(pLista.get(4)));
        this.objItemImovel.setITE_CUSTO_MEDIO(Integer.parseInt(pLista.get(5)));
        this.objItemImovel.setIMO_ID(Integer.parseInt(pLista.get(6)));
        this.objItemImovel.setProximoCodigoInsercao();

        this.objItemImovel.Salvar();

        return this.objItemImovel.getITE_ID();
    }

    public void Atualizar(ArrayList<String> pLista) {
        this.objItemImovel.setITE_ID(Integer.parseInt(pLista.get(0)));
        this.objItemImovel.setITE_DESCRICAO(pLista.get(1));
        this.objItemImovel.setITE_UNIDADE(Integer.parseInt(pLista.get(2)));
        this.objItemImovel.setITE_QUANTIDADE(Integer.parseInt(pLista.get(3)));
        this.objItemImovel.setITE_VIDA_UTIL(Integer.parseInt(pLista.get(4)));
        this.objItemImovel.setITE_CUSTO_MEDIO(Integer.parseInt(pLista.get(5)));
        this.objItemImovel.setIMO_ID(Integer.parseInt(pLista.get(6)));

        this.objItemImovel.Atualizar();
    }

    private ArrayList<String> converterObjetoParaArray() {
        ArrayList<String> vetCampos = new ArrayList<String>();
        vetCampos.add(String.valueOf(this.objItemImovel.getITE_ID()));
        vetCampos.add(this.objItemImovel.getITE_DESCRICAO());
        vetCampos.add(String.valueOf(this.objItemImovel.getITE_UNIDADE()));
        vetCampos.add(String.valueOf(this.objItemImovel.getITE_QUANTIDADE()));
        vetCampos.add(String.valueOf(this.objItemImovel.getITE_VIDA_UTIL()));
        vetCampos.add(String.valueOf(this.objItemImovel.getITE_CUSTO_MEDIO()));
        vetCampos.add(String.valueOf(this.objItemImovel.getIMO_ID()));

        return vetCampos;
    }

    public ArrayList<String> RecuperaObjeto(int iCodigo) {
        this.objItemImovel.RecuperaObjeto(iCodigo);
        return converterObjetoParaArray();
    }

    public ArrayList<String> RecuperaObjeto(String sCampo, String sValor, boolean bEmQualquerParte) {
        this.objItemImovel.RecuperaObjeto(sCampo, sValor, bEmQualquerParte);
        return converterObjetoParaArray();
    }
    
    public ArrayList<String> RecuperaObjetoNavegacao(int iOpcao, int iCodigo) {
        this.objItemImovel.RecuperaObjetoNavegacao(iOpcao, iCodigo);
        return converterObjetoParaArray();
    }

    public void Excluir(int iChave) {
        this.objItemImovel.setITE_ID(iChave);
        this.objItemImovel.Excluir();

    }
    
    public DefaultTableModel PesquisaObjeto(ArrayList<String> Parametros, DefaultTableModel NomeTabela) {
        // Decodificando o arraylist em variaveis
        String Campo = Parametros.get(0);
        String Valor = Parametros.get(1);
        boolean EmQualquerParte = Parametros.get(2).equals("S");
        
        // Efetuamos a pesquisa de objetos no campo de dados
        ArrayList<MDItemImovel> ItemImovels = this.objItemImovel.RecuperaObjeto(Campo, Valor, EmQualquerParte);
        
        
        // Preenchendo a tabela com os dados retornados
        Vector<String> vetVetor;
        MDItemImovel objItemImovelBuffer;
        for (int i=0; i<ItemImovels.size(); i++) {
           vetVetor = new Vector<String>();
           objItemImovelBuffer = ItemImovels.get(i);
            
           vetVetor.addElement(String.valueOf( objItemImovelBuffer.getITE_ID()) );
           vetVetor.addElement(objItemImovelBuffer.getITE_DESCRICAO() );
           vetVetor.addElement(String.valueOf( objItemImovelBuffer.getITE_UNIDADE()) );
           vetVetor.addElement(String.valueOf( objItemImovelBuffer.getITE_QUANTIDADE()) );
           vetVetor.addElement(String.valueOf( objItemImovelBuffer.getITE_VIDA_UTIL()) );
           vetVetor.addElement(String.valueOf( objItemImovelBuffer.getITE_CUSTO_MEDIO()) );
           vetVetor.addElement(String.valueOf( objItemImovelBuffer.getIMO_ID()) );
           NomeTabela.addRow(vetVetor);
        }
        return NomeTabela;
    }
}