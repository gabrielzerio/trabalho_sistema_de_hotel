/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Reserva;

/**
 *
 * @author Gabriel
 */
public class ControladorReserva {
    private List<Reserva> listaReservas = new ArrayList<>();
    ReservaDAO reservaDAO = new ReservaDAO();
    
    public void novaReserva(Reserva reserva){
        
        try {
            reservaDAO.cadastraReserva(reserva);//reserva.getNumeroQuarto(), reserva.getDataInicio(), reserva.getDataFim()
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
        }
    }
}
