/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AventronMD;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import AventronDP.OfertanteDP;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ANDRES
 */
public class OfertanteMD {

    private String error;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
    
    
    private void Insertar(OfertanteDP ofertante)
    {
       try {
            DataSource DSActividad = this.getConnection0();
            Connection con = DSActividad.getConnection();
            Statement st = con.createStatement();
            //String CI = ofertante.getUsuarioCI();
            String Query = "";
            Query = "select * from OFERTANTE where USUARIOCI='"+ofertante.getUsuarioCI()+"'";
            ResultSet rs = st.executeQuery(Query);
            if (rs.next()) {
                error = "La Actividad que desea crear ya existe.";
                con.close();
                st.close();
                Query = "";
            } else {
           /*Query = "insert into actividad  (CODIGORESULTADO, CODIGOUSUARIO, NUMERACIONACTIVIDAD, DETALLEACTIVIDAD) values('"
                    + actividad.getCodigoResultado() + "','"
                    + actividad.getCodigoUsuario() + "','"
                    + actividad.getNumeracionActividad() + "','"
                    + actividad.getDetalleActividad() + "')";
            error = "Ingreso Exitoso..";*/
            st.executeUpdate(Query);
            con.close();
            st.close();
            }
        } catch (NamingException ex) {
            //Logger.getLogger(Actividad.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            //Logger.getLogger(Actividad.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private DataSource getConnection0() throws NamingException {
        Context c = new InitialContext();
        return (DataSource) c.lookup("java:comp/env/connection0");
    }
}
