/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Gabriel
 */
public class Banco {

    public static void main(String[] args) throws IOException
    {
        Connection con = null;
      try
      {
        // create a database connection
        con = Database.getInstance().getConnection();
        Statement statement = con.createStatement();
        statement.setQueryTimeout(30);  // set timeout to 30 sec.
        // statement.executeUpdate("drop table if exists person");
        // statement.executeUpdate("create table person (id integer, name string)");
        // statement.executeUpdate("insert into person values(1, 'leo')");
        // statement.executeUpdate("insert into person values(2, 'yui')");
        ResultSet rs = statement.executeQuery("select * from cliente");
        while(rs.next())
        {
          // read the result set
          System.out.println("nome = " + rs.getString("nome"));
          System.out.println("cpf = " + rs.getInt("cpf"));
        }
          JOptionPane.showMessageDialog(null, "ALL FINE");
      }
      catch(SQLException e)
      {
        // if the error message is "out of memory",
        // it probably means no database file is found
        System.err.println(e.getMessage());
          JOptionPane.showMessageDialog(null, "ERROR" + e.getMessage());
      }
      finally
      {
        try
        {
          if(con != null)
            con.close();
        }
        catch(SQLException e)
        {
          // connection close failed.
          System.err.println(e.getMessage());
        }
      }
    }
}
