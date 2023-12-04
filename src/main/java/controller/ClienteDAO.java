package controller;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Cliente;


public class ClienteDAO {
   private Connection con;

    public ClienteDAO() {
        con = Database.getInstance().getConnection();
    }
   
    
    
    public void insert (Cliente cliente) throws SQLException{
       Statement stat = con.createStatement();
       stat.executeUpdate("insert into cliente values ("+cliente.getCpf() + ","+ cliente.getNome() + ","+cliente.getTelefone()+")");
       stat.close(); 
   } 
    public ArrayList<Cliente> selectAll() throws SQLException{

       ArrayList<Cliente> clientes = new ArrayList<>();
        String sql = "select * from cliente";
       Statement stat = con.createStatement();
       ResultSet rs = stat.executeQuery(sql);
       while(rs.next()){
          Cliente cliente;            
          clientes.add( cliente = new Cliente(rs.getString("cpf"), rs.getString("nome"), rs.getString("telefone")));
       }
       return clientes;
    }
}
