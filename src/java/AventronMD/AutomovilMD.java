/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AventronMD;

import AventronDP.AutomovilDP;
import java.sql.Connection;
import java.sql.DriverManager;
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
    
    }
    
    public void EliminarAutomovil(String placa){
    
    }
    
    public void ActualizarAutomovil(String placa){
    
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
