/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Cliente;
import model.Reserva;
import view.MenuReserva;

/**
 *
 * @author Gabriel
 */
public class ReservaDAO {

    private Connection con;

    public ReservaDAO() {
        con = Database.getInstance().getConnection();
    }

    /* public List<Date[]> buscaDatas(int numero) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String sql = "select data_inicio, data_fim from reserva where numero_quarto = '" + numero + "'";
            Statement stat = con.createStatement();
            ResultSet rs = stat.executeQuery(sql);

            List<Date[]> reservas = new ArrayList<>();
            while (rs.next()) {
                Date dataInicio;
                try {
                    dataInicio = dateFormat.parse(rs.getString("data_inicio"));
                    Date dataFim = dateFormat.parse(rs.getString("data_fim"));
                    reservas.add(new Date[]{dataInicio, dataFim});
                } catch (ParseException ex) {
                    Logger.getLogger(ReservaDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            stat.close();
            return reservas;
        } catch (SQLException ex) {
            Logger.getLogger(MenuReserva.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    } */
    public void cadastraReserva(Reserva reserva) throws SQLException {
        String sql = "INSERT INTO reserva (cpf_cliente,numero_quarto,data_inicio,data_fim) VALUES(" + reserva.getCPF() + "," + reserva.getNumeroQuarto() + ", '" + reserva.getDataInicio() + "', '" + reserva.getDataFim() + "');";

        Statement stat = con.createStatement();
        stat.executeUpdate(sql);
        stat.close();
    }

    public boolean existeReservaNoPeriodo(int numeroQuarto, String dataInicio, String dataFim) {
        try {
            String sql = "SELECT COUNT(*) FROM reserva "
                    + "WHERE numero_quarto = ? "
                    + "AND ((data_inicio >= ? AND data_inicio <= ?) OR (data_fim >= ? AND data_fim <= ?) OR (data_inicio <= ? AND data_fim >= ?))";

            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, numeroQuarto);
            statement.setString(2, dataInicio);
            statement.setString(3, dataFim);
            statement.setString(4, dataInicio);
            statement.setString(5, dataFim);
            statement.setString(6, dataInicio);
            statement.setString(7, dataFim);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            int count = resultSet.getInt(1);
            System.out.println(count);

            statement.close();
            return count > 0;
        } catch (SQLException ex) {
            Logger.getLogger(MenuReserva.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public List<Reserva> buscaReservas(int numeroQuarto, String CPF, int opcao) {
        try {
            List<Reserva> reservas = new ArrayList();
            String sql = "";
            if (opcao == 1) {
                sql = "select * from reserva where numero_quarto = ?";
            } else if(opcao == 2) {
                sql = "select * from reserva where cpf_cliente = ?";
            }
            PreparedStatement statement = con.prepareStatement(sql);
            if (opcao == 1) {
                statement.setInt(1, numeroQuarto);
            } else if (opcao == 2) {
                statement.setString(1, CPF);
            }
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Reserva reserva = new Reserva(rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getInt(1));
                reservas.add(reserva);
            }
            return reservas;
        } catch (SQLException ex) {
            Logger.getLogger(MenuReserva.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public boolean cancelaReserva(int idReserva){
        try {
            String sql = "delete from reserva where id_reserva = ?;";

            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, idReserva);
            statement.executeUpdate();
            statement.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(MenuReserva.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
