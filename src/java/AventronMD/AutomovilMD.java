/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AventronMD;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author dagom
 */
public class AutomovilMD {
    Connection conexion = null;

    public Connection conectar()
    {
        try
        {
          Class.forName("oracle.jdbc.driver.OracleDriver");
          conexion = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:Aventron0", "system", "questo");
          System.out.println("Conectado!!");
        }catch(Exception ex)
        {
            System.out.println("ERROR:"+ex.getMessage());
        }
        return conexion;
    }
    
    
    public static void main (String args[])
    {
        AutomovilMD d = new AutomovilMD();
        System.out.println(""+d.conectar());
    }
    
}
