/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AventronMD;

import AventronDP.AutomovilDP;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author dagom
 */
public class AutomovilMD {

    private AutomovilDP automovilDP;
    private Connection con;
    private Statement stm;
    private String cadena;
    private ResultSet rs; 

    public AutomovilMD(AutomovilDP automovilDP) {
        this.automovilDP = automovilDP;
    }
    
    public AutomovilDP recuperarAutomovil(){
         try {
            DataSource DSAutomovil = this.getAventronPool();
            this.con = DSAutomovil.getConnection();
            this.stm = this.con.createStatement();
            this.cadena = "SELECT * FROM AUTOMOVIL WHERE AUTOPLACA = '"+automovilDP.getAutoPlaca()
                    + "'";
            rs = this.stm.executeQuery(this.cadena);
            con.close();
            this.stm.close();
            
            while (rs.next()) {
                String autoPlaca = rs.getString("AUTOPLACA");
                String usuarioCI = rs.getString("USUARIOCI");
                Date autoAnio = rs.getDate("AUTOANIO");
                int autoAsientosMaximos = rs.getInt("AUTOASIENTOSMAXIMOS");
                Blob autoImagen = rs.getBlob("AUTOIMAGEN");
                /*System.out.println(coffeeName + "\t" + supplierID +
                                   "\t" + price + "\t" + sales +
                                   "\t" + total);*/
                automovilDP = new AutomovilDP(autoPlaca,usuarioCI,autoAnio, autoAsientosMaximos,autoImagen);
            }
            
        } catch (NamingException ex) {
            /////Logger.getLogger(Actividad.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            //Logger.getLogger(Actividad.class.getName()).log(Level.SEVERE, null, ex);    }   
        }
       
        return automovilDP;
    }
    
    public List<AutomovilDP> recuperarAutomoviles(AutomovilDP automovilDP){
        List<AutomovilDP> retorno = null;
        
        return retorno;        
    }

    public void insertarAutomovil(){

        try {
            DataSource DSAutomovil = this.getAventronPool();
            this.con = DSAutomovil.getConnection();
           cadena = "INSERT INTO AUTOMOVIL  (AUTOPLACA , USUARIOCI, AUTOANIO,  AUTOASIENTOSMAXIMOS, AUTOIMAGEN ) values('"
                    + automovilDP.getAutoPlaca() + "','"
                    +automovilDP.getUsuarioCI() +"', to_date('"
                    + automovilDP.getAutoAnio() + "','yyyy-mm-dd'),"
                    + automovilDP.getAutoAsientosMaximos() + ",'"
                    + automovilDP.getAutoImagen() + "')";
            this.stm.executeUpdate(this.cadena);
            this.con.close();
            this.stm.close();

        } catch (NamingException ex) {
            /////Logger.getLogger(Actividad.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            //Logger.getLogger(Actividad.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    public void eliminarAutomovil(String placa){
         try {
            DataSource DSAutomovil = this.getAventronPool();
            this.con = DSAutomovil.getConnection();
            this.stm = this.con.createStatement();
            this.cadena = "DELTE FROM AUTOMOVIL WHERE AUTOPLACA = " + automovilDP.getAutoPlaca() ;
            this.stm.executeUpdate(this.cadena);
            con.close();
            this.stm.close();
            
        } catch (NamingException ex) {
            /////Logger.getLogger(Actividad.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            //Logger.getLogger(Actividad.class.getName()).log(Level.SEVERE, null, ex);
        }      
    }
    
    public void actualizarAutomovil(){
        
         try {
            DataSource DSAutomovil = this.getAventronPool();
            this.con = DSAutomovil.getConnection();
            this.stm = this.con.createStatement();
            this.cadena = "UPDATE AUTOMOVIL  SET AUTOANIO= '"+automovilDP.getAutoAnio() 
                    + "',  AUTOASIENTOSMAXIMOS = "+automovilDP.getAutoAsientosMaximos() 
                    +", AUTOIMAGEN ="+ automovilDP.getAutoImagen() +"WHERE AUTOPLACA = "+automovilDP.getAutoPlaca() ;
            this.stm.executeUpdate(this.cadena);
            con.close();
            this.stm.close();
            
        } catch (NamingException ex) {
            /////Logger.getLogger(Actividad.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            //Logger.getLogger(Actividad.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
    
    public boolean validarAutomovil(AutomovilDP auto){
        boolean resultado = false;
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
        return resultado;
    }
    
    private DataSource getAventronPool() throws NamingException {
        Context c = new InitialContext();
        return (DataSource) c.lookup("java:comp/env/AventronPool");
    }
    
    
}
