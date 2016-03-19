package com.web.repository;

import com.web.model.MDProprietario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class RPProprietario {

    public static final int cNavPrimeiro = 0;
    public static final int cNavAnterior = 1;
    public static final int cNavProximo = 2;
    public static final int cNavUltimo = 3;

    public static void salvar(MDProprietario Proprietario) {
        Connection cnx = RPConexao.getConexaoMySQL();
        PreparedStatement insereSt = null;
        String sql = "insert into autopred.cad_proprietario values(?,?,?,?,?,?,?,?,?,?,?)";

        try {
            insereSt = cnx.prepareStatement(sql);

            insereSt.setInt(1, Proprietario.getPRO_ID());            
            insereSt.setString(2, Proprietario.getPRO_CPF());
            insereSt.setString(3, Proprietario.getPRO_NOME());
            insereSt.setString(4, Proprietario.getPRO_ENDERECO());
            insereSt.setString(5, Proprietario.getPRO_NUMERO());
            insereSt.setString(6, Proprietario.getPRO_BAIRRO());
            insereSt.setString(7, Proprietario.getPRO_COMPLEMENTO());
            insereSt.setInt(8, Proprietario.getCID_ID());
            insereSt.setString(9, Proprietario.getPRO_TELEFONE());
            insereSt.setInt(10, Proprietario.getBAN_ID());
            insereSt.setInt(11, Proprietario.getADM_ID());

            insereSt.executeUpdate();
            System.out.println("Linha inserida: " + Proprietario.getPRO_ID() + " | " + Proprietario.getPRO_NOME());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao incluir proprietario: " + e.getMessage() + "\n");
        } finally {
            try {
                insereSt.close();
                cnx.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao encerrar conexão: " + e.getMessage() + "\n");
            }
        }
    }

    public static ArrayList<MDProprietario> RecuperaObjetos(String proprietario) {
        Connection cnx = RPConexao.getConexaoMySQL();
        ArrayList<MDProprietario> prop = new ArrayList<>();
        try {
            Statement objStm = cnx.createStatement();
            objStm.executeQuery(proprietario);
            //objStm.executeQuery("Select * from autopred.cad_proprietario where pro_nome like '%" + proprietario + "%'");
            ResultSet objRsSt = objStm.getResultSet();
            while (objRsSt.next()) {
                MDProprietario  p = new MDProprietario();
                String vID          = objRsSt.getString("pro_id");
                String vCPF         = objRsSt.getString("pro_cpf");
                String vNome        = objRsSt.getString("pro_nome");
                String vEndereco    = objRsSt.getString("pro_endereco");
                String vNumero      = objRsSt.getString("pro_numero");
                String vBairro      = objRsSt.getString("pro_bairro");
                String vComplemento = objRsSt.getString("pro_complemento");
                String vCid_ID      = objRsSt.getString("cid_id");
                String vTelefone    = objRsSt.getString("pro_telefone");
                String vBan_ID      = objRsSt.getString("ban_id");
                String vAdm_ID      = objRsSt.getString("adm_id");

                p.setPRO_ID(Integer.parseInt(vID));
                p.setPRO_CPF(vCPF);
                p.setPRO_NOME(vNome);
                p.setPRO_ENDERECO(vEndereco);
                p.setPRO_NUMERO(vNumero);
                p.setPRO_BAIRRO(vBairro);
                p.setPRO_COMPLEMENTO(vComplemento);
                p.setCID_ID(Integer.parseInt(vCid_ID));
                p.setPRO_TELEFONE(vTelefone);
                p.setBAN_ID(Integer.parseInt(vBan_ID));
                p.setADM_ID(Integer.parseInt(vAdm_ID));
                
                prop.add(p);
            }
            objRsSt.close();
            objStm.close();
            cnx.close();
        } catch (NumberFormatException | SQLException erro) {
            String erroMsg = "Erro ao recuperar objetos: " + erro.getMessage();
            JOptionPane.showMessageDialog(null, erroMsg, "Mensagem", JOptionPane.ERROR_MESSAGE);
        }

        return prop;
    }

    public static MDProprietario RecuperaObjCodigo(Integer proprietario) {
        Connection cnx = RPConexao.getConexaoMySQL();
        MDProprietario prop = new MDProprietario();
        try {
            Statement objStm = cnx.createStatement();
            objStm.executeQuery("Select * from autopred.cad_proprietario where pro_id =" + String.valueOf(proprietario));
            ResultSet objRsSt = objStm.getResultSet();
            while (objRsSt.next()) {
                prop = new MDProprietario();
                String vID          = objRsSt.getString("pro_id");
                String vCPF         = objRsSt.getString("pro_cpf");
                String vNome        = objRsSt.getString("pro_nome");
                String vEndereco    = objRsSt.getString("pro_endereco");
                String vNumero      = objRsSt.getString("pro_numero");
                String vBairro      = objRsSt.getString("pro_bairro");
                String vComplemento = objRsSt.getString("pro_complemento");
                String vCid_ID      = objRsSt.getString("cid_id");
                String vTelefone    = objRsSt.getString("pro_telefone");
                String vBan_ID      = objRsSt.getString("ban_id");
                String vAdm_ID      = objRsSt.getString("adm_id");

                prop.setPRO_ID(Integer.parseInt(vID));
                prop.setPRO_CPF(vCPF);
                prop.setPRO_NOME(vNome);
                prop.setPRO_ENDERECO(vEndereco);
                prop.setPRO_NUMERO(vNumero);
                prop.setPRO_BAIRRO(vBairro);
                prop.setPRO_COMPLEMENTO(vComplemento);
                prop.setCID_ID(Integer.parseInt(vCid_ID));
                prop.setPRO_TELEFONE(vTelefone);
                prop.setBAN_ID(Integer.parseInt(vBan_ID));
                prop.setADM_ID(Integer.parseInt(vAdm_ID));
                
            }

            objRsSt.close();
            objStm.close();
            cnx.close();
        } catch (NumberFormatException | SQLException erro) {
            String erroMsg = "Erro ao recuperar: " + erro.getMessage();
            JOptionPane.showMessageDialog(null, erroMsg, "Mensagem", JOptionPane.ERROR_MESSAGE);

        }

        return prop;
    }

    public static void atualizar(MDProprietario proprietario) {
        Connection cnx = RPConexao.getConexaoMySQL();
        PreparedStatement insereSt = null;
        String sqlU = "update autopred.cad_proprietario set pro_id = ?, pro_cpf = ?, pro_nome = ?, "
                    + "pro_endereco = ?, pro_numero = ?, pro_bairro = ?, pro_complemento = ?, cid_id = ?, "
                    + "pro_telefone = ?, ban_id = ?, adm_id = ? where pro_id = " + proprietario.getPRO_ID();

        try {
            insereSt = cnx.prepareStatement(sqlU);

            insereSt.setInt(1, proprietario.getPRO_ID());            
            insereSt.setString(2, proprietario.getPRO_CPF());
            insereSt.setString(3, proprietario.getPRO_NOME());
            insereSt.setString(4, proprietario.getPRO_ENDERECO());
            insereSt.setString(5, proprietario.getPRO_NUMERO());
            insereSt.setString(6, proprietario.getPRO_BAIRRO());
            insereSt.setString(7, proprietario.getPRO_COMPLEMENTO());
            insereSt.setInt(8, proprietario.getCID_ID());
            insereSt.setString(9, proprietario.getPRO_TELEFONE());
            insereSt.setInt(10, proprietario.getBAN_ID());
            insereSt.setInt(11, proprietario.getADM_ID());

            insereSt.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar proprietario: " + e.getMessage());
        } finally {
            try {
                insereSt.close();
                cnx.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao encerrar conexão: " + e.getMessage() + "\n");
            }
        }
    }

    public static void excluir(MDProprietario proprietario) {
        Connection cnx = RPConexao.getConexaoMySQL();
        PreparedStatement insereSt = null;
        String sql = "delete from autopred.cad_proprietario where pro_id = ?";
        try {
            insereSt = cnx.prepareStatement(sql);
            insereSt.setInt(1, proprietario.getPRO_ID());
            insereSt.executeUpdate();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir proprietario: " + e.getMessage() + "\n");
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
                sql = "select min(pro_id) as COD from cad_proprietario";
                break;
            case cNavAnterior:
                sql = "select max(pro_id) as COD from cad_proprietario where pro_id < " + String.valueOf(codigoAtual);
                break;
            case cNavProximo:
                sql = "select min(pro_id) as COD from cad_proprietario where pro_id > " + String.valueOf(codigoAtual);
                break;
            case cNavUltimo:
                sql = "select max(pro_id) as COD from cad_proprietario";
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
    
    public static ArrayList<MDProprietario> PesquisaObjeto(String sCampo, String sValor, boolean bTodaParte) {
        String sql = "select * from cad_proprietario where " + sCampo + " like '";
        if (bTodaParte)
            sql = sql + "%";
        sql = sql + sValor + "%'";
        
        
        return RecuperaObjetos(sql);
        
    }
}