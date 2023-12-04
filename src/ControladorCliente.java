import java.util.ArrayList;
import java.util.List;

public class ControladorCliente {

    private List<Cliente> listaClientes = new ArrayList<>();

    public boolean existeCliente(String CPF) {
        for (Cliente c : this.listaClientes) {
            if (c.getCpf().equals(CPF)) {
                System.out.println("Já existe");
                return true;
            }
        }
        return false;
    }

    public Cliente pesquisarCliente(String CPF) {
        for (Cliente c : this.listaClientes) {
            if (c.getCpf().equals(CPF)) {
                return c;
            }
        }
        return null;
    }

    public int retornarIndice(String CPF) {
        for (Cliente c : this.listaClientes) {
            if (c.getCpf().equals(CPF)) {
                return this.listaClientes.indexOf(c);
            }
        }
        return -1;
    }

    public boolean salvarCliente(Cliente c) {
        
        if (c == null) {
            return false;
        }
        if (existeCliente(c.getCpf())) {
            this.listaClientes.set(retornarIndice(c.getCpf()), c);
            return true;
        } else {
            this.listaClientes.add(c);
            return true;
        }
    }

    public boolean excluirCliente(Cliente c) {
        if (c == null) {
            return false;
        }
        if (existeCliente(c.getCpf())) {
            this.listaClientes.remove(retornarIndice(c.getCpf()));
            return true;
        } else {
            return false;
        }
    }

    public List<Cliente> retornarTodos(){
        return this.listaClientes;
    }
    
}


