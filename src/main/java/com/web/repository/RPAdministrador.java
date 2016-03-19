package com.web.repository;

import com.web.model.MDAdministrador;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class RPAdministrador {

    public static final int cNavPrimeiro = 0;
    public static final int cNavAnterior = 1;
    public static final int cNavProximo = 2;
    public static final int cNavUltimo = 3;

    public static void salvar(MDAdministrador Administrador) {
        Connection cnx = RPConexao.getConexaoMySQL();
        PreparedStatement insereSt = null;
        String sql = "insert into autopred.cad_admin_sis values(?,?,?,?,?,?,?)";

        try {
            insereSt = cnx.prepareStatement(sql);

            insereSt.setInt(1, Administrador.getADM_ID());
            insereSt.setString(2, Administrador.getADM_NOME());
            insereSt.setInt(3, Administrador.getCAF_ID());
            insereSt.setInt(4, Administrador.getSET_ID());
            insereSt.setString(5, Administrador.getADM_EMAIL());
            insereSt.setString(6, Administrador.getADM_USUARIO());
            insereSt.setString(7, Administrador.getADM_SENHA());

            insereSt.executeUpdate();
            System.out.println("Linha inserida: " + Administrador.getADM_ID() + " | " + Administrador.getADM_NOME());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao incluir administrador: " + e.getMessage() + "\n");
        } finally {
            try {
                insereSt.close();
                cnx.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao encerrar conexão: " + e.getMessage() + "\n");
            }
        }
    }

    public static ArrayList<MDAdministrador> RecuperaObjetos(String administrador) {
        Connection cnx = RPConexao.getConexaoMySQL();
        ArrayList<MDAdministrador> adm = new ArrayList<>();
        try {
            Statement objStm = cnx.createStatement();
            objStm.executeQuery(administrador);
            //objStm.executeQuery("Select * from autopred.cad_admin_sis where adm_nome like '%" + administrador + "%'");
            ResultSet objRsSt = objStm.getResultSet();
            while (objRsSt.next()) {
                MDAdministrador a = new MDAdministrador();
                String vID      = objRsSt.getString("adm_id");
                String vNome    = objRsSt.getString("adm_nome");
                String vCaf_ID  = objRsSt.getString("caf_id");
                String vSet_ID  = objRsSt.getString("set_id");
                String vEmail   = objRsSt.getString("adm_email");
                String vUsuario = objRsSt.getString("adm_usuario");
                String vSenha   = objRsSt.getString("adm_senha");
                
                a.setADM_ID(Integer.parseInt(vID));
                a.setADM_NOME(vNome);
                a.setCAF_ID(Integer.parseInt(vCaf_ID));
                a.setSET_ID(Integer.parseInt(vSet_ID));
                a.setADM_EMAIL(vEmail);
                a.setADM_USUARIO(vUsuario);
                a.setADM_SENHA(vSenha);
                adm.add(a);
            }
            objRsSt.close();
            objStm.close();
            cnx.close();
        } catch (NumberFormatException | SQLException erro) {
            String erroMsg = "Erro ao recuperar objetos: " + erro.getMessage();
            JOptionPane.showMessageDialog(null, erroMsg, "Mensagem", JOptionPane.ERROR_MESSAGE);
        }

        return adm;
    }

    public static MDAdministrador RecuperaObjCodigo(Integer administrador) {
        Connection cnx = RPConexao.getConexaoMySQL();
        MDAdministrador adm = new MDAdministrador();
        try {
            Statement objStm = cnx.createStatement();
            objStm.executeQuery("Select * from autopred.cad_admin_sis where adm_id =" + String.valueOf(administrador));
            ResultSet objRsSt = objStm.getResultSet();
            while (objRsSt.next()) {
                adm = new MDAdministrador();
                String vID      = objRsSt.getString("adm_id");
                String vNome    = objRsSt.getString("adm_nome");
                String vCaf_ID  = objRsSt.getString("caf_id");
                String vSet_ID  = objRsSt.getString("set_id");
                String vEmail   = objRsSt.getString("adm_email");
                String vUsuario = objRsSt.getString("adm_usuario");
                String vSenha   = objRsSt.getString("adm_senha");

                adm.setADM_ID(Integer.parseInt(vID));
                adm.setADM_NOME(vNome);
                adm.setCAF_ID(Integer.parseInt(vCaf_ID));
                adm.setSET_ID(Integer.parseInt(vSet_ID));
                adm.setADM_EMAIL(vEmail);
                adm.setADM_USUARIO(vUsuario);
                adm.setADM_SENHA(vSenha);
            }

            objRsSt.close();
            objStm.close();
            cnx.close();
        } catch (NumberFormatException | SQLException erro) {
            String erroMsg = "Erro ao recuperar: " + erro.getMessage();
            JOptionPane.showMessageDialog(null, erroMsg, "Mensagem", JOptionPane.ERROR_MESSAGE);

        }

        return adm;
    }

    public static void atualizar(MDAdministrador administrador) {
        Connection cnx = RPConexao.getConexaoMySQL();
        PreparedStatement insereSt = null;
        String sqlU = "update autopred.cad_admin_sis set adm_id = ?, adm_nome = ?, caf_id = ?, set_id = ?, adm_email = ?, adm_usuario = ?, adm_senha = ? where adm_id = " + administrador.getADM_ID();

        try {
            insereSt = cnx.prepareStatement(sqlU);

            insereSt.setInt(1, administrador.getADM_ID());
            insereSt.setString(2, administrador.getADM_NOME());
            insereSt.setInt(3, administrador.getCAF_ID());
            insereSt.setInt(4, administrador.getSET_ID());
            insereSt.setString(5, administrador.getADM_EMAIL());
            insereSt.setString(6, administrador.getADM_USUARIO());
            insereSt.setString(7, administrador.getADM_SENHA());

            insereSt.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar administrador: " + e.getMessage());
        } finally {
            try {
                insereSt.close();
                cnx.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao encerrar conexão: " + e.getMessage() + "\n");
            }
        }
    }

    public static void excluir(MDAdministrador administrador) {
        Connection cnx = RPConexao.getConexaoMySQL();
        PreparedStatement insereSt = null;
        String sql = "delete from autopred.cad_admin_sis where adm_id = ?";
        try {
            insereSt = cnx.prepareStatement(sql);
            insereSt.setInt(1, administrador.getADM_ID());
            insereSt.executeUpdate();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir administrador: " + e.getMessage() + "\n");
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
                sql = "select min(adm_id) as COD from cad_admin_sis";
                break;
            case cNavAnterior:
                sql = "select max(adm_id) as COD from cad_admin_sis where adm_id < " + String.valueOf(codigoAtual);
                break;
            case cNavProximo:
                sql = "select min(adm_id) as COD from cad_admin_sis where adm_id > " + String.valueOf(codigoAtual);
                break;
            case cNavUltimo:
                sql = "select max(adm_id) as COD from cad_admin_sis";
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
    
    public static ArrayList<MDAdministrador> PesquisaObjeto(String sCampo, String sValor, boolean bTodaParte) {
        String sql = "select * from cad_admin_sis where " + sCampo + " like '";
        if (bTodaParte)
            sql = sql + "%";
        sql = sql + sValor + "%'";
        
        
        return RecuperaObjetos(sql);
        
    }
}