/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AventronMD;

import AventronDP.UbicacionDP;
import java.sql.*;
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
    private Connection ubicacionConnection;
    private DriverManager ubicacionDriverManager;
    private PreparedStatement ubicacionPreparedStatement;
    private ResultSet ubicacionResultSet;

    public UbicacionMD() {
    }
    
    public Connection getUbicacionConnection() {
        return ubicacionConnection;
    }

    public void setUbicacionConnection(Connection ubicacionConnection) {
        this.ubicacionConnection = ubicacionConnection;
    }

    public DriverManager getUbicacionDriverManager() {
        return ubicacionDriverManager;
    }

    public void setUbicacionDriverManager(DriverManager ubicacionDriverManager) {
        this.ubicacionDriverManager = ubicacionDriverManager;
    }

    public PreparedStatement getUbicacionPreparedStatement() {
        return ubicacionPreparedStatement;
    }

    public void setUbicacionPreparedStatement(PreparedStatement ubicacionPreparedStatement) {
        this.ubicacionPreparedStatement = ubicacionPreparedStatement;
    }

    public ResultSet getUbicacionResultSet() {
        return ubicacionResultSet;
    }

    public void setUbicacionResultSet(ResultSet ubicacionResultSet) {
        this.ubicacionResultSet = ubicacionResultSet;
    }
   public boolean ValidarUbicacion(UbicacionDP ubicacion)
    {
        try {
            DataSource DsActividad=this.getConnection0();
            Connection con=DsActividad.getConnection();
            Statement st = con.createStatement();
            String Query= "select * from UBICACION where USUARIOCI='" + ubicacion.getUsuarioCI() + "'"
                    + " and UBICACIONLATITUD='" + ubicacion.getUbicacionLatitud() + "'"
                    + " and UBICACIONLONGITUD ='" + ubicacion.getUbicacionLongitud() + "'";
            ResultSet rs = st.executeQuery(Query);
            if (rs.next())
            {
                con.close();
                st.close();
                return true;
            }
            con.close();
            st.close();
            return false;
        } catch (Exception ex) {
            return false;
        }
    }
    public boolean GuardarUbicacion(UbicacionDP ubicacion)
    {
        try {
            DataSource DsActividad=this.getConnection0();
            Connection con=DsActividad.getConnection();
            Statement st = con.createStatement();
            String Query= "select * from UBICACION where USUARIOCI='" + ubicacion.getUsuarioCI() + "'"
                    + " and UBICACIONLATITUD='" + ubicacion.getUbicacionLatitud() + "'"
                    + " and UBICACIONLONGITUD ='" + ubicacion.getUbicacionLongitud() + "'";
            ResultSet rs = st.executeQuery(Query);
            if (!rs.next())
            {
                // no existe
                Query = "insert into UBICACION  (USUARIOCI, UBICACIONLATITUD, UBICACIONLONGITUD, UBICACIONDESCRIPCION) values('"
            + ubicacion.getUsuarioCI() + "','"
            + ubicacion.getUbicacionLatitud() + "','"
            + ubicacion.getUbicacionLongitud() + "','"
            + ubicacion.getUbicacionDescripcion() + "')";
            st.executeUpdate(Query);
            con.close();
            st.close();
            }
            else
            {   con.close();
                st.close();
                return false;
            }
            } catch (Exception ex) 
            {
                return false;
            }
        return true;
    }
   public List<UbicacionDP> CargarUbicaciones(UbicacionDP ubicacion)
    {
        List<UbicacionDP> lista=new LinkedList<UbicacionDP>();
        try {
            DataSource DsActividad=this.getConnection0();
            Connection con=DsActividad.getConnection();
            Statement st = con.createStatement();
            String Query= "select * from UBICACION where USUARIOCI='" + ubicacion.getUsuarioCI() + "'";
            ResultSet rs = st.executeQuery(Query);
            while(rs.next())
            {
                int ubiId=rs.getInt("UBICACIONID");
                int usuarioId=rs.getInt("USUARIOCI");
                float ubilatitud=rs.getFloat("UBICACIONLATITUD");
                float ubilongitud=rs.getFloat("UBICACIONLONGITUD");
                String ubiDescripcion=rs.getString("UBICACIONDESCRIPCION");
                UbicacionDP ubinueva=new UbicacionDP(ubiId, usuarioId, ubilongitud, ubilatitud, ubiDescripcion);
                lista.add(ubinueva);
            }
            con.close();
            st.close();
            
        } catch (Exception ex) {
            
        }
        return lista;
    }
    public UbicacionDP CargarUbicacion(UbicacionDP ubicacion)
    {
       try {
            DataSource DsActividad=this.getConnection0();
            Connection con=DsActividad.getConnection();
            Statement st = con.createStatement();
            String Query= "select * from UBICACION where USUARIOCI='" + ubicacion.getUsuarioCI() + "'";
            ResultSet rs = st.executeQuery(Query);
            while(rs.next())
            {
                int ubiId=rs.getInt("UBICACIONID");
                int usuarioId=rs.getInt("USUARIOCI");
                float ubilatitud=rs.getFloat("UBICACIONLATITUD");
                float ubilongitud=rs.getFloat("UBICACIONLONGITUD");
                String ubiDescripcion=rs.getString("UBICACIONDESCRIPCION");
                UbicacionDP ubinueva=new UbicacionDP(ubiId, usuarioId, ubilongitud, ubilatitud, ubiDescripcion);
                return ubinueva;
            }
            con.close();
            st.close();        
        } catch (Exception ex) {           
        }
        return null;
    }
     public boolean CambiarUbicacion(UbicacionDP ubicacion)
    {
        try {
            DataSource DsActividad=this.getConnection0();
            Connection con=DsActividad.getConnection();
            Statement st = con.createStatement();
            String Query="update UBICACION  SET UBICACIONDESCRIPCION = '"
            + ubicacion.getUbicacionDescripcion() + "' where USUARIOCI='"
            + ubicacion.getUsuarioCI() + "' and UBICACIONLATITUD='"
            + ubicacion.getUbicacionLatitud() + "' and UBICACIONLONGITUD ='"
            + ubicacion.getUbicacionLongitud() + "'";
            st.executeUpdate(Query);
            con.close();
            st.close();
        } catch (Exception ex) {
            return false;
        }
        return true;
    }  
    
     public boolean BorrarUbicacion(UbicacionDP ubicacion)
     {
          try {
            DataSource DsActividad=this.getConnection0();
            Connection con=DsActividad.getConnection();
            Statement st = con.createStatement();
            String Query="delete from UBICACION where USUARIOCI='"
            + ubicacion.getUsuarioCI() + "' and UBICACIONLATITUD='"
            + ubicacion.getUbicacionLatitud() + "' and UBICACIONLONGITUD ='"
            + ubicacion.getUbicacionLongitud() + "'";
            st.executeUpdate(Query);
            con.close();
            st.close();
        } catch (Exception ex) {
            return false;
        }
        return true;
     }
    public List<UbicacionDP> CargarUbicacionesGeneral()
    {
        List<UbicacionDP> lista=new LinkedList<UbicacionDP>();
        try {
            DataSource DsActividad=this.getConnection0();
            Connection con=DsActividad.getConnection();
            Statement st = con.createStatement();
            String Query= "select * from UBICACION";
            ResultSet rs = st.executeQuery(Query);
            while(rs.next())
            {
                int ubiId=rs.getInt("UBICACIONID");
                int usuarioId=rs.getInt("USUARIOCI");
                float ubilatitud=rs.getFloat("UBICACIONLATITUD");
                float ubilongitud=rs.getFloat("UBICACIONLONGITUD");
                String ubiDescripcion=rs.getString("UBICACIONDESCRIPCION");
                UbicacionDP ubinueva=new UbicacionDP(ubiId, usuarioId, ubilongitud, ubilatitud, ubiDescripcion);
                lista.add(ubinueva);
            }
            con.close();
            st.close();
            
        } catch (Exception ex) {
            
        }
        return lista;
    }
    
    public List<UbicacionDP> BuscarUbicacionesPorDescripcion(UbicacionDP ubicacion)
    {
        List<UbicacionDP> lista=new LinkedList<UbicacionDP>();
        try {
            DataSource DsActividad=this.getConnection0();
            Connection con=DsActividad.getConnection();
            Statement st = con.createStatement();
            String Query= "select * from UBICACION where UBICACIONDESCRIPCION like '%"+ubicacion.getUbicacionDescripcion()+"%'";
            ResultSet rs = st.executeQuery(Query);
            while(rs.next())
            {
                int ubiId=rs.getInt("UBICACIONID");
                int usuarioId=rs.getInt("USUARIOCI");
                float ubilatitud=rs.getFloat("UBICACIONLATITUD");
                float ubilongitud=rs.getFloat("UBICACIONLONGITUD");
                String ubiDescripcion=rs.getString("UBICACIONDESCRIPCION");
                UbicacionDP ubinueva=new UbicacionDP(ubiId, usuarioId, ubilongitud, ubilatitud, ubiDescripcion);
                lista.add(ubinueva);
            }
            con.close();
            st.close();
            
        } catch (Exception ex) {
            
        }
        return lista;
    }
    
    private DataSource getConnection0() throws NamingException {
        Context c = new InitialContext();
        return (DataSource) c.lookup("java:comp/env/connection0");
    }
}
