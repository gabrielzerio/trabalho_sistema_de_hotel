package controller;

import java.sql.SQLException;
import model.Cliente;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
            salvaNoBanco(c);
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

    public List<Cliente> retornarTodos() {
        return this.listaClientes;
    }

    private void salvaNoBanco(Cliente c) {
        System.out.println(c.getCpf() + " " + c.getNome() + " " + c.getTelefone());
        ClienteDAO clientedao = new ClienteDAO();
        try {
            clientedao.insert(c);
        } catch (SQLException ex) {
            Logger.getLogger(ControladorCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
