package com.web.repository;

import com.web.model.MDImovel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class RPImovel {

    public static final int cNavPrimeiro = 0;
    public static final int cNavAnterior = 1;
    public static final int cNavProximo = 2;
    public static final int cNavUltimo = 3;

    public static void salvar(MDImovel Imovel) {
        Connection cnx = RPConexao.getConexaoMySQL();
        PreparedStatement insereSt = null;
        String sql = "insert into autopred.cad_imovel values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try {
            insereSt = cnx.prepareStatement(sql);

            insereSt.setInt(1, Imovel.getIMO_ID());
            insereSt.setInt(2, Imovel.getIMO_TIPO());
            insereSt.setString(3, Imovel.getIMO_ENDERECO());
            insereSt.setInt(4, Imovel.getIMO_NUMERO());
            insereSt.setString(5, Imovel.getIMO_BAIRRO());
            insereSt.setString(6, Imovel.getIMO_COMPLEMENTO());
            insereSt.setInt(7, Imovel.getCID_ID());
            insereSt.setString(8, Imovel.getIMO_TELEFONE());
            insereSt.setInt(9, Imovel.getIMO_METRO_CONS());
            insereSt.setInt(10, Imovel.getIMO_ANO_CONS());
            insereSt.setByte(11, Imovel.getIMO_PLANTA_BAIXA());
            insereSt.setByte(12, Imovel.getIMO_PLANTA_ELETRICA());
            insereSt.setByte(13, Imovel.getIMO_PLANTA_HIDRA());
            insereSt.setInt(14, Imovel.getPRO_ID());
            insereSt.setInt(15, Imovel.getADM_ID());
            
            insereSt.executeUpdate();
            System.out.println("Linha inserida: " + Imovel.getIMO_ID() + " | " + Imovel.getIMO_ENDERECO());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao incluir imovel: " + e.getMessage() + "\n");
        } finally {
            try {
                insereSt.close();
                cnx.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao encerrar conexão: " + e.getMessage() + "\n");
            }
        }
    }

    public static ArrayList<MDImovel> RecuperaObjetos(String imovel) {
        Connection cnx = RPConexao.getConexaoMySQL();
        ArrayList<MDImovel> imov = new ArrayList<>();
        try {
            Statement objStm = cnx.createStatement();
            objStm.executeQuery(imovel);
            //objStm.executeQuery("Select * from autopred.cad_imovel where imo_id like '%" + imovel + "%'");
            ResultSet objRsSt = objStm.getResultSet();
            while (objRsSt.next()) {
                MDImovel  i = new MDImovel();
                
                String vID             = objRsSt.getString("imo_id");
                String vTipo           = objRsSt.getString("imo_tipo");
                String vEndereco       = objRsSt.getString("imo_endereco");
                String vNumero         = objRsSt.getString("imo_numero");
                String vBairro         = objRsSt.getString("imo_bairro");
                String vComplemento    = objRsSt.getString("imo_complemento");
                String vCid_ID         = objRsSt.getString("cid_id");
                String vTelefone       = objRsSt.getString("imo_telefone");
                String vMetroContruido = objRsSt.getString("imo_metro_cons");
                String vAnoConstruido  = objRsSt.getString("imo_ano_cons");
                String vPlantaBaixa    = objRsSt.getString("imo_planta_baixa");
                String vPlantaEletrica = objRsSt.getString("imo_planta_eletrica");
                String vPlantaHidra    = objRsSt.getString("imo_planta_hidra");
                String vPro_ID         = objRsSt.getString("pro_id");
                String vAdm_ID         = objRsSt.getString("adm_id");

                i.setIMO_ID(Integer.parseInt(vID));
                i.setIMO_TIPO(Integer.parseInt(vTipo));
                i.setIMO_ENDERECO(vEndereco);
                i.setIMO_NUMERO(Integer.parseInt(vNumero));
                i.setIMO_BAIRRO(vBairro);
                i.setIMO_COMPLEMENTO(vComplemento);
                i.setCID_ID(Integer.parseInt(vCid_ID));
                i.setIMO_TELEFONE(vTelefone);
                i.setIMO_METRO_CONS(Integer.parseInt(vMetroContruido));
                i.setIMO_ANO_CONS(Integer.parseInt(vAnoConstruido));
                i.setIMO_PLANTA_BAIXA(Byte.parseByte(vPlantaBaixa));
                i.setIMO_PLANTA_ELETRICA(Byte.parseByte(vPlantaEletrica));
                i.setIMO_PLANTA_HIDRA(Byte.parseByte(vPlantaHidra));
                i.setPRO_ID(Integer.parseInt(vPro_ID));
                i.setADM_ID(Integer.parseInt(vAdm_ID));

                imov.add(i);
            }
            objRsSt.close();
            objStm.close();
            cnx.close();
        } catch (NumberFormatException | SQLException erro) {
            String erroMsg = "Erro ao recuperar objetos: " + erro.getMessage();
            JOptionPane.showMessageDialog(null, erroMsg, "Mensagem", JOptionPane.ERROR_MESSAGE);
        }

        return imov;
    }

    public static MDImovel RecuperaObjCodigo(Integer imovel) {
        Connection cnx = RPConexao.getConexaoMySQL();
        MDImovel imov = new MDImovel();
        try {
            Statement objStm = cnx.createStatement();
            objStm.executeQuery("Select * from autopred.cad_imovel where imo_id =" + String.valueOf(imovel));
            ResultSet objRsSt = objStm.getResultSet();
            while (objRsSt.next()) {
                imov = new MDImovel();
                String vID             = objRsSt.getString("imo_id");
                String vTipo           = objRsSt.getString("imo_tipo");
                String vEndereco       = objRsSt.getString("imo_endereco");
                String vNumero         = objRsSt.getString("imo_numero");
                String vBairro         = objRsSt.getString("imo_bairro");
                String vComplemento    = objRsSt.getString("imo_complemento");
                String vCid_ID         = objRsSt.getString("cid_id");
                String vTelefone       = objRsSt.getString("imo_telefone");
                String vMetroContruido = objRsSt.getString("imo_metro_cons");
                String vAnoConstruido  = objRsSt.getString("imo_ano_cons");
                String vPlantaBaixa    = objRsSt.getString("imo_planta_baixa");
                String vPlantaEletrica = objRsSt.getString("imo_planta_eletrica");
                String vPlantaHidra    = objRsSt.getString("imo_planta_hidra");
                String vPro_ID         = objRsSt.getString("pro_id");
                String vAdm_ID         = objRsSt.getString("adm_id");

                imov.setIMO_ID(Integer.parseInt(vID));
                imov.setIMO_TIPO(Integer.parseInt(vTipo));
                imov.setIMO_ENDERECO(vEndereco);
                imov.setIMO_NUMERO(Integer.parseInt(vNumero));
                imov.setIMO_BAIRRO(vBairro);
                imov.setIMO_COMPLEMENTO(vComplemento);
                imov.setCID_ID(Integer.parseInt(vCid_ID));
                imov.setIMO_TELEFONE(vTelefone);
                imov.setIMO_METRO_CONS(Integer.parseInt(vMetroContruido));
                imov.setIMO_ANO_CONS(Integer.parseInt(vAnoConstruido));
                imov.setIMO_PLANTA_BAIXA(Byte.parseByte(vPlantaBaixa));
                imov.setIMO_PLANTA_ELETRICA(Byte.parseByte(vPlantaEletrica));
                imov.setIMO_PLANTA_HIDRA(Byte.parseByte(vPlantaHidra));
                imov.setPRO_ID(Integer.parseInt(vPro_ID));
                imov.setADM_ID(Integer.parseInt(vAdm_ID));
            }

            objRsSt.close();
            objStm.close();
            cnx.close();
        } catch (NumberFormatException | SQLException erro) {
            String erroMsg = "Erro ao recuperar: " + erro.getMessage();
            JOptionPane.showMessageDialog(null, erroMsg, "Mensagem", JOptionPane.ERROR_MESSAGE);

        }

        return imov;
    }

    public static void atualizar(MDImovel imovel) {
        Connection cnx = RPConexao.getConexaoMySQL();
        PreparedStatement insereSt = null;
        String sqlU = "update autopred.cad_imovel set imo_id = ?, imo_tipo = ?, imo_endereco = ?, "
                    + "imo_numero = ?, imo_bairro = ?, imo_complemento = ?, cid_id = ?, imo_telefone = ?, "
                    + "imo_metro_cons = ?, imo_ano_cons = ?, imo_planta_baixa = ?, imo_planta_eletrica = ?, "
                    + "imo_planta_hidra = ?, pro_id = ?, adm_id = ? where imo_id = " + imovel.getIMO_ID();

        try {
            insereSt = cnx.prepareStatement(sqlU);

            insereSt.setInt(1, imovel.getIMO_ID());
            insereSt.setInt(2, imovel.getIMO_TIPO());
            insereSt.setString(3, imovel.getIMO_ENDERECO());
            insereSt.setInt(4, imovel.getIMO_NUMERO());
            insereSt.setString(5, imovel.getIMO_BAIRRO());
            insereSt.setString(6, imovel.getIMO_COMPLEMENTO());
            insereSt.setInt(7, imovel.getCID_ID());
            insereSt.setString(8, imovel.getIMO_TELEFONE());
            insereSt.setInt(9, imovel.getIMO_METRO_CONS());
            insereSt.setInt(10, imovel.getIMO_ANO_CONS());
            insereSt.setByte(11, imovel.getIMO_PLANTA_BAIXA());
            insereSt.setByte(12, imovel.getIMO_PLANTA_ELETRICA());
            insereSt.setByte(13, imovel.getIMO_PLANTA_HIDRA());
            insereSt.setInt(14, imovel.getPRO_ID());
            insereSt.setInt(15, imovel.getADM_ID());

            insereSt.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar imovel: " + e.getMessage());
        } finally {
            try {
                insereSt.close();
                cnx.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao encerrar conexão: " + e.getMessage() + "\n");
            }
        }
    }

    public static void excluir(MDImovel imovel) {
        Connection cnx = RPConexao.getConexaoMySQL();
        PreparedStatement insereSt = null;
        String sql = "delete from autopred.cad_imovel where imo_id = ?";
        try {
            insereSt = cnx.prepareStatement(sql);
            insereSt.setInt(1, imovel.getIMO_ID());
            insereSt.executeUpdate();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir imovel: " + e.getMessage() + "\n");
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
                sql = "select min(imo_id) as COD from cad_imovel";
                break;
            case cNavAnterior:
                sql = "select max(imo_id) as COD from cad_imovel where imo_id < " + String.valueOf(codigoAtual);
                break;
            case cNavProximo:
                sql = "select min(imo_id) as COD from cad_imovel where imo_id > " + String.valueOf(codigoAtual);
                break;
            case cNavUltimo:
                sql = "select max(imo_id) as COD from cad_imovel";
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
    
    public static ArrayList<MDImovel> PesquisaObjeto(String sCampo, String sValor, boolean bTodaParte) {
        String sql = "select * from cad_imovel where " + sCampo + " like '";
        if (bTodaParte)
            sql = sql + "%";
        sql = sql + sValor + "%'";
        
        
        return RecuperaObjetos(sql);
    }
}