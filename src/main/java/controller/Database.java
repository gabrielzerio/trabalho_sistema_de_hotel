/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Gabriel
 */
public class Database {
    private static Database INSTANCE = null;
    private Connection connection = null;
    
    private Database(){
        try {
            connection = DriverManager.getConnection("jdbc:sqlite::resource:" + Banco.class.getResource("/res/sample.db"));
        } catch (SQLException e) {
            System.err.println("Houve um problema ao criar o arquivo do banco!");
            e.printStackTrace();
        }
    }
    public static Database getInstance(){
        if(INSTANCE == null){
            INSTANCE= new Database();
        }
        return INSTANCE;
    }
    
    public Connection getConnection(){
        return this.connection;
    }
    public void closeConnection(){
        try {
            connection.close();
        } catch (SQLException ex) {
            System.err.println("Houve um problema ao fechar a conexão com o banco!");
            ex.printStackTrace();
        }
    }
}
