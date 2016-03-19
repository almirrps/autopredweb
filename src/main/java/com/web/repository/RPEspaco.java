package com.web.repository;

import com.web.model.MDEspaco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class RPEspaco {

    public static final int cNavPrimeiro = 0;
    public static final int cNavAnterior = 1;
    public static final int cNavProximo = 2;
    public static final int cNavUltimo = 3;

    public static void salvar(MDEspaco Espaco) {
        Connection cnx = RPConexao.getConexaoMySQL();
        PreparedStatement insereSt = null;
        String sql = "insert into autopred.cad_espaco values(?,?,?,?,?,?,?,?,?)";

        try {
            insereSt = cnx.prepareStatement(sql);

            insereSt.setInt(1, Espaco.getESP_ID());
            insereSt.setString(2, Espaco.getESP_DESCRICAO());
            insereSt.setInt(3, Espaco.getTIP_ID());
            insereSt.setInt(4, Espaco.getESP_ANO());
            insereSt.setInt(5, Espaco.getESP_COMPRIMENTO());
            insereSt.setInt(6, Espaco.getESP_LARGURA());
            insereSt.setInt(7, Espaco.getESP_ALTURA());
            insereSt.setInt(8, Espaco.getESP_AREA());
            insereSt.setInt(9, Espaco.getIMO_ID());

            insereSt.executeUpdate();
            System.out.println("Linha inserida: " + Espaco.getESP_ID() + " | " + Espaco.getESP_DESCRICAO());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao incluir espaço: " + e.getMessage() + "\n");
        } finally {
            try {
                insereSt.close();
                cnx.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao encerrar conexão: " + e.getMessage() + "\n");
            }
        }
    }

    public static ArrayList<MDEspaco> RecuperaObjetos(String espaco) {
        Connection cnx = RPConexao.getConexaoMySQL();
        ArrayList<MDEspaco> esp = new ArrayList<>();
        try {
            Statement objStm = cnx.createStatement();
            objStm.executeQuery(espaco);
            //objStm.executeQuery("Select * from autopred.cad_espaco where esp_descricao like '%" + espaco + "%'");
            ResultSet objRsSt = objStm.getResultSet();
            while (objRsSt.next()) {
                MDEspaco a = new MDEspaco();
                String vID      = objRsSt.getString("esp_id");
                String vDescr   = objRsSt.getString("esp_descricao");
                String vTip_ID  = objRsSt.getString("tip_id");
                String vAno     = objRsSt.getString("esp_ano");
                String vCompr   = objRsSt.getString("esp_comprimento");
                String vLargura = objRsSt.getString("esp_largura");
                String vAltura  = objRsSt.getString("esp_altura");
                String vArea    = objRsSt.getString("esp_area");
                String vImo_ID  = objRsSt.getString("imo_id");
                
                a.setESP_ID(Integer.parseInt(vID));
                a.setESP_DESCRICAO(vDescr);
                a.setTIP_ID(Integer.parseInt(vTip_ID));
                a.setESP_ANO(Integer.parseInt(vAno));
                a.setESP_COMPRIMENTO(Integer.parseInt(vCompr));
                a.setESP_LARGURA(Integer.parseInt(vLargura));
                a.setESP_ALTURA(Integer.parseInt(vAltura));
                a.setESP_ALTURA(Integer.parseInt(vArea));
                a.setESP_ALTURA(Integer.parseInt(vImo_ID));
                
                esp.add(a);
            }
            objRsSt.close();
            objStm.close();
            cnx.close();
        } catch (NumberFormatException | SQLException erro) {
            String erroMsg = "Erro ao recuperar objetos: " + erro.getMessage();
            JOptionPane.showMessageDialog(null, erroMsg, "Mensagem", JOptionPane.ERROR_MESSAGE);
        }

        return esp;
    }

    public static MDEspaco RecuperaObjCodigo(Integer espaco) {
        Connection cnx = RPConexao.getConexaoMySQL();
        MDEspaco esp = new MDEspaco();
        try {
            Statement objStm = cnx.createStatement();
            objStm.executeQuery("Select * from autopred.cad_espaco where esp_id =" + String.valueOf(espaco));
            ResultSet objRsSt = objStm.getResultSet();
            while (objRsSt.next()) {
                esp = new MDEspaco();
                String vID      = objRsSt.getString("esp_id");
                String vDescr   = objRsSt.getString("esp_descricao");
                String vTip_ID  = objRsSt.getString("tip_id");
                String vAno     = objRsSt.getString("esp_ano");
                String vCompr   = objRsSt.getString("esp_comprimento");
                String vLargura = objRsSt.getString("esp_largura");
                String vAltura  = objRsSt.getString("esp_altura");
                String vArea    = objRsSt.getString("esp_area");
                String vImo_ID  = objRsSt.getString("imo_id");

                esp.setESP_ID(Integer.parseInt(vID));
                esp.setESP_DESCRICAO(vDescr);
                esp.setTIP_ID(Integer.parseInt(vTip_ID));
                esp.setESP_ANO(Integer.parseInt(vAno));
                esp.setESP_COMPRIMENTO(Integer.parseInt(vCompr));
                esp.setESP_LARGURA(Integer.parseInt(vLargura));
                esp.setESP_ALTURA(Integer.parseInt(vAltura));
                esp.setESP_AREA(Integer.parseInt(vArea));
                esp.setIMO_ID(Integer.parseInt(vImo_ID));
            }

            objRsSt.close();
            objStm.close();
            cnx.close();
        } catch (NumberFormatException | SQLException erro) {
            String erroMsg = "Erro ao recuperar: " + erro.getMessage();
            JOptionPane.showMessageDialog(null, erroMsg, "Mensagem", JOptionPane.ERROR_MESSAGE);

        }

        return esp;
    }

    public static void atualizar(MDEspaco espaco) {
        Connection cnx = RPConexao.getConexaoMySQL();
        PreparedStatement insereSt = null;
        String sqlU = "update autopred.cad_espaco set esp_id = ?, esp_descricao = ?, tip_id = ?, esp_ano = ?, esp_comprimento = ?, esp_largura = ?, esp_altura = ?, esp_area = ?, imo_id = ? where esp_id = " + espaco.getESP_ID();

        try {
            insereSt = cnx.prepareStatement(sqlU);

            insereSt.setInt(1, espaco.getESP_ID());
            insereSt.setString(2, espaco.getESP_DESCRICAO());
            insereSt.setInt(3, espaco.getTIP_ID());
            insereSt.setInt(4, espaco.getESP_ANO());
            insereSt.setInt(5, espaco.getESP_COMPRIMENTO());
            insereSt.setInt(6, espaco.getESP_LARGURA());
            insereSt.setInt(7, espaco.getESP_ALTURA());
            insereSt.setInt(8, espaco.getESP_AREA());
            insereSt.setInt(9, espaco.getIMO_ID());

            insereSt.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar espaço: " + e.getMessage());
        } finally {
            try {
                insereSt.close();
                cnx.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao encerrar conexão: " + e.getMessage() + "\n");
            }
        }
    }

    public static void excluir(MDEspaco espaco) {
        Connection cnx = RPConexao.getConexaoMySQL();
        PreparedStatement insereSt = null;
        String sql = "delete from autopred.cad_espaco where esp_id = ?";
        try {
            insereSt = cnx.prepareStatement(sql);
            insereSt.setInt(1, espaco.getESP_ID());
            insereSt.executeUpdate();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir espaço: " + e.getMessage() + "\n");
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
                sql = "select min(esp_id) as COD from cad_espaco";
                break;
            case cNavAnterior:
                sql = "select max(esp_id) as COD from cad_espaco where esp_id < " + String.valueOf(codigoAtual);
                break;
            case cNavProximo:
                sql = "select min(esp_id) as COD from cad_espaco where esp_id > " + String.valueOf(codigoAtual);
                break;
            case cNavUltimo:
                sql = "select max(esp_id) as COD from cad_espaco";
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
    
    public static ArrayList<MDEspaco> PesquisaObjeto(String sCampo, String sValor, boolean bTodaParte) {
        String sql = "select * from cad_espaco where " + sCampo + " like '";
        if (bTodaParte)
            sql = sql + "%";
        sql = sql + sValor + "%'";
        
        
        return RecuperaObjetos(sql);
        
    }
}