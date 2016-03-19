package com.web.repository;

import com.web.model.MDBanco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class RPBanco {

    public static final int cNavPrimeiro = 0;
    public static final int cNavAnterior = 1;
    public static final int cNavProximo = 2;
    public static final int cNavUltimo = 3;

    public static void salvar(MDBanco Banco) {
        Connection cnx = RPConexao.getConexaoMySQL();
        PreparedStatement insereSt = null;
        String sql = "insert into autopred.cad_banco values(?,?,?,?,?)";

        try {
            insereSt = cnx.prepareStatement(sql);

            insereSt.setInt(1, Banco.getBAN_ID());
            insereSt.setInt(2, Banco.getBAN_NUMERO());
            insereSt.setString(3, Banco.getBAN_NOME());
            insereSt.setString(4, Banco.getBAN_AGENCIA());
            insereSt.setString(5, Banco.getBAN_CONTA());

            insereSt.executeUpdate();
            System.out.println("Linha inserida: " + Banco.getBAN_ID() + " | " + Banco.getBAN_NOME());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao incluir banco: " + e.getMessage() + "\n");
        } finally {
            try {
                insereSt.close();
                cnx.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao encerrar conexão: " + e.getMessage() + "\n");
            }
        }
    }

    public static ArrayList<MDBanco> RecuperaObjetos(String banco) {
        Connection cnx = RPConexao.getConexaoMySQL();
        ArrayList<MDBanco> ban = new ArrayList<>();
        try {
            Statement objStm = cnx.createStatement();
            objStm.executeQuery(banco);
            //objStm.executeQuery("Select * from autopred.cad_banco where ban_nome like '%" + banco + "%'");
            ResultSet objRsSt = objStm.getResultSet();
            while (objRsSt.next()) {
                MDBanco b       = new MDBanco();
                String vID      = objRsSt.getString("ban_id");
                Integer vNumero = objRsSt.getInt("ban_numero");
                String vNome    = objRsSt.getString("ban_nome");
                String vAgencia = objRsSt.getString("ban_agencia");
                String vConta   = objRsSt.getString("ban_conta");
                
                b.setBAN_ID(Integer.parseInt(vID));
                b.setBAN_NUMERO(vNumero);
                b.setBAN_NOME(vNome);
                b.setBAN_AGENCIA(vAgencia);
                b.setBAN_CONTA(vConta);
                ban.add(b);
            }
            objRsSt.close();
            objStm.close();
            cnx.close();
        } catch (NumberFormatException | SQLException erro) {
            String erroMsg = "Erro ao recuperar objetos: " + erro.getMessage();
            JOptionPane.showMessageDialog(null, erroMsg, "Mensagem", JOptionPane.ERROR_MESSAGE);
        }

        return ban;
    }

    public static MDBanco RecuperaObjCodigo(Integer banco) {
        Connection cnx = RPConexao.getConexaoMySQL();
        MDBanco ban = new MDBanco();
        try {
            Statement objStm = cnx.createStatement();
            objStm.executeQuery("Select * from autopred.cad_banco where ban_id =" + String.valueOf(banco));
            ResultSet objRsSt = objStm.getResultSet();
            while (objRsSt.next()) {
                ban = new MDBanco();
                String vID      = objRsSt.getString("ban_id");
                Integer vNumero = objRsSt.getInt("ban_numero");
                String vNome    = objRsSt.getString("ban_nome");
                String vAgencia = objRsSt.getString("ban_agencia");
                String vConta   = objRsSt.getString("ban_conta");

                ban.setBAN_ID(Integer.parseInt(vID));
                ban.setBAN_NUMERO(vNumero);
                ban.setBAN_NOME(vNome);
                ban.setBAN_AGENCIA(vAgencia);
                ban.setBAN_CONTA(vConta);
            }

            objRsSt.close();
            objStm.close();
            cnx.close();
        } catch (NumberFormatException | SQLException erro) {
            String erroMsg = "Erro ao recuperar: " + erro.getMessage();
            JOptionPane.showMessageDialog(null, erroMsg, "Mensagem", JOptionPane.ERROR_MESSAGE);

        }

        return ban;
    }

    public static void atualizar(MDBanco banco) {
        Connection cnx = RPConexao.getConexaoMySQL();
        PreparedStatement insereSt = null;
        String sqlU = "update autopred.cad_banco set ban_id = ?, ban_numero = ?, ban_nome = ?, ban_agencia = ?, ban_conta = ? where ban_id = " + banco.getBAN_ID();

        try {
            insereSt = cnx.prepareStatement(sqlU);

            insereSt.setInt(1, banco.getBAN_ID());
            insereSt.setInt(2, banco.getBAN_NUMERO());
            insereSt.setString(3, banco.getBAN_NOME());
            insereSt.setString(4, banco.getBAN_AGENCIA());
            insereSt.setString(5, banco.getBAN_CONTA());

            insereSt.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar banco: " + e.getMessage());
        } finally {
            try {
                insereSt.close();
                cnx.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao encerrar conexão: " + e.getMessage() + "\n");
            }
        }
    }

    public static void excluir(MDBanco banco) {
        Connection cnx = RPConexao.getConexaoMySQL();
        PreparedStatement insereSt = null;
        String sql = "delete from autopred.cad_banco where ban_id = ?";
        try {
            insereSt = cnx.prepareStatement(sql);
            insereSt.setInt(1, banco.getBAN_ID());
            insereSt.executeUpdate();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir banco: " + e.getMessage() + "\n");
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
                sql = "select min(ban_id) as COD from cad_banco";
                break;
            case cNavAnterior:
                sql = "select max(ban_id) as COD from cad_banco where ban_id < " + String.valueOf(codigoAtual);
                break;
            case cNavProximo:
                sql = "select min(ban_id) as COD from cad_banco where ban_id > " + String.valueOf(codigoAtual);
                break;
            case cNavUltimo:
                sql = "select max(ban_id) as COD from cad_banco";
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
    
    public static ArrayList<MDBanco> PesquisaObjeto(String sCampo, String sValor, boolean bTodaParte) {
        String sql = "select * from cad_banco where " + sCampo + " like '";
        if (bTodaParte)
            sql = sql + "%";
        sql = sql + sValor + "%'";
        
        
        return RecuperaObjetos(sql);
        
    }
}