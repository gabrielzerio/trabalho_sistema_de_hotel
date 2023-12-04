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
       String sql = "insert into cliente values("+cliente.getCpf()+", '"+cliente.getNome()+ "', "+cliente.getTelefone()+");";
       stat.executeUpdate(sql);
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
       stat.close();
       return clientes;
    }
    
    public void alteraCliente(Cliente c) throws SQLException{
        String sql = "UPDATE cliente set nome = "+ c.getNome() + ", telefone = "+ c.getTelefone() + " where cpf = "+ c.getCpf() + ";";
        Statement stat = con.createStatement();
        stat.executeUpdate(sql);
        stat.close();
    }
    
    public void excluiCliente(Cliente c) throws SQLException{
        String sql = "DELETE FROM cliente where cpf = " + c.getCpf();
        Statement stat = con.createStatement();
        stat.executeUpdate(sql);
        stat.close();
    }
}
