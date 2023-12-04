package model;

public class Quarto {

    private int numero;
    private boolean reservado;
    private int capacidade;

    public Quarto(int numero, int capacidade) {
        this.numero = numero;
        this.capacidade = capacidade;
    }

    public int getNumero() {
        return numero;
    }

    public boolean getReservado() {
        return reservado;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setReservado(boolean reservado) {
        this.reservado = reservado;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

}
