package com.web.controller;

import com.web.model.MDFornecedor;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

public class CLFornecedor {
    private final MDFornecedor objFornecedor;
    
    public CLFornecedor() {
        this.objFornecedor = new MDFornecedor();
    }

    public int Salvar(ArrayList<String> pLista) {
        this.objFornecedor.setFOR_RAZAO(pLista.get(1));
        this.objFornecedor.setFOR_CNPJ(pLista.get(2));
        this.objFornecedor.setFOR_IE(pLista.get(3));
        this.objFornecedor.setFOR_IM(pLista.get(4));
        this.objFornecedor.setFOR_SEGMENTO(pLista.get(5));
        this.objFornecedor.setFOR_ENDERECO(pLista.get(6));
        this.objFornecedor.setFOR_NUMERO(pLista.get(7));
        this.objFornecedor.setFOR_BAIRRO(pLista.get(8));
        this.objFornecedor.setFOR_COMPLEMENTO(pLista.get(9));
        this.objFornecedor.setCID_ID(Integer.parseInt(pLista.get(10)));
        this.objFornecedor.setFOR_TELEFONE(pLista.get(11));
        this.objFornecedor.setFOR_RESPONSAVEL(pLista.get(12));
        this.objFornecedor.setFOR_EMAIL(pLista.get(13));
        
        this.objFornecedor.setProximoCodigoInsercao();

        this.objFornecedor.Salvar();

        return this.objFornecedor.getFOR_ID();
    }

    public void Atualizar(ArrayList<String> pLista) {
        this.objFornecedor.setFOR_ID(Integer.parseInt(pLista.get(0)));
        this.objFornecedor.setFOR_RAZAO(pLista.get(1));
        this.objFornecedor.setFOR_CNPJ(pLista.get(2));
        this.objFornecedor.setFOR_IE(pLista.get(3));
        this.objFornecedor.setFOR_IM(pLista.get(4));
        this.objFornecedor.setFOR_SEGMENTO(pLista.get(5));
        this.objFornecedor.setFOR_ENDERECO(pLista.get(6));
        this.objFornecedor.setFOR_NUMERO(pLista.get(7));
        this.objFornecedor.setFOR_BAIRRO(pLista.get(8));
        this.objFornecedor.setFOR_COMPLEMENTO(pLista.get(9));
        this.objFornecedor.setCID_ID(Integer.parseInt(pLista.get(10)));
        this.objFornecedor.setFOR_TELEFONE(pLista.get(11));
        this.objFornecedor.setFOR_RESPONSAVEL(pLista.get(12));
        this.objFornecedor.setFOR_EMAIL(pLista.get(13));

        this.objFornecedor.Atualizar();
    }

    private ArrayList<String> converterObjetoParaArray() {
        ArrayList<String> vetCampos = new ArrayList<String>();
        vetCampos.add(String.valueOf(this.objFornecedor.getFOR_ID()));
        vetCampos.add(this.objFornecedor.getFOR_RAZAO());
        vetCampos.add(this.objFornecedor.getFOR_CNPJ());
        vetCampos.add(this.objFornecedor.getFOR_IE());
        vetCampos.add(this.objFornecedor.getFOR_IM());
        vetCampos.add(this.objFornecedor.getFOR_SEGMENTO());
        vetCampos.add(this.objFornecedor.getFOR_ENDERECO());
        vetCampos.add(this.objFornecedor.getFOR_NUMERO());
        vetCampos.add(this.objFornecedor.getFOR_BAIRRO());
        vetCampos.add(this.objFornecedor.getFOR_COMPLEMENTO());        
        vetCampos.add(String.valueOf(this.objFornecedor.getCID_ID()));
        vetCampos.add(this.objFornecedor.getFOR_TELEFONE());
        vetCampos.add(this.objFornecedor.getFOR_RESPONSAVEL());
        vetCampos.add(this.objFornecedor.getFOR_EMAIL());

        return vetCampos;
    }

    public ArrayList<String> RecuperaObjeto(int iCodigo) {
        this.objFornecedor.RecuperaObjeto(iCodigo);
        return converterObjetoParaArray();
    }

    public ArrayList<String> RecuperaObjeto(String sCampo, String sValor, boolean bEmQualquerParte) {
        this.objFornecedor.RecuperaObjeto(sCampo, sValor, bEmQualquerParte);
        return converterObjetoParaArray();
    }
    
    public ArrayList<String> RecuperaObjetoNavegacao(int iOpcao, int iCodigo) {
        this.objFornecedor.RecuperaObjetoNavegacao(iOpcao, iCodigo);
        return converterObjetoParaArray();
    }

    public void Excluir(int iChave) {
        this.objFornecedor.setFOR_ID(iChave);
        this.objFornecedor.Excluir();

    }
    
    public DefaultTableModel PesquisaObjeto(ArrayList<String> Parametros, DefaultTableModel NomeTabela) {
        // Decodificando o arraylist em variaveis
        String Campo = Parametros.get(0);
        String Valor = Parametros.get(1);
        boolean EmQualquerParte = Parametros.get(2).equals("S");
        
        // Efetuamos a pesquisa de objetos no campo de dados
        ArrayList<MDFornecedor> Fornecedores = this.objFornecedor.RecuperaObjeto(Campo, Valor, EmQualquerParte);
        
        
        // Preenchendo a tabela com os dados retornados
        Vector<String> vetVetor;
        MDFornecedor objFornecedorBuffer;
        for (int i=0; i<Fornecedores.size(); i++) {
           vetVetor = new Vector<String>();
           objFornecedorBuffer = Fornecedores.get(i);
            
           vetVetor.addElement(String.valueOf( objFornecedorBuffer.getFOR_ID()) );
           vetVetor.addElement(objFornecedorBuffer.getFOR_RAZAO() );
           vetVetor.addElement(objFornecedorBuffer.getFOR_CNPJ() );
           vetVetor.addElement(objFornecedorBuffer.getFOR_IE() );
           vetVetor.addElement(objFornecedorBuffer.getFOR_IM() );
           vetVetor.addElement(objFornecedorBuffer.getFOR_SEGMENTO() );
           vetVetor.addElement(objFornecedorBuffer.getFOR_ENDERECO() );
           vetVetor.addElement(objFornecedorBuffer.getFOR_NUMERO() );
           vetVetor.addElement(objFornecedorBuffer.getFOR_BAIRRO() );
           vetVetor.addElement(objFornecedorBuffer.getFOR_COMPLEMENTO() );
           vetVetor.addElement(String.valueOf( objFornecedorBuffer.getCID_ID()) );
           vetVetor.addElement(objFornecedorBuffer.getFOR_TELEFONE() );
           vetVetor.addElement(objFornecedorBuffer.getFOR_RESPONSAVEL() );
           vetVetor.addElement(objFornecedorBuffer.getFOR_EMAIL() );
           
           NomeTabela.addRow(vetVetor);
        }
        return NomeTabela;
    }
}