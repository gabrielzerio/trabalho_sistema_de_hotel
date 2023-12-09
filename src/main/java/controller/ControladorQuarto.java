package controller;

import java.sql.SQLException;
import model.Quarto;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControladorQuarto {
    public ControladorQuarto(){
        try {
            listaQuartos = quartosdao.selectQuartos();
        } catch (SQLException ex) {
            Logger.getLogger(ControladorQuarto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private List<Quarto> listaQuartos = new ArrayList<>();
    QuartosDAO quartosdao = new QuartosDAO();

    public boolean existeQuarto(int numero) {
        for (Quarto q : this.listaQuartos) {
            if (q.getNumero() == numero) {
                System.out.println("Já existe");
                return true;
            }
        }
        return false;
    }

    public Quarto pesquisarQuarto(int numero) {
        for (Quarto q : this.listaQuartos) {
            if (q.getNumero() == numero) {
                return q;
            }
        }
        return null;
    }

    public int retornarIndice(int numero) {
        for (Quarto q : this.listaQuartos) {
            if (q.getNumero() == numero) {
                return this.listaQuartos.indexOf(q);
            }
        }
        return -1;
    }

    public boolean salvarQuarto(Quarto q) {

        return false;

    }

    public boolean excluirQuarto(Quarto q) {
        if (q == null) {
            return false;
        }
        if (existeQuarto(q.getNumero())) {
            this.listaQuartos.remove(retornarIndice(q.getNumero()));
            return true;
        } else {
            return false;
        }
    }

    public List<Quarto> retornarTodos() {
        return this.listaQuartos;
    }
    
}
