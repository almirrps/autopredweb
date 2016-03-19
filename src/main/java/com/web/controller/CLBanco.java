package com.web.controller;

import com.web.model.MDBanco;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

public class CLBanco {
    private final MDBanco objBanco;
    
    public CLBanco() {
        this.objBanco = new MDBanco();
    }

    public int Salvar(ArrayList<String> pLista) {
        this.objBanco.setBAN_NUMERO(Integer.parseInt(pLista.get(1)));
        this.objBanco.setBAN_NOME(pLista.get(2));
        this.objBanco.setBAN_AGENCIA(pLista.get(3));
        this.objBanco.setBAN_CONTA(pLista.get(4));
        this.objBanco.setProximoCodigoInsercao();

        this.objBanco.Salvar();

        return this.objBanco.getBAN_ID();
    }

    public void Atualizar(ArrayList<String> pLista) {
        this.objBanco.setBAN_ID(Integer.parseInt(pLista.get(0)));
        this.objBanco.setBAN_NUMERO(Integer.parseInt(pLista.get(1)));
        this.objBanco.setBAN_NOME(pLista.get(2));
        this.objBanco.setBAN_AGENCIA(pLista.get(3));
        this.objBanco.setBAN_CONTA(pLista.get(4));

        this.objBanco.Atualizar();
    }

    private ArrayList<String> converterObjetoParaArray() {
        ArrayList<String> vetCampos = new ArrayList<String>();
        vetCampos.add(String.valueOf(this.objBanco.getBAN_ID()));
        vetCampos.add(String.valueOf(this.objBanco.getBAN_NUMERO()));
        vetCampos.add(this.objBanco.getBAN_NOME());
        vetCampos.add(this.objBanco.getBAN_AGENCIA());
        vetCampos.add(this.objBanco.getBAN_CONTA());

        return vetCampos;
    }

    public ArrayList<String> RecuperaObjeto(int iCodigo) {
        this.objBanco.RecuperaObjeto(iCodigo);
        return converterObjetoParaArray();
    }

    public ArrayList<String> RecuperaObjeto(String sCampo, String sValor, boolean bEmQualquerParte) {
        this.objBanco.RecuperaObjeto(sCampo, sValor, bEmQualquerParte);
        return converterObjetoParaArray();
    }
    
    public ArrayList<String> RecuperaObjetoNavegacao(int iOpcao, int iCodigo) {
        this.objBanco.RecuperaObjetoNavegacao(iOpcao, iCodigo);
        return converterObjetoParaArray();
    }

    public void Excluir(int iChave) {
        this.objBanco.setBAN_ID(iChave);
        this.objBanco.Excluir();

    }
    
    public DefaultTableModel PesquisaObjeto(ArrayList<String> Parametros, DefaultTableModel NomeTabela) {
        // Decodificando o arraylist em variaveis
        String Campo = Parametros.get(0);
        String Valor = Parametros.get(1);
        boolean EmQualquerParte = Parametros.get(2).equals("S");
        
        // Efetuamos a pesquisa de objetos no campo de dados
        ArrayList<MDBanco> Bancoes = this.objBanco.RecuperaObjeto(Campo, Valor, EmQualquerParte);
        
        
        // Preenchendo a tabela com os dados retornados
        Vector<String> vetVetor;
        MDBanco objBancoBuffer;
        for (int i=0; i<Bancoes.size(); i++) {
           vetVetor = new Vector<String>();
           objBancoBuffer = Bancoes.get(i);
            
           vetVetor.addElement(String.valueOf( objBancoBuffer.getBAN_ID()) );
           vetVetor.addElement(String.valueOf(objBancoBuffer.getBAN_NUMERO()) );
           vetVetor.addElement(objBancoBuffer.getBAN_NOME() );
           vetVetor.addElement(objBancoBuffer.getBAN_AGENCIA() );
           vetVetor.addElement(objBancoBuffer.getBAN_CONTA() );
           NomeTabela.addRow(vetVetor);
        }
        return NomeTabela;
    }
}