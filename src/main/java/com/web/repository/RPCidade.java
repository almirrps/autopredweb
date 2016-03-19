package com.web.repository;

import com.web.model.MDCidade;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class RPCidade {

    public static final int cNavPrimeiro = 0;
    public static final int cNavAnterior = 1;
    public static final int cNavProximo = 2;
    public static final int cNavUltimo = 3;

    public static void salvar(MDCidade Cidade) {
        Connection cnx = RPConexao.getConexaoMySQL();
        PreparedStatement insereSt = null;
        String sql = "insert into autopred.cad_cidade values(?,?,?)";

        try {
            insereSt = cnx.prepareStatement(sql);

            insereSt.setInt(1, Cidade.getCID_ID());
            insereSt.setString(2, Cidade.getCID_NOME());
            insereSt.setInt(3, Cidade.getEST_ID());

            insereSt.executeUpdate();
            System.out.println("Linha inserida: " + Cidade.getCID_ID() + " | " + Cidade.getCID_NOME());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao incluir cidade: " + e.getMessage() + "\n");
        } finally {
            try {
                insereSt.close();
                cnx.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao encerrar conexão: " + e.getMessage() + "\n");
            }
        }
    }

    public static ArrayList<MDCidade> RecuperaObjetos(String cidade) {
        Connection cnx = RPConexao.getConexaoMySQL();
        ArrayList<MDCidade> cid = new ArrayList<>();
        try {
            Statement objStm = cnx.createStatement();
            objStm.executeQuery(cidade);
            //objStm.executeQuery("Select * from autopred.cad_cidade where cid_nome like '%" + cidade + "%'");
            ResultSet objRsSt = objStm.getResultSet();
            while (objRsSt.next()) {
                MDCidade a = new MDCidade();
                String vID      = objRsSt.getString("cid_id");
                String vNome    = objRsSt.getString("cid_nome");
                String vEst_ID  = objRsSt.getString("est_id");
                
                a.setCID_ID(Integer.parseInt(vID));
                a.setCID_NOME(vNome);
                a.setEST_ID(Integer.parseInt(vEst_ID));
                cid.add(a);
            }
            objRsSt.close();
            objStm.close();
            cnx.close();
        } catch (NumberFormatException | SQLException erro) {
            String erroMsg = "Erro ao recuperar objetos: " + erro.getMessage();
            JOptionPane.showMessageDialog(null, erroMsg, "Mensagem", JOptionPane.ERROR_MESSAGE);
        }

        return cid;
    }

    public static MDCidade RecuperaObjCodigo(Integer cidade) {
        Connection cnx = RPConexao.getConexaoMySQL();
        MDCidade cid = new MDCidade();
        try {
            Statement objStm = cnx.createStatement();
            objStm.executeQuery("Select * from autopred.cad_cidade where cid_id =" + String.valueOf(cidade));
            ResultSet objRsSt = objStm.getResultSet();
            while (objRsSt.next()) {
                cid = new MDCidade();
                String vID      = objRsSt.getString("cid_id");
                String vNome    = objRsSt.getString("cid_nome");
                String vEst_ID  = objRsSt.getString("est_id");
                cid.setCID_ID(Integer.parseInt(vID));
                cid.setCID_NOME(vNome);
                cid.setEST_ID(Integer.parseInt(vEst_ID));
            }

            objRsSt.close();
            objStm.close();
            cnx.close();
        } catch (NumberFormatException | SQLException erro) {
            String erroMsg = "Erro ao recuperar: " + erro.getMessage();
            JOptionPane.showMessageDialog(null, erroMsg, "Mensagem", JOptionPane.ERROR_MESSAGE);

        }

        return cid;
    }

    public static void atualizar(MDCidade cidade) {
        Connection cnx = RPConexao.getConexaoMySQL();
        PreparedStatement insereSt = null;
        String sqlU = "update autopred.cad_cidade set cid_id = ?, cid_nome = ?, est_id = ? where cid_id = " + cidade.getCID_ID();

        try {
            insereSt = cnx.prepareStatement(sqlU);

            insereSt.setInt(1, cidade.getCID_ID());
            insereSt.setString(2, cidade.getCID_NOME());
            insereSt.setInt(3, cidade.getEST_ID());

            insereSt.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar cidade: " + e.getMessage());
        } finally {
            try {
                insereSt.close();
                cnx.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao encerrar conexão: " + e.getMessage() + "\n");
            }
        }
    }

    public static void excluir(MDCidade cidade) {
        Connection cnx = RPConexao.getConexaoMySQL();
        PreparedStatement insereSt = null;
        String sql = "delete from autopred.cad_cidade where cid_id = ?";
        try {
            insereSt = cnx.prepareStatement(sql);
            insereSt.setInt(1, cidade.getCID_ID());
            insereSt.executeUpdate();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir cidade: " + e.getMessage() + "\n");
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
                sql = "select min(cid_id) as COD from cad_cidade";
                break;
            case cNavAnterior:
                sql = "select max(cid_id) as COD from cad_cidade where cid_id < " + String.valueOf(codigoAtual);
                break;
            case cNavProximo:
                sql = "select min(cid_id) as COD from cad_cidade where cid_id > " + String.valueOf(codigoAtual);
                break;
            case cNavUltimo:
                sql = "select max(cid_id) as COD from cad_cidade";
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
    
    public static ArrayList<MDCidade> PesquisaObjeto(String sCampo, String sValor, boolean bTodaParte) {
        String sql = "select * from cad_cidade where " + sCampo + " like '";
        if (bTodaParte)
            sql = sql + "%";
        sql = sql + sValor + "%'";
        
        
        return RecuperaObjetos(sql);
        
    }
}