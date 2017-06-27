/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



package AventronMD;

import AventronDP.RutaDP;
import AventronDP.UbicacionDP;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.LinkedList;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;




/**
 *
 * @author dagom
 */
public class RutaMD {

    private RutaDP rutaDP;
    private Connection con;
    private Statement stm;
    private String cadena;
    private ResultSet rs; 
    private String error;

    public RutaMD(RutaDP rutaDP) {
        this.rutaDP = rutaDP;
    }

    public RutaDP recuperarRuta(){
        RutaDP rutaDPL = new RutaDP();
         try {
            DataSource DSAutomovil = this.getAventronPool();
            this.con = DSAutomovil.getConnection();
            this.stm = this.con.createStatement();
            this.cadena = "SELECT * FROM RUTA WHERE RUTANOMBRE = '"+rutaDP.getRutaNombre()
                    + "'";
            rs = this.stm.executeQuery(this.cadena);
            while (rs.next()) {
                String rutaNombre = rs.getString("RUTANOMBRE");
                String rutaDescripcion = rs.getString("RUTADESCRIPCION");
                LinkedList<UbicacionDP> ubicaciones = recuperarUbicaciones();
                
                rutaDPL = new RutaDP(rutaNombre,rutaDescripcion,ubicaciones );
            }
            con.close();
            this.stm.close();
                       
        } catch (NamingException ex) {
            /////Logger.getLogger(Actividad.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            //Logger.getLogger(Actividad.class.getName()).log(Level.SEVERE, null, ex);    }   
        }
       
        return rutaDPL;
    }
    
    public LinkedList<UbicacionDP> recuperarUbicaciones(){
        LinkedList<UbicacionDP> ubicaciones =new LinkedList<>();
        
        
        return ubicaciones;
    }
    
    
    public LinkedList<RutaDP> recuperarRutas(){
         LinkedList<RutaDP> retorno1= new LinkedList<>();
         RutaDP rutaDPL = new RutaDP();
         try {
            DataSource DSAutomovil = this.getAventronPool();
            this.con = DSAutomovil.getConnection();
            this.stm = this.con.createStatement();
            this.cadena = "SELECT * FROM AUTOMOVIL WHERE CIUSUARIO = '"+rutaDP.getUsuarioCI()
                    + "'";
            rs = this.stm.executeQuery(this.cadena);
            while (rs.next()) {
                String rutaNombre = rs.getString("RUTANOMBRE");
                String rutaDescripcion = rs.getString("RUTADESCRIPCION");
                LinkedList<UbicacionDP> ubicaciones = recuperarUbicaciones();
                
                rutaDPL = new RutaDP(rutaNombre,rutaDescripcion,ubicaciones );
                retorno1.add(rutaDPL);
            }
            con.close();
            this.stm.close();
                       
        } catch (NamingException ex) {
            /////Logger.getLogger(Actividad.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            //Logger.getLogger(Actividad.class.getName()).log(Level.SEVERE, null, ex);    }   
        }        
        return retorno1;        
    }

    /*public String conversorDate(){
        Date hola = new Date(this.rutaDP.getAutoAnio().getTime());
        String[] elementos = hola.toString().split(" ");
        
        return elementos[5]+ "-"+ elementos[1] +"-"+elementos[2];        
    }*/
    
    public void insertarRuta(){

        try {
            DataSource DSAutomovil = this.getAventronPool();
            this.con = DSAutomovil.getConnection();
            this.stm = this.con.createStatement();
                 cadena = "INSERT INTO RUTA  (RUTANOMBRE, RUTADESCRIPCION) values('"
                        + this.rutaDP.getRutaNombre() + "','"
                        +this.rutaDP.getUsuarioCI() 
                        + ")";               
           
           
            this.stm.executeUpdate(this.cadena);
            this.con.close();
            this.stm.close();
            
            this.insertarUbicacionesDebiles();

        } catch (NamingException ex) {
            /////Logger.getLogger(Actividad.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            //Logger.getLogger(Actividad.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    public void insertarUbicacionesDebiles(){
        
    }
    
    public void eliminarRuta(){
         try {
            this.eliminarUbicacionesDebiles();
             
            DataSource DSAutomovil = this.getAventronPool();
            this.con = DSAutomovil.getConnection();
            this.stm = this.con.createStatement();
            this.cadena = "DELETE FROM RUTA WHERE NOMBRERUTA = " + this.rutaDP.getRutaNombre() ;
            this.stm.executeUpdate(this.cadena);
            con.close();
            this.stm.close();
            
        } catch (NamingException ex) {
            /////Logger.getLogger(Actividad.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            //Logger.getLogger(Actividad.class.getName()).log(Level.SEVERE, null, ex);
        }      
    }
    
    public void eliminarUbicacionesDebiles(){
        
    }

    
    public boolean validarRuta(){
        boolean resultado = true;
           /* this.stm = this.con.createStatement();
            //actividad = actividad1;
            //String detalle = auto.getAutoPlaca();
            this.cadena = "select * from automovil where autoPlaca ='" + auto.getAutoPlaca() + "'";
            ResultSet rs = this.stm.executeQuery(this.cadena);
            if (rs.next()) {
                error = "El usuario que desea crear ya existe.";
                con.close();
                this.stm.close();
                this.cadena = "";
            } else {*/    
         try {
            DataSource DSAutomovil = this.getAventronPool();
            this.con = DSAutomovil.getConnection();
            this.stm = this.con.createStatement();
            this.cadena = "SELECT * from RUTA WHERE RUTANOMBRE='" + this.rutaDP.getRutaNombre() + "'" ;
            this.rs = this.stm.executeQuery(this.cadena);
            if (this.rs != null)
                resultado = false;            
            this.con.close();
            this.stm.close();
            
        } catch (NamingException ex) {
            /////Logger.getLogger(Actividad.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            //Logger.getLogger(Actividad.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return resultado;
    }    
    
    private DataSource getAventronPool() throws NamingException {
        Context c = new InitialContext();
        return (DataSource) c.lookup("java:comp/env/AventronPool");
    }    
    
}
