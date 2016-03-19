package com.web.repository;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PC_TCC
 */
public class RPConexao {
    
    static String status = "Não conectou...";

//Método de Conexão//
public static Connection getConexaoMySQL() {

    Connection connection = null;
    
    try {

        // Carregando o JDBC Driver padrão
        String driverName = "com.mysql.jdbc.Driver";                        
        Class.forName(driverName).newInstance();

        // Configurando a nossa conexão com um banco de dados//
        String serverName = "localhost";    //caminho do servidor do BD
        String mydatabase ="autopred";      //nome do seu banco de dados
        String url = "jdbc:mysql://" + serverName + "/" + mydatabase;
        String username = "root";           //nome de um usuário de seu BD      
        String password = "tcc123";         //sua senha de acesso
        connection = DriverManager.getConnection(url, username, password);

        status = ("Conectado com sucesso!");
        } catch (SQLException e) {
            //Não conseguindo se conectar ao banco
            status = ("Nao foi possivel conectar ao Banco de Dados.");
        } catch (ClassNotFoundException e) {  //Driver não encontrado
            status = ("O driver expecificado nao foi encontrado.");
        } catch (InstantiationException | IllegalAccessException e) {
            status = e.getMessage();
        } 
        return connection;
    }

    //Método que fecha sua conexão//
    public static boolean FecharConexao() {
        try {
            getConexaoMySQL().close();
            return true;
        } catch (SQLException e) {
            return false;
        } 
    }

    //Método que reinicia sua conexão//
    public static java.sql.Connection ReiniciarConexao() {
        FecharConexao();
        return getConexaoMySQL();
    }

}