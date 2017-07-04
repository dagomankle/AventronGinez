/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AventronMD;

import AventronDP.AutomovilDP;
import AventronDP.RutaDP;
import AventronDP.UbicacionDP;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
    ArrayList<RutaDP> nueva = new ArrayList();
    ArrayList<String> nueva2 = new ArrayList();

    public RutaMD(RutaDP rutaDP) {
        this.rutaDP = rutaDP;
    }

    public RutaDP recuperarRuta(String nombre) {
        RutaDP rutaDPL = new RutaDP();
        rutaDPL.setRutaNombre(nombre);
        try {
            DataSource DSAutomovil = this.getAventronPool();
            this.con = DSAutomovil.getConnection();
            this.stm = this.con.createStatement();
            this.cadena = "SELECT * FROM RUTA WHERE RUTANOMBRE = '" + rutaDPL.getRutaNombre()
                    + "'";
            rs = this.stm.executeQuery(this.cadena);
            while (rs.next()) {
                String rutaNombre = rs.getString("RUTANOMBRE");
                String rutaDescripcion = rs.getString("RUTADESCRIPCION");
                LinkedList<UbicacionDP> ubicaciones = this.obtenerUbicaciones(rutaNombre);

                rutaDPL = new RutaDP(rutaNombre, rutaDescripcion, ubicaciones);
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
    
    public LinkedList<UbicacionDP> obtenerUbicaciones(String nom){
        String nombres = "";
        RutaDP rutita = new RutaDP();
        try {
            DataSource DSAutomovil = this.getAventronPool();
            this.con = DSAutomovil.getConnection();
            this.stm = this.con.createStatement();
            this.cadena = "SELECT * FROM FORM WHERE RUTANOMBRE = '" + nom
                    + "'";
            rs = this.stm.executeQuery(this.cadena);
            while (rs.next()) {
                String rutaNombre = rs.getString("RUTANOMBRE");
                String ubicacionNombre = rs.getString("UBICACIONID");

                nombres = nombres+ubicacionNombre+", ";
            }
            
            if(nombres != "")
                nombres = nombres.substring(0, nombres.length() -2);
            con.close();
            this.stm.close();

        } catch (NamingException ex) {
            /////Logger.getLogger(Actividad.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            //Logger.getLogger(Actividad.class.getName()).log(Level.SEVERE, null, ex);    }   
        }        
        
        rutita.setRutaNombresUbicaciones(nombres);
 
        return rutita.determinarRutaUbicaciones();
    }
    
    public LinkedList<RutaDP> recuperarRutas() throws NamingException, SQLException {
        LinkedList<RutaDP> retorno1 = new LinkedList<>();
        RutaDP rutaDPL = new RutaDP();
        ArrayList codigos = consultaPorUsuario(this.rutaDP.getUsuarioCI());
        for (Object i: codigos){
            String b = i.toString();
            rutaDPL=this.recuperarRuta(b);
            rutaDPL.setUsuarioCI(this.rutaDP.getUsuarioCI());
            retorno1.add(rutaDPL);
        }
        
        return retorno1;
    }

    /*public String conversorDate(){
        Date hola = new Date(this.rutaDP.getAutoAnio().getTime());
        String[] elementos = hola.toString().split(" ");
        
        return elementos[5]+ "-"+ elementos[1] +"-"+elementos[2];        
    }*/
    public void insertarRuta() {

        try {
            DataSource DSRuta = this.getAventronPool();
            this.con = DSRuta.getConnection();
            this.stm = this.con.createStatement();
            cadena = "INSERT INTO RUTA  (RUTANOMBRE, RUTADESCRIPCION) values('"
                    + this.rutaDP.getRutaNombre() + "','"
                    + this.rutaDP.getRutaDescripcion()
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

    public void insertarUbicacionesDebiles() {
        
        for (UbicacionDP item : this.rutaDP.getRutaUbicaciones()) {
            try {
                DataSource DSRuta = this.getAventronPool();
                this.con = DSRuta.getConnection();
                this.stm = this.con.createStatement();
                cadena = "INSERT INTO FORMA  (RUTANOMBRE, UBICACIONID) values('"
                        + this.rutaDP.getRutaNombre() + "','"
                        + item.getCodigo()
                        + ")";

                this.stm.executeUpdate(this.cadena);
                this.con.close();
                this.stm.close();

            } catch (NamingException ex) {
                /////Logger.getLogger(Actividad.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                //Logger.getLogger(Actividad.class.getName()).log(Level.SEVERE, null, ex);
            }  
        }  
    }

    public void eliminarRuta() {
        try {
            this.eliminarUbicacionesDebiles();

            DataSource DSRuta = this.getAventronPool();
            this.con = DSRuta.getConnection();
            this.stm = this.con.createStatement();
            this.cadena = "DELETE FROM RUTA WHERE NOMBRERUTA = " + this.rutaDP.getRutaNombre();
            this.stm.executeUpdate(this.cadena);
            con.close();
            this.stm.close();

        } catch (NamingException ex) {
            /////Logger.getLogger(Actividad.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            //Logger.getLogger(Actividad.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminarUbicacionesDebiles() {

    }

    public boolean validarRuta() {
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
            this.cadena = "SELECT * from RUTA WHERE RUTANOMBRE='" + this.rutaDP.getRutaNombre() + "'";
            this.rs = this.stm.executeQuery(this.cadena);
            if (this.rs != null) {
                resultado = false;
            }
            this.con.close();
            this.stm.close();

        } catch (NamingException ex) {
            /////Logger.getLogger(Actividad.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            //Logger.getLogger(Actividad.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }

    public RutaDP ConsultaporParametros2(String nombre) {
        try {
            DataSource DSAutomovil = this.getAventronPool();
            this.con = DSAutomovil.getConnection();
            this.stm = this.con.createStatement();

            this.cadena = "SELECT * FROM RUTA WHERE RUTANOMBRE = '" + nombre
                    + "'";
            rs = this.stm.executeQuery(this.cadena);
            while (rs.next()) {
                String rutanom = rs.getString("RUTANOMBRE");
                String rutadesc = rs.getString("RUTADESCRIPCION");
                LinkedList<UbicacionDP> ubicaciones = new LinkedList<>();
                rutaDP = new RutaDP(rutanom, rutadesc, ubicaciones);
            }
            con.close();
            this.stm.close();
            return rutaDP;

        } catch (NamingException ex) {
            /////Logger.getLogger(Actividad.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            //Logger.getLogger(Actividad.class.getName()).log(Level.SEVERE, null, ex);    }   
        }
        return rutaDP;
    }

    public ArrayList consultaPorUsuario(String userCI) throws NamingException, SQLException {
        DataSource DSUsuario = this.getAventronPool();
        Connection con = DSUsuario.getConnection();
        Statement st = con.createStatement();
        String cadena = "";
        cadena = "select RUTANOMBRE from FORMA where UBICACIONID in (select UBICACIONID from UBICACION where CIUSUARIO= '"+userCI+"' group by UBICACIONID)GROUP BY RUTANOMBRE";
        ResultSet rs = st.executeQuery(cadena);
        while (rs.next()) {
            String rutanom = rs.getString("RUTANOMBRE");
            //String rutadesc = rs.getString("RUTADESCRIPCION");
            //rutaDP = new RutaDP(rutanom, rutadesc, null);
            this.nueva2.add(rutanom);
        }
        con.close();
        st.close();
        return this.nueva2;
    }

    private DataSource getAventronPool() throws NamingException {
        Context c = new InitialContext();
        return (DataSource) c.lookup("java:comp/env/AventronPool");
    }

}
