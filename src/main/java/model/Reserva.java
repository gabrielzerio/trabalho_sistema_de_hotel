package model;

import java.util.Date;
import java.util.List;

public class Reserva {

        private String CPF;
        private int numeroQuarto;
        private Date dataInicio;
        private Date dataFim;

    public Reserva(String CPF, int numeroQuarto, Date dataInicio, Date dataFim) {
        this.CPF = CPF;
        this.numeroQuarto = numeroQuarto;
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

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }
        
        
        
}
