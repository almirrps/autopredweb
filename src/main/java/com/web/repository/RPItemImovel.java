package com.web.repository;

import com.web.model.MDItemImovel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class RPItemImovel {

    public static final int cNavPrimeiro = 0;
    public static final int cNavAnterior = 1;
    public static final int cNavProximo = 2;
    public static final int cNavUltimo = 3;

    public static void salvar(MDItemImovel ItemImovel) {
        Connection cnx = RPConexao.getConexaoMySQL();
        PreparedStatement insereSt = null;
        String sql = "insert into autopred.cad_item values(?,?,?,?,?,?,?)";

        try {
            insereSt = cnx.prepareStatement(sql);

            insereSt.setInt(1, ItemImovel.getITE_ID());
            insereSt.setString(2, ItemImovel.getITE_DESCRICAO());
            insereSt.setInt(3, ItemImovel.getITE_UNIDADE());
            insereSt.setInt(4, ItemImovel.getITE_QUANTIDADE());
            insereSt.setInt(5, ItemImovel.getITE_VIDA_UTIL());
            insereSt.setInt(6, ItemImovel.getITE_CUSTO_MEDIO());
            insereSt.setInt(7, ItemImovel.getIMO_ID());

            insereSt.executeUpdate();
            System.out.println("Linha inserida: " + ItemImovel.getITE_ID() + " | " + ItemImovel.getITE_DESCRICAO());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao incluir item: " + e.getMessage() + "\n");
        } finally {
            try {
                insereSt.close();
                cnx.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao encerrar conexão: " + e.getMessage() + "\n");
            }
        }
    }

    public static ArrayList<MDItemImovel> RecuperaObjetos(String item) {
        Connection cnx = RPConexao.getConexaoMySQL();
        ArrayList<MDItemImovel> ite = new ArrayList<>();
        try {
            Statement objStm = cnx.createStatement();
            objStm.executeQuery(item);
            //objStm.executeQuery("Select * from autopred.cad_item where ite_descricao like '%" + item + "%'");
            ResultSet objRsSt = objStm.getResultSet();
            while (objRsSt.next()) {
                MDItemImovel a   = new MDItemImovel();
                String vID       = objRsSt.getString("ite_id");
                String vDescr    = objRsSt.getString("ite_descricao");
                String vUnidade  = objRsSt.getString("ite_unidade");
                String vQuant    = objRsSt.getString("ite_quantidade");
                String vVidaUtil = objRsSt.getString("ite_vida_util");
                String vCusMedio = objRsSt.getString("ite_custo_medio");
                String vImo_ID   = objRsSt.getString("imo_id");
                
                a.setITE_ID(Integer.parseInt(vID));
                a.setITE_DESCRICAO(vDescr);
                a.setITE_UNIDADE(Integer.parseInt(vUnidade));
                a.setITE_QUANTIDADE(Integer.parseInt(vQuant));
                a.setITE_VIDA_UTIL(Integer.parseInt(vVidaUtil));
                a.setITE_VIDA_UTIL(Integer.parseInt(vCusMedio));
                a.setITE_VIDA_UTIL(Integer.parseInt(vImo_ID));
                
                ite.add(a);
            }
            objRsSt.close();
            objStm.close();
            cnx.close();
        } catch (NumberFormatException | SQLException erro) {
            String erroMsg = "Erro ao recuperar objetos: " + erro.getMessage();
            JOptionPane.showMessageDialog(null, erroMsg, "Mensagem", JOptionPane.ERROR_MESSAGE);
        }

        return ite;
    }

    public static MDItemImovel RecuperaObjCodigo(Integer item) {
        Connection cnx = RPConexao.getConexaoMySQL();
        MDItemImovel ite = new MDItemImovel();
        try {
            Statement objStm = cnx.createStatement();
            objStm.executeQuery("Select * from autopred.cad_item where ite_id =" + String.valueOf(item));
            ResultSet objRsSt = objStm.getResultSet();
            while (objRsSt.next()) {
                ite = new MDItemImovel();
                String vID       = objRsSt.getString("ite_id");
                String vDescr    = objRsSt.getString("ite_descricao");
                String vUnidade  = objRsSt.getString("ite_unidade");
                String vQuant    = objRsSt.getString("ite_quantidade");
                String vVidaUtil = objRsSt.getString("ite_vida_util");
                String vCusMedio = objRsSt.getString("ite_custo_medio");
                String vImo_ID   = objRsSt.getString("imo_id");

                ite.setITE_ID(Integer.parseInt(vID));
                ite.setITE_DESCRICAO(vDescr);
                ite.setITE_UNIDADE(Integer.parseInt(vUnidade));
                ite.setITE_QUANTIDADE(Integer.parseInt(vQuant));
                ite.setITE_VIDA_UTIL(Integer.parseInt(vVidaUtil));
                ite.setITE_CUSTO_MEDIO(Integer.parseInt(vCusMedio));
                ite.setIMO_ID(Integer.parseInt(vImo_ID));
            }

            objRsSt.close();
            objStm.close();
            cnx.close();
        } catch (NumberFormatException | SQLException erro) {
            String erroMsg = "Erro ao recuperar: " + erro.getMessage();
            JOptionPane.showMessageDialog(null, erroMsg, "Mensagem", JOptionPane.ERROR_MESSAGE);

        }

        return ite;
    }

    public static void atualizar(MDItemImovel item) {
        Connection cnx = RPConexao.getConexaoMySQL();
        PreparedStatement insereSt = null;
        String sqlU = "update autopred.cad_item set ite_id = ?, ite_descricao = ?, ite_unidade = ?, ite_quantidade = ?, ite_vida_util = ?, ite_custo_medio = ?, imo_id = ? where ite_id = " + item.getITE_ID();

        try {
            insereSt = cnx.prepareStatement(sqlU);

            insereSt.setInt(1, item.getITE_ID());
            insereSt.setString(2, item.getITE_DESCRICAO());
            insereSt.setInt(3, item.getITE_UNIDADE());
            insereSt.setInt(4, item.getITE_QUANTIDADE());
            insereSt.setInt(5, item.getITE_VIDA_UTIL());
            insereSt.setInt(6, item.getITE_CUSTO_MEDIO());
            insereSt.setInt(7, item.getIMO_ID());

            insereSt.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar item: " + e.getMessage());
        } finally {
            try {
                insereSt.close();
                cnx.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao encerrar conexão: " + e.getMessage() + "\n");
            }
        }
    }

    public static void excluir(MDItemImovel item) {
        Connection cnx = RPConexao.getConexaoMySQL();
        PreparedStatement insereSt = null;
        String sql = "delete from autopred.cad_item where ite_id = ?";
        try {
            insereSt = cnx.prepareStatement(sql);
            insereSt.setInt(1, item.getITE_ID());
            insereSt.executeUpdate();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir item: " + e.getMessage() + "\n");
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
                sql = "select min(ite_id) as COD from cad_item";
                break;
            case cNavAnterior:
                sql = "select max(ite_id) as COD from cad_item where ite_id < " + String.valueOf(codigoAtual);
                break;
            case cNavProximo:
                sql = "select min(ite_id) as COD from cad_item where ite_id > " + String.valueOf(codigoAtual);
                break;
            case cNavUltimo:
                sql = "select max(ite_id) as COD from cad_item";
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
    
    public static ArrayList<MDItemImovel> PesquisaObjeto(String sCampo, String sValor, boolean bTodaParte) {
        String sql = "select * from cad_item where " + sCampo + " like '";
        if (bTodaParte)
            sql = sql + "%";
        sql = sql + sValor + "%'";
        
        
        return RecuperaObjetos(sql);
    }
}