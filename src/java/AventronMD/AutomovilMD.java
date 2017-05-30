/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AventronMD;

import AventronDP.AutomovilDP;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

    public AutomovilMD() {
    }
    
    public AutomovilDP RecuperarAutomovil( String placa){
        AutomovilDP retorno = new AutomovilDP();
        
        return retorno;
    }
    
    public List<AutomovilDP> RecuperarAutomoviles(){
        List<AutomovilDP> retorno = null;
        
        return retorno;        
    }

    public void InsertarAutomovil(AutomovilDP auto){
        String error = "";
        try {
            DataSource DSAutomovil = this.getConnection0();
            Connection con = DSAutomovil.getConnection();
            Statement st = con.createStatement();
            //actividad = actividad1;
            //String detalle = auto.getAutoPlaca();
            String Query = "select * from automovil where autoPlaca ='" + auto.getAutoPlaca() + "'";
            ResultSet rs = st.executeQuery(Query);
            if (rs.next()) {
                error = "El usuario que desea crear ya existe.";
                con.close();
                st.close();
                Query = "";
            } else {
           Query = "INSERT INTO AUTOMOVIL  (AUTOPLACA , AUTOANIO,  AUTOASIENTOSMAXIMOS, AUTOIMAGEN ) values('"
                    + auto.getAutoPlaca() + "',"
                    + auto.getAutoAnio() + ","
                    + auto.getAutoSientosMaximos() + ",'"
                    + auto.getAutoImagen() + "')";
            error = "Ingreso Exitoso..";
            st.executeUpdate(Query);
            con.close();
            st.close();
            }
        } catch (NamingException ex) {
            /////Logger.getLogger(Actividad.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            //Logger.getLogger(Actividad.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    public void EliminarAutomovil(String placa){
    
    }
    
    public void ActualizarAutomovil(AutomovilDP auto){
         try {
            DataSource DSAutomovil = this.getConnection0();
            Connection con = DSAutomovil.getConnection();
            Statement st = con.createStatement();
            String Query = "UPDATE AUTOMOVIL  SET AUTOANIO= '"+auto.getAutoAnio() 
                    + "',  AUTOASIENTOSMAXIMOS = "+auto.getAutoSientosMaximos() 
                    +", AUTOIMAGEN ="+ auto.getAutoImagen() +"WHERE AUTOPLACA = "+auto.getAutoPlaca() ;
            st.executeUpdate(Query);
            con.close();
            st.close();
            
        } catch (NamingException ex) {
            /////Logger.getLogger(Actividad.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            //Logger.getLogger(Actividad.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
    
    public boolean ValidarAutomovil(AutomovilDP auto){
        boolean resultado = false;
        
        return resultado;
    }
    
    private DataSource getConnection0() throws NamingException {
        Context c = new InitialContext();
        return (DataSource) c.lookup("java:comp/env/connection0");
    }
    
    
}
