package controller;

import java.sql.SQLException;
import model.Cliente;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ControladorCliente {

    public ControladorCliente() {

    }
    private ClienteDAO clientedao = new ClienteDAO();
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
            int opt = JOptionPane.showConfirmDialog(null, "Esse usuario já está cadastrado, você deseja alterar os dados de telefone e nome?", "Atenção", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (opt == 0) {
                //this.listaClientes.set(retornarIndice(c.getCpf()), c);
                modificaNoBanco(c);
            }
            return true;
        } else {
            salvaNoBanco(c);
            return true;
        }
    }

    public boolean excluirCliente(Cliente c) {
        if (c == null) {
            return false;
        }
        if (existeCliente(c.getCpf())) {
            //this.listaClientes.remove(retornarIndice(c.getCpf()));
            excluirNoBanco(c);
            return true;
        } else {
            return false;
        }
    }

    public List<Cliente> retornarTodos() {
        try {
            listaClientes = clientedao.selectAll();
        } catch (SQLException ex) {
            Logger.getLogger(ControladorCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.listaClientes;
    }

    private void salvaNoBanco(Cliente c) {
        System.out.println(c.getCpf() + " " + c.getNome() + " " + c.getTelefone());
        try {
            clientedao.insert(c);
        } catch (SQLException ex) {
            Logger.getLogger(ControladorCliente.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            retornarTodos();
        }
    }

    private void modificaNoBanco(Cliente c) {
        try {
            clientedao.alteraCliente(c);
        } catch (SQLException ex) {
            Logger.getLogger(ControladorCliente.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            retornarTodos();
        }
    }
    
    private void excluirNoBanco(Cliente c){
        try {
            clientedao.excluiCliente(c);
        } catch (SQLException ex) {
            Logger.getLogger(ControladorCliente.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            retornarTodos();
        }
    }
}
