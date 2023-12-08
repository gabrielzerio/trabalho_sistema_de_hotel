/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import model.Cliente;
import model.Reserva;

/**
 *
 * @author Gabriel
 */
public class ReservaDAO {
    private Connection con;

    public ReservaDAO() {
        con = Database.getInstance().getConnection();
    }
    public boolean verificaDataReserva(int numero, Date dataInicio, Date dataFim) throws SQLException{
        //String sql = "select data_inicio, data_fim from reserva where numero_quarto ="+ numero+ " and ((data_inicio >= '"+dataInicio+"' and data_inicio <= '"+dataInicio+"') OR (data_fim >= '"+dataFim+"' and data_fim <= '"+dataInicio+"') OR (data_inicio <= '"+dataInicio+"' and data_fim>= '"+dataFim+"'));";
        String sql = "select data_inicio, data_fim from reserva where numero_quarto ='"+ numero +";'";
        Statement stat = con.createStatement();
        ResultSet rs = stat.executeQuery(sql);
       while(rs.next()){
           System.out.println(rs.getString(" id_reserva") + rs.getString(" cpf_cliente")+ rs.getString(" numero_quarto") + rs.getString(" data_inicio") + rs.getString(" data_fim"));
           return true;
       }
       stat.close();
       return false;
    }
    public void cadastraReserva(Reserva reserva) throws SQLException{
        String sql = "INSERT INTO reserva (cpf_cliente,numero_quarto,data_inicio,data_fim) VALUES("+reserva.getCPF()+"," +reserva.getNumeroQuarto() + ", '"+ reserva.getDataInicio() + "', '"+reserva.getDataFim()+"');";
        
        Statement stat = con.createStatement();
        stat.executeUpdate(sql);
        stat.close();
    }
    
    
}
