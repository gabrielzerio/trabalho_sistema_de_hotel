package model;

import java.util.Date;
import java.util.List;

public class Reserva {

        private String CPF;
        private int numeroQuarto;
        private String dataInicio;
        private String dataFim;
        private int idReserva;

    public Reserva(String CPF, int numeroQuarto, String dataInicio, String dataFim) {
        this.CPF = CPF;
        this.numeroQuarto = numeroQuarto;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    public Reserva(String CPF, int numeroQuarto, String dataInicio, String dataFim, int idReserva) {
        this.CPF = CPF;
        this.numeroQuarto = numeroQuarto;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.idReserva = idReserva;
    }

    public Reserva(String dataInicio, String dataFim) {
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    
        
        
    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public int getNumeroQuarto() {
        return numeroQuarto;
    }

    public void setNumeroQuarto(int numeroQuarto) {
        this.numeroQuarto = numeroQuarto;
    }

    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getDataFim() {
        return dataFim;
    }

    public void setDataFim(String dataFim) {
        this.dataFim = dataFim;
    }

    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }
        
        
        
}
