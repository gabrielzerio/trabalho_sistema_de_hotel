/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Gabriel
 */
public class Banco {

    public static void main(String[] args)
    {
        Connection con = null;
      try
      {
        // create a database connection
        con = Database.getInstance().getConnection();
        Statement statement = con.createStatement();
        statement.setQueryTimeout(30);  // set timeout to 30 sec.

        statement.executeUpdate("drop table if exists person");
        statement.executeUpdate("create table person (id integer, name string)");
        statement.executeUpdate("insert into person values(1, 'leo')");
        statement.executeUpdate("insert into person values(2, 'yui')");
        ResultSet rs = statement.executeQuery("select * from person");
        while(rs.next())
        {
          // read the result set
          System.out.println("name = " + rs.getString("name"));
          System.out.println("id = " + rs.getInt("id"));
        }
      }
      catch(SQLException e)
      {
        // if the error message is "out of memory",
        // it probably means no database file is found
        System.err.println(e.getMessage());
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
