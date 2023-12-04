package controller;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
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
}
