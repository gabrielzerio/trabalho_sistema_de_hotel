
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ControladorQuarto {

    private List<Quarto> listaQuartos = new ArrayList<>();

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
        
        if (q == null) {
            return false;
        }
        if (existeQuarto(q.getNumero())) {
            this.listaQuartos.set(retornarIndice(q.getNumero()), q);
            return true;
        } else {
            this.listaQuartos.add(q);
            return true;
        }
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

    public List<Quarto> retornarTodos(){
        return this.listaQuartos;
    }
    
}

