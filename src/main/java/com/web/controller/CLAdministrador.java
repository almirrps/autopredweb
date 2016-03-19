package com.web.controller;

import com.web.model.MDAdministrador;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

public class CLAdministrador {
    private final MDAdministrador objAdministrador;
    
    public CLAdministrador() {
        this.objAdministrador = new MDAdministrador();
    }

    public int Salvar(ArrayList<String> pLista) {
        this.objAdministrador.setADM_NOME(pLista.get(1));
        this.objAdministrador.setCAF_ID(Integer.parseInt(pLista.get(2)));
        this.objAdministrador.setSET_ID(Integer.parseInt(pLista.get(3)));
        this.objAdministrador.setADM_EMAIL(pLista.get(4));
        this.objAdministrador.setADM_USUARIO(pLista.get(5));
        this.objAdministrador.setADM_SENHA(pLista.get(6));
        this.objAdministrador.setProximoCodigoInsercao();

        this.objAdministrador.Salvar();

        return this.objAdministrador.getADM_ID();
    }

    public void Atualizar(ArrayList<String> pLista) {
        this.objAdministrador.setADM_ID(Integer.parseInt(pLista.get(0)));
        this.objAdministrador.setADM_NOME(pLista.get(1));
        this.objAdministrador.setCAF_ID(Integer.parseInt(pLista.get(2)));
        this.objAdministrador.setSET_ID(Integer.parseInt(pLista.get(3)));
        this.objAdministrador.setADM_EMAIL(pLista.get(4));
        this.objAdministrador.setADM_USUARIO(pLista.get(5));
        this.objAdministrador.setADM_SENHA(pLista.get(6));

        this.objAdministrador.Atualizar();
    }

    private ArrayList<String> converterObjetoParaArray() {
        ArrayList<String> vetCampos = new ArrayList<String>();
        vetCampos.add(String.valueOf(this.objAdministrador.getADM_ID()));
        vetCampos.add(this.objAdministrador.getADM_NOME());
        vetCampos.add(String.valueOf(this.objAdministrador.getCAF_ID()));
        vetCampos.add(String.valueOf(this.objAdministrador.getSET_ID()));
        vetCampos.add(this.objAdministrador.getADM_EMAIL());
        vetCampos.add(this.objAdministrador.getADM_USUARIO());
        vetCampos.add(this.objAdministrador.getADM_SENHA());

        return vetCampos;
    }

    public ArrayList<String> RecuperaObjeto(int iCodigo) {
        this.objAdministrador.RecuperaObjeto(iCodigo);
        return converterObjetoParaArray();
    }

    public ArrayList<String> RecuperaObjeto(String sCampo, String sValor, boolean bEmQualquerParte) {
        this.objAdministrador.RecuperaObjeto(sCampo, sValor, bEmQualquerParte);
        return converterObjetoParaArray();
    }
    
    public ArrayList<String> RecuperaObjetoNavegacao(int iOpcao, int iCodigo) {
        this.objAdministrador.RecuperaObjetoNavegacao(iOpcao, iCodigo);
        return converterObjetoParaArray();
    }

    public void Excluir(int iChave) {
        this.objAdministrador.setADM_ID(iChave);
        this.objAdministrador.Excluir();

    }
    
    public DefaultTableModel PesquisaObjeto(ArrayList<String> Parametros, DefaultTableModel NomeTabela) {
        // Decodificando o arraylist em variaveis
        String Campo = Parametros.get(0);
        String Valor = Parametros.get(1);
        boolean EmQualquerParte = Parametros.get(2).equals("S");
        
        // Efetuamos a pesquisa de objetos no campo de dados
        ArrayList<MDAdministrador> Administradores = this.objAdministrador.RecuperaObjeto(Campo, Valor, EmQualquerParte);
        
        
        // Preenchendo a tabela com os dados retornados
        Vector<String> vetVetor;
        MDAdministrador objAdministradorBuffer;
        for (int i=0; i<Administradores.size(); i++) {
           vetVetor = new Vector<String>();
           objAdministradorBuffer = Administradores.get(i);
            
           vetVetor.addElement(String.valueOf( objAdministradorBuffer.getADM_ID()) );
           vetVetor.addElement(objAdministradorBuffer.getADM_NOME() );
           vetVetor.addElement(objAdministradorBuffer.getADM_EMAIL() );
           vetVetor.addElement(objAdministradorBuffer.getADM_USUARIO() );
           vetVetor.addElement(objAdministradorBuffer.getADM_SENHA() );
           NomeTabela.addRow(vetVetor);
        }
        return NomeTabela;
    }
}