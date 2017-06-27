/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AventronMD;

import AventronDP.UbicacionDP;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author JUANCARLOS
 */
public class UbicacionMD {
     UbicacionDP ubicaciondp; 
    Connection con; 
    Statement stm; 
    String cadena; 

    public UbicacionMD(UbicacionDP ubicaciondp) {
        this.ubicaciondp = ubicaciondp;
    }
    public void guardar()
    {
        try {
            DataSource dataSource = this.getGetLabJDBC();
            con = dataSource.getConnection();
            stm = con.createStatement(); 
            cadena = "insert into UBICACION values ( '" + ubicaciondp.getCodigo()+ "' , '"+ubicaciondp.getCiUsuario()+"', " + ubicaciondp.getUbiLongitud() + " , " + 
                    ubicaciondp.getUbiLatitud() +" , '" + ubicaciondp.getUbiDescripcion() + "' )";
            
            System.out.println("Cadena SQL: " + cadena);
            stm.executeUpdate(cadena);
            
        } catch (Exception ex) {
            System.out.println(">>>>"+ex.getMessage());
        }
    }

    public void borrar()
    {
        try {
            DataSource dataSource = this.getGetLabJDBC();
            con = dataSource.getConnection();
            stm = con.createStatement(); 
            cadena = "delete from UBICACION where UBICODIGO='" + ubicaciondp.getCodigo()+ "'";
            
            System.out.println("Cadena SQL: " + cadena);
            stm.executeUpdate(cadena);
            
        } catch (Exception ex) {
            System.out.println(">>>>"+ex.getMessage());
        }
    }

    public void cambiar()
    {
        try {
            DataSource dataSource = this.getGetLabJDBC();
            con = dataSource.getConnection();
            stm = con.createStatement(); 
            cadena = "update ubicaciones set longitud="+ubicaciondp.getUbiLongitud()+
                    ", latitud="+ubicaciondp.getUbiLatitud()+", descripcion='"+ubicaciondp.getUbiDescripcion()+
                    "'  where codigo='" + ubicaciondp.getCodigo()+ "'";
            
            System.out.println("Cadena SQL: " + cadena);
            stm.executeUpdate(cadena);
            
        } catch (Exception ex) {
            System.out.println(">>>>"+ex.getMessage());
        }
    }
    
    public List<UbicacionDP> consultarubicaciones()
    {
        List<UbicacionDP> lista=new ArrayList<>();
        try {
            DataSource dataSource = this.getGetLabJDBC();
            con = dataSource.getConnection();
            stm = con.createStatement(); 
            cadena = "select * from ubicaciones";
            ResultSet rs=stm.executeQuery(cadena);
            System.out.println("Cadena SQL: " + cadena);
            while (rs.next()) {
              UbicacionDP nuevo=new UbicacionDP();
              nuevo.setCodigo(rs.getString("CODIGO"));
              nuevo.setUbiLongitud(rs.getDouble(1));
              nuevo.setUbiLatitud(rs.getDouble(2));
              nuevo.setUbiDescripcion(rs.getString(3));
              nuevo.setCiUsuario(rs.getString(4));
              System.out.println("\n"+nuevo.getCodigo()+" >> "+nuevo.getUbiDescripcion()+" >> "+nuevo.getUbiLatitud()+" >> "+nuevo.getUbiLongitud()+" >> "+nuevo.getCiUsuario());
              lista.add(nuevo);
            }
            
        } catch (Exception ex) {
            System.out.println(">>>>"+ex.getMessage());
        }
        System.out.println(">>>>>>>>>>>>>"+lista.size());
        return lista;
    }

    private DataSource getGetLabJDBC() throws NamingException {
       Context c = new InitialContext();
        return (DataSource) c.lookup("java:comp/env/AventronPool");
    }
}
