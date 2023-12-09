/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
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
    public boolean existeReservaNoPeriodo(int numeroQuarto, String dataInicio, String dataFim){
        return reservaDAO.existeReservaNoPeriodo(numeroQuarto, dataInicio, dataFim);
    }
    public List<Reserva> buscaReservas(int numeroQuarto , String CPF, int opcao){
        return reservaDAO.buscaReservas(numeroQuarto, CPF, opcao);
    }
    
    /*public List<Date[]> buscaDatas(int numeroQuarto){
        return reservaDAO.buscaDatas(numeroQuarto);
    }*/

    public boolean cancelaReserva(int IDReserva) {
        return reservaDAO.cancelaReserva(IDReserva);
    }
}
