package com.web.repository;

import com.web.model.MDEstado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class RPEstado {

    public static final int cNavPrimeiro = 0;
    public static final int cNavAnterior = 1;
    public static final int cNavProximo = 2;
    public static final int cNavUltimo = 3;

    public static void salvar(MDEstado Estado) {
        Connection cnx = RPConexao.getConexaoMySQL();
        PreparedStatement insereSt = null;
        String sql = "insert into autopred.cad_estado values(?,?,?)";

        try {
            insereSt = cnx.prepareStatement(sql);

            insereSt.setInt(1, Estado.getEST_ID());
            insereSt.setString(2, Estado.getEST_NOME());
            insereSt.setString(3, Estado.getEST_SIGLA());

            insereSt.executeUpdate();
            System.out.println("Linha inserida: " + Estado.getEST_ID() + " | " + Estado.getEST_NOME());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao incluir estado: " + e.getMessage() + "\n");
        } finally {
            try {
                insereSt.close();
                cnx.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao encerrar conexão: " + e.getMessage() + "\n");
            }
        }
    }

    public static ArrayList<MDEstado> RecuperaObjetos(String estado) {
        Connection cnx = RPConexao.getConexaoMySQL();
        ArrayList<MDEstado> est = new ArrayList<>();
        try {
            Statement objStm = cnx.createStatement();
            objStm.executeQuery(estado);
            //objStm.executeQuery("Select * from autopred.cad_estado where est_nome like '%" + estado + "%'");
            ResultSet objRsSt = objStm.getResultSet();
            while (objRsSt.next()) {
                MDEstado a    = new MDEstado();
                String vID    = objRsSt.getString("est_id");
                String vNome  = objRsSt.getString("est_nome");
                String vSigla = objRsSt.getString("est_sigla");
                
                a.setEST_ID(Integer.parseInt(vID));
                a.setEST_NOME(vNome);
                a.setEST_SIGLA(vSigla);
                est.add(a);
            }
            objRsSt.close();
            objStm.close();
            cnx.close();
        } catch (NumberFormatException | SQLException erro) {
            String erroMsg = "Erro ao recuperar objetos: " + erro.getMessage();
            JOptionPane.showMessageDialog(null, erroMsg, "Mensagem", JOptionPane.ERROR_MESSAGE);
        }

        return est;
    }

    public static MDEstado RecuperaObjCodigo(Integer estado) {
        Connection cnx = RPConexao.getConexaoMySQL();
        MDEstado est = new MDEstado();
        try {
            Statement objStm = cnx.createStatement();
            objStm.executeQuery("Select * from autopred.cad_estado where est_id =" + String.valueOf(estado));
            ResultSet objRsSt = objStm.getResultSet();
            while (objRsSt.next()) {
                est           = new MDEstado();
                String vID    = objRsSt.getString("est_id");
                String vNome  = objRsSt.getString("est_nome");
                String vSigla = objRsSt.getString("est_sigla");
                est.setEST_ID(Integer.parseInt(vID));
                est.setEST_NOME(vNome);
                est.setEST_SIGLA(vSigla);
            }

            objRsSt.close();
            objStm.close();
            cnx.close();
        } catch (NumberFormatException | SQLException erro) {
            String erroMsg = "Erro ao recuperar: " + erro.getMessage();
            JOptionPane.showMessageDialog(null, erroMsg, "Mensagem", JOptionPane.ERROR_MESSAGE);
        }

        return est;
    }

    public static void atualizar(MDEstado estado) {
        Connection cnx = RPConexao.getConexaoMySQL();
        PreparedStatement insereSt = null;
        String sqlU = "update autopred.cad_estado set est_id = ?, est_nome = ?, est_sigla = ? where est_id = " + estado.getEST_ID();

        try {
            insereSt = cnx.prepareStatement(sqlU);

            insereSt.setInt(1, estado.getEST_ID());
            insereSt.setString(2, estado.getEST_NOME());
            insereSt.setString(3, estado.getEST_SIGLA());

            insereSt.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar estado: " + e.getMessage());
        } finally {
            try {
                insereSt.close();
                cnx.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao encerrar conexão: " + e.getMessage() + "\n");
            }
        }
    }

    public static void excluir(MDEstado estado) {
        Connection cnx = RPConexao.getConexaoMySQL();
        PreparedStatement insereSt = null;
        String sql = "delete from autopred.cad_estado where est_id = ?";
        try {
            insereSt = cnx.prepareStatement(sql);
            insereSt.setInt(1, estado.getEST_ID());
            insereSt.executeUpdate();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir estado: " + e.getMessage() + "\n");
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
                sql = "select min(est_id) as COD from cad_estado";
                break;
            case cNavAnterior:
                sql = "select max(est_id) as COD from cad_estado where est_id < " + String.valueOf(codigoAtual);
                break;
            case cNavProximo:
                sql = "select min(est_id) as COD from cad_estado where est_id > " + String.valueOf(codigoAtual);
                break;
            case cNavUltimo:
                sql = "select max(est_id) as COD from cad_estado";
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
    
    public static ArrayList<MDEstado> PesquisaObjeto(String sCampo, String sValor, boolean bTodaParte) {
        String sql = "select * from cad_estado where " + sCampo + " like '";
        if (bTodaParte)
            sql = sql + "%";
        sql = sql + sValor + "%'";
        
        
        return RecuperaObjetos(sql);
        
    }
}