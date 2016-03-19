package com.web.controller;

import com.web.model.MDCargoFuncao;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

public class CLCargoFuncao {
    private final MDCargoFuncao objCargoFuncao;
    
    public CLCargoFuncao() {
        this.objCargoFuncao = new MDCargoFuncao();
    }

    public int Salvar(ArrayList<String> pLista) {
        this.objCargoFuncao.setCAF_NOME(pLista.get(1));
        this.objCargoFuncao.setProximoCodigoInsercao();

        this.objCargoFuncao.Salvar();

        return this.objCargoFuncao.getCAF_ID();
    }

    public void Atualizar(ArrayList<String> pLista) {
        this.objCargoFuncao.setCAF_ID(Integer.parseInt(pLista.get(0)));
        this.objCargoFuncao.setCAF_NOME(pLista.get(1));

        this.objCargoFuncao.Atualizar();
    }

    private ArrayList<String> converterObjetoParaArray() {
        ArrayList<String> vetCampos = new ArrayList<String>();
        vetCampos.add(String.valueOf(this.objCargoFuncao.getCAF_ID()));
        vetCampos.add(this.objCargoFuncao.getCAF_NOME());

        return vetCampos;
    }

    public ArrayList<String> RecuperaObjeto(int iCodigo) {
        this.objCargoFuncao.RecuperaObjeto(iCodigo);
        return converterObjetoParaArray();
    }

    public ArrayList<String> RecuperaObjeto(String sCampo, String sValor, boolean bEmQualquerParte) {
        this.objCargoFuncao.RecuperaObjeto(sCampo, sValor, bEmQualquerParte);
        return converterObjetoParaArray();
    }
    
    public ArrayList<String> RecuperaObjetoNavegacao(int iOpcao, int iCodigo) {
        this.objCargoFuncao.RecuperaObjetoNavegacao(iOpcao, iCodigo);
        return converterObjetoParaArray();
    }

    public void Excluir(int iChave) {
        this.objCargoFuncao.setCAF_ID(iChave);
        this.objCargoFuncao.Excluir();

    }
    
    public DefaultTableModel PesquisaObjeto(ArrayList<String> Parametros, DefaultTableModel NomeTabela) {
        // Decodificando o arraylist em variaveis
        String Campo = Parametros.get(0);
        String Valor = Parametros.get(1);
        boolean EmQualquerParte = Parametros.get(2).equals("S");
        
        // Efetuamos a pesquisa de objetos no campo de dados
        ArrayList<MDCargoFuncao> CargosFuncoes = this.objCargoFuncao.RecuperaObjeto(Campo, Valor, EmQualquerParte);
        
        
        // Preenchendo a tabela com os dados retornados
        Vector<String> vetVetor;
        MDCargoFuncao objCargoFuncaoBuffer;
        for (int i=0; i<CargosFuncoes.size(); i++) {
           vetVetor = new Vector<String>();
           objCargoFuncaoBuffer = CargosFuncoes.get(i);
            
           vetVetor.addElement(String.valueOf( objCargoFuncaoBuffer.getCAF_ID()) );
           vetVetor.addElement(objCargoFuncaoBuffer.getCAF_NOME() );
           NomeTabela.addRow(vetVetor);
        }
        return NomeTabela;
    }
}