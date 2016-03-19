package com.web.repository;

import com.web.model.MDTipoEspaco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class RPTipoEspaco {

    public static final int cNavPrimeiro = 0;
    public static final int cNavAnterior = 1;
    public static final int cNavProximo = 2;
    public static final int cNavUltimo = 3;

    public static void salvar(MDTipoEspaco TipoEspaco) {
        Connection cnx = RPConexao.getConexaoMySQL();
        PreparedStatement insereSt = null;
        String sql = "insert into autopred.cad_tipo_espaco values(?,?)";

        try {
            insereSt = cnx.prepareStatement(sql);

            insereSt.setInt(1, TipoEspaco.getTIP_ID());
            insereSt.setString(2, TipoEspaco.getTIP_NOME());

            insereSt.executeUpdate();
            System.out.println("Linha inserida: " + TipoEspaco.getTIP_ID() + " | " + TipoEspaco.getTIP_NOME());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao incluir tipo espaço: " + e.getMessage() + "\n");
        } finally {
            try {
                insereSt.close();
                cnx.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao encerrar conexão: " + e.getMessage() + "\n");
            }
        }
    }

    public static ArrayList<MDTipoEspaco> RecuperaObjetos(String tipoespaco) {
        Connection cnx = RPConexao.getConexaoMySQL();
        ArrayList<MDTipoEspaco> tip = new ArrayList<>();
        try {
            Statement objStm = cnx.createStatement();
            objStm.executeQuery(tipoespaco);
            //objStm.executeQuery("Select * from autopred.cad_tipo_espaco where tip_nome like '%" + tipoespaco + "%'");
            ResultSet objRsSt = objStm.getResultSet();
            while (objRsSt.next()) {
                MDTipoEspaco t = new MDTipoEspaco();
                String vID      = objRsSt.getString("tip_id");
                String vNome    = objRsSt.getString("tip_nome");
                
                t.setTIP_ID(Integer.parseInt(vID));
                t.setTIP_NOME(vNome);
                tip.add(t);
            }
            objRsSt.close();
            objStm.close();
            cnx.close();
        } catch (NumberFormatException | SQLException erro) {
            String erroMsg = "Erro ao recuperar objetos: " + erro.getMessage();
            JOptionPane.showMessageDialog(null, erroMsg, "Mensagem", JOptionPane.ERROR_MESSAGE);
        }

        return tip;
    }

    public static MDTipoEspaco RecuperaObjCodigo(Integer tipoespaco) {
        Connection cnx = RPConexao.getConexaoMySQL();
        MDTipoEspaco tip = new MDTipoEspaco();
        try {
            Statement objStm = cnx.createStatement();
            objStm.executeQuery("Select * from autopred.cad_tipo_espaco where tip_id =" + String.valueOf(tipoespaco));
            ResultSet objRsSt = objStm.getResultSet();
            while (objRsSt.next()) {
                tip = new MDTipoEspaco();
                String vID      = objRsSt.getString("tip_id");
                String vNome    = objRsSt.getString("tip_nome");
                tip.setTIP_ID(Integer.parseInt(vID));
                tip.setTIP_NOME(vNome);
            }

            objRsSt.close();
            objStm.close();
            cnx.close();
        } catch (NumberFormatException | SQLException erro) {
            String erroMsg = "Erro ao recuperar: " + erro.getMessage();
            JOptionPane.showMessageDialog(null, erroMsg, "Mensagem", JOptionPane.ERROR_MESSAGE);

        }

        return tip;
    }

    public static void atualizar(MDTipoEspaco tipoespaco) {
        Connection cnx = RPConexao.getConexaoMySQL();
        PreparedStatement insereSt = null;
        String sqlU = "update autopred.cad_tipo_espaco set tip_id = ?, tip_nome = ? where tip_id = " + tipoespaco.getTIP_ID();

        try {
            insereSt = cnx.prepareStatement(sqlU);

            insereSt.setInt(1, tipoespaco.getTIP_ID());
            insereSt.setString(2, tipoespaco.getTIP_NOME());

            insereSt.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar tipo espaço: " + e.getMessage());
        } finally {
            try {
                insereSt.close();
                cnx.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao encerrar conexão: " + e.getMessage() + "\n");
            }
        }
    }

    public static void excluir(MDTipoEspaco tipoespaco) {
        Connection cnx = RPConexao.getConexaoMySQL();
        PreparedStatement insereSt = null;
        String sql = "delete from autopred.cad_tipo_espaco where tip_id = ?";
        try {
            insereSt = cnx.prepareStatement(sql);
            insereSt.setInt(1, tipoespaco.getTIP_ID());
            insereSt.executeUpdate();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir tipo espaço: " + e.getMessage() + "\n");
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
                sql = "select min(tip_id) as COD from cad_tipo_espaco";
                break;
            case cNavAnterior:
                sql = "select max(tip_id) as COD from cad_tipo_espaco where tip_id < " + String.valueOf(codigoAtual);
                break;
            case cNavProximo:
                sql = "select min(tip_id) as COD from cad_tipo_espaco where tip_id > " + String.valueOf(codigoAtual);
                break;
            case cNavUltimo:
                sql = "select max(tip_id) as COD from cad_tipo_espaco";
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
    
    public static ArrayList<MDTipoEspaco> PesquisaObjeto(String sCampo, String sValor, boolean bTodaParte) {
        String sql = "select * from cad_tipo_espaco where " + sCampo + " like '";
        if (bTodaParte)
            sql = sql + "%";
        sql = sql + sValor + "%'";
        
        
        return RecuperaObjetos(sql);
    }
}