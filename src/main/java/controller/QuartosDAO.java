/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Quarto;

/**
 *
 * @author Gabriel
 */
public class QuartosDAO {

    private Connection con;
    public QuartosDAO() {
        con = Database.getInstance().getConnection();
    }

    public List<Quarto> selectQuartos() throws SQLException {
        List<Quarto> quartos = new ArrayList();
        
        String sql = "select * from quartos";
        Statement stat = con.createStatement();
        ResultSet rs = stat.executeQuery(sql);

        while (rs.next()) {
            Quarto quarto = new Quarto();
            quarto.setNumero(rs.getInt("numero"));
            quarto.setCapacidade(rs.getInt(3));
            quarto.setReservado(true);
            quartos.add(quarto);
        }
        return quartos;
    }

}
