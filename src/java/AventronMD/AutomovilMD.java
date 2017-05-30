/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AventronMD;

import AventronDP.AutomovilDP;
import java.sql.Connection;
import java.sql.DriverManager;
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
       /* try {
            DataSource DSAutomovil = this.getConnection0();
            Connection con = DSAutomovil.getConnection();
            Statement st = con.createStatement();
            //actividad = actividad1;
            //String detalle = auto.getAutoPlaca();
            String Query = "";
            Query ="INSERT INTO automovil (col1, col2,col3 col4) VAlUES ('"+auto.getAutoPlaca()+"',"+ auto.getAutoAnio()+ ")"; // "select * from Usuario where usuarioci='" + detalle + "'";
            ResultSet rs = st.executeQuery(Query);
            if (rs.next()) {
                error = "El usuario que desea crear ya existe.";
                con.close();
                st.close();
                Query = "";
            } else {
           //Query = "insert into automovil  (CODIGORESULTADO, CODIGOUSUARIO, NUMERACIONACTIVIDAD, DETALLEACTIVIDAD) values('"
           //         + auto.getAutoPlaca() + "',"
           //         + auto.getAutoAnio() + ","
           //         + auto.auto.getAutoAnio() + ",'"
           //         + actividad.getDetalleActividad() + "')";
            error = "Ingreso Exitoso..";
            st.executeUpdate(Query);
            con.close();
            st.close();
            }
        } catch (NamingException ex) {
            /////Logger.getLogger(Actividad.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            //Logger.getLogger(Actividad.class.getName()).log(Level.SEVERE, null, ex);
        } */
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
