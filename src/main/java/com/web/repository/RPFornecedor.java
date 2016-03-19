package com.web.repository;

import com.web.model.MDFornecedor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class RPFornecedor {

    public static final int cNavPrimeiro = 0;
    public static final int cNavAnterior = 1;
    public static final int cNavProximo = 2;
    public static final int cNavUltimo = 3;

    public static void salvar(MDFornecedor Fornecedor) {
        Connection cnx = RPConexao.getConexaoMySQL();
        PreparedStatement insereSt = null;
        String sql = "insert into autopred.cad_fornecedor values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try {
            insereSt = cnx.prepareStatement(sql);

            insereSt.setInt(1, Fornecedor.getFOR_ID());
            insereSt.setString(2, Fornecedor.getFOR_RAZAO());
            insereSt.setString(3, Fornecedor.getFOR_CNPJ());
            insereSt.setString(4, Fornecedor.getFOR_IE());
            insereSt.setString(5, Fornecedor.getFOR_IM());
            insereSt.setString(6, Fornecedor.getFOR_SEGMENTO());
            insereSt.setString(7, Fornecedor.getFOR_ENDERECO());
            insereSt.setString(8, Fornecedor.getFOR_NUMERO());
            insereSt.setString(9, Fornecedor.getFOR_BAIRRO());
            insereSt.setString(10, Fornecedor.getFOR_COMPLEMENTO());            
            insereSt.setInt(11, Fornecedor.getCID_ID());
            insereSt.setString(12, Fornecedor.getFOR_TELEFONE());
            insereSt.setString(13, Fornecedor.getFOR_RESPONSAVEL());
            insereSt.setString(14, Fornecedor.getFOR_EMAIL());

            insereSt.executeUpdate();
            System.out.println("Linha inserida: " + Fornecedor.getFOR_ID() + " | " + Fornecedor.getFOR_RAZAO());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao incluir fornecedor: " + e.getMessage() + "\n");
        } finally {
            try {
                insereSt.close();
                cnx.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao encerrar conexão: " + e.getMessage() + "\n");
            }
        }
    }

    public static ArrayList<MDFornecedor> RecuperaObjetos(String fornecedor) {
        Connection cnx = RPConexao.getConexaoMySQL();
        ArrayList<MDFornecedor> forn = new ArrayList<>();
        try {
            Statement objStm = cnx.createStatement();
            objStm.executeQuery(fornecedor);
            //objStm.executeQuery("Select * from autopred.cad_fornecedor where for_razao like '%" + fornecedor + "%'");
            ResultSet objRsSt = objStm.getResultSet();
            while (objRsSt.next()) {
                MDFornecedor  f = new MDFornecedor();
                String vID          = objRsSt.getString("for_id");
                String vRazao       = objRsSt.getString("for_razao");
                String vCNPJ        = objRsSt.getString("for_cnpj");
                String vIE          = objRsSt.getString("for_ie");
                String vIM          = objRsSt.getString("for_IM");
                String vSegmento    = objRsSt.getString("for_segmento");
                String vEndereco    = objRsSt.getString("for_endereco");
                String vNumero      = objRsSt.getString("for_numero");
                String vBairro      = objRsSt.getString("for_bairro");
                String vComplemento = objRsSt.getString("for_complemento");                                
                String vCid_ID      = objRsSt.getString("cid_id");
                String vTelefone    = objRsSt.getString("for_telefone");
                String vResponsavel = objRsSt.getString("for_responsavel");
                String vEmail       = objRsSt.getString("for_email");
                
                f.setFOR_ID(Integer.parseInt(vID));
                f.setFOR_RAZAO(vRazao);
                f.setFOR_CNPJ(vCNPJ);
                f.setFOR_IE(vIE);
                f.setFOR_IM(vIM);
                f.setFOR_SEGMENTO(vSegmento);
                f.setFOR_ENDERECO(vEndereco);
                f.setFOR_NUMERO(vNumero);
                f.setFOR_BAIRRO(vBairro);
                f.setFOR_COMPLEMENTO(vComplemento);
                f.setCID_ID(Integer.parseInt(vCid_ID));
                f.setFOR_TELEFONE(vTelefone);
                f.setFOR_RESPONSAVEL(vResponsavel);
                f.setFOR_EMAIL(vEmail);
                
                forn.add(f);
            }
            objRsSt.close();
            objStm.close();
            cnx.close();
        } catch (NumberFormatException | SQLException erro) {
            String erroMsg = "Erro ao recuperar objetos: " + erro.getMessage();
            JOptionPane.showMessageDialog(null, erroMsg, "Mensagem", JOptionPane.ERROR_MESSAGE);
        }

        return forn;
    }

    public static MDFornecedor RecuperaObjCodigo(Integer fornecedor) {
        Connection cnx = RPConexao.getConexaoMySQL();
        MDFornecedor forn = new MDFornecedor();
        try {
            Statement objStm = cnx.createStatement();
            objStm.executeQuery("Select * from autopred.cad_fornecedor where for_id =" + String.valueOf(fornecedor));
            ResultSet objRsSt = objStm.getResultSet();
            while (objRsSt.next()) {
                forn = new MDFornecedor();
                String vID          = objRsSt.getString("for_id");
                String vRazao       = objRsSt.getString("for_razao");
                String vCNPJ        = objRsSt.getString("for_cnpj");
                String vIE          = objRsSt.getString("for_ie");
                String vIM          = objRsSt.getString("for_im");
                String vSegmento    = objRsSt.getString("for_segmento");
                String vEndereco    = objRsSt.getString("for_endereco");
                String vNumero      = objRsSt.getString("for_numero");
                String vBairro      = objRsSt.getString("for_bairro");
                String vComplemento = objRsSt.getString("for_complemento");
                String vCid_ID      = objRsSt.getString("CID_ID");
                String vTelefone    = objRsSt.getString("for_telefone");
                String vResponsavel = objRsSt.getString("for_responsavel");
                String vEmail       = objRsSt.getString("for_email");
                
                forn.setFOR_ID(Integer.parseInt(vID));
                forn.setFOR_RAZAO(vRazao);
                forn.setFOR_CNPJ(vCNPJ);
                forn.setFOR_IE(vIE);
                forn.setFOR_IM(vIM);
                forn.setFOR_SEGMENTO(vSegmento);
                forn.setFOR_ENDERECO(vEndereco);
                forn.setFOR_NUMERO(vNumero);
                forn.setFOR_BAIRRO(vBairro);
                forn.setFOR_COMPLEMENTO(vComplemento);
                forn.setCID_ID(Integer.parseInt(vCid_ID));
                forn.setFOR_TELEFONE(vTelefone);
                forn.setFOR_RESPONSAVEL(vResponsavel);
                forn.setFOR_EMAIL(vEmail);
            }

            objRsSt.close();
            objStm.close();
            cnx.close();
        } catch (NumberFormatException | SQLException erro) {
            String erroMsg = "Erro ao recuperar: " + erro.getMessage();
            JOptionPane.showMessageDialog(null, erroMsg, "Mensagem", JOptionPane.ERROR_MESSAGE);

        }

        return forn;
    }

    public static void atualizar(MDFornecedor fornecedor) {
        Connection cnx = RPConexao.getConexaoMySQL();
        PreparedStatement insereSt = null;
        String sqlU = "update autopred.cad_fornecedor set for_id = ?, for_razao = ?, for_cnpj = ?, "
                    + "for_ie = ?, for_im = ?, for_segmento = ?, for_endereco = ?, for_numero = ?, "
                    + "for_bairro = ?, for_complemento = ?, cid_id = ?, for_telefone = ?, "
                    + "for_responsavel = ?, for_email = ? where for_id = " + fornecedor.getFOR_ID();

        try {
            insereSt = cnx.prepareStatement(sqlU);

            insereSt.setInt(1, fornecedor.getFOR_ID());
            insereSt.setString(2, fornecedor.getFOR_RAZAO());
            insereSt.setString(3, fornecedor.getFOR_CNPJ());
            insereSt.setString(4, fornecedor.getFOR_IE());
            insereSt.setString(5, fornecedor.getFOR_IM());
            insereSt.setString(6, fornecedor.getFOR_SEGMENTO());
            insereSt.setString(7, fornecedor.getFOR_ENDERECO());
            insereSt.setString(8, fornecedor.getFOR_NUMERO());
            insereSt.setString(9, fornecedor.getFOR_BAIRRO());
            insereSt.setString(10, fornecedor.getFOR_COMPLEMENTO());
            insereSt.setInt(11, fornecedor.getCID_ID());
            insereSt.setString(12, fornecedor.getFOR_TELEFONE());
            insereSt.setString(13, fornecedor.getFOR_RESPONSAVEL());
            insereSt.setString(14, fornecedor.getFOR_EMAIL());

            insereSt.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar fornecedor: " + e.getMessage());
        } finally {
            try {
                insereSt.close();
                cnx.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao encerrar conexão: " + e.getMessage() + "\n");
            }
        }
    }

    public static void excluir(MDFornecedor fornecedor) {
        Connection cnx = RPConexao.getConexaoMySQL();
        PreparedStatement insereSt = null;
        String sql = "delete from autopred.cad_fornecedor where for_id = ?";
        try {
            insereSt = cnx.prepareStatement(sql);
            insereSt.setInt(1, fornecedor.getFOR_ID());
            insereSt.executeUpdate();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir fornecedor: " + e.getMessage() + "\n");
        } finally {
            try {
                insereSt.close();
                cnx.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao encerrar conexão: " + e.getMessage() + "\n");
            }
        }
    }

    private static String SQLNavegacao(int iOpcao, int codigoAtual) {
        String sql = "";
        switch (iOpcao) {
            case cNavPrimeiro:
                sql = "select min(for_id) as COD from cad_fornecedor";
                break;
            case cNavAnterior:
                sql = "select max(for_id) as COD from cad_fornecedor where for_id < " + String.valueOf(codigoAtual);
                break;
            case cNavProximo:
                sql = "select min(for_id) as COD from cad_fornecedor where for_id > " + String.valueOf(codigoAtual);
                break;
            case cNavUltimo:
                sql = "select max(for_id) as COD from cad_fornecedor";
                break;
        }
        return sql;
    }

    public static int PegaCodigoPelaNavegacao(int iOpcao, int codigoAtual) {
        Connection cnx = RPConexao.getConexaoMySQL();
        Statement consulta = null;
        ResultSet resultado;
        int codigoEncontrado = -1;

        try {
            consulta = (Statement) cnx.createStatement();
            resultado = consulta.executeQuery(SQLNavegacao(iOpcao, codigoAtual));
            resultado.next();
            codigoEncontrado = resultado.getInt("COD");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao executar sql de navegacao: " + e.getMessage() + "\n");
        } finally {
            try {
                consulta.close();
                cnx.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao encerrar conexão no sql de navegacao: " + e.getMessage() + "\n");
            }
        }
        return codigoEncontrado;

    }
    
    public static ArrayList<MDFornecedor> PesquisaObjeto(String sCampo, String sValor, boolean bTodaParte) {
        String sql = "select * from cad_fornecedor where " + sCampo + " like '";
        if (bTodaParte)
            sql = sql + "%";
        sql = sql + sValor + "%'";
        
        
        return RecuperaObjetos(sql);
        
    }
}