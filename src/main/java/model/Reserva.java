package model;

import java.util.Date;
import java.util.List;

public class Reserva {

    private List<Cliente> cliente[];
    private List<Quarto> quarto[];
    private Date dataInicio;
    private Date dataFim;

    public Reserva(List<Cliente>[] cliente, List<Quarto>[] quarto, Date dataInicio, Date dataFim) {
        this.cliente = cliente;
        this.quarto = quarto;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }
        
    public List<Cliente>[] getCliente() {
        return cliente;
    }

    public List<Quarto>[] getQuarto() {
        return quarto;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setCliente(List<Cliente>[] cliente) {
        this.cliente = cliente;
    }

    public void setQuarto(List<Quarto>[] quarto) {
        this.quarto = quarto;
    }

    public Date getDataFim() {
        return dataFim;
    }

}
