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

   /* public void Insertar(OfertanteDP ofertante) {
        try {
            DataSource DSActividad = this.getConnection0();
            Connection con = DSActividad.getConnection();
            Statement st = con.createStatement();
            //String CI = ofertante.getUsuarioCI();
            String Query = "";
            Query = "select * from OFERTANTE where USUARIOCI='" + ofertante.getUsuarioCI() + "'";
            ResultSet rs = st.executeQuery(Query);
            if (rs.next()) {
                error = "El CI ya esta asociado a un usuario.";
                con.close();
                st.close();
                Query = "";
            } else {
                Query = "insert into OFERTANTE  (USUARIOCI, USUARIONOMBRE, USUARIOTIPO, USUARIOSEXO, USUARIOCONTRASENA) values('"
                        + ofertante.getUsuarioCI() + "','"
                        + ofertante.getUsuarioNombre() + "','"
                        + ofertante.getUsuarioTipo() + "','"
                        + ofertante.getUsuarioSexo() + "','"
                        + ofertante.getUsuarioContrasena() + "')";
                        //+ ofertante.getOfertanteTipoLic() 
                error = "Ingreso Exitoso..";
                st.executeUpdate(Query);
                con.close();
                st.close();
            }
        } catch (NamingException ex) {
            Logger.getLogger(ex.getExplanation());
        } catch (SQLException ex) {
            Logger.getLogger(ex.toString());
        }
    }

    public void Modificar(OfertanteDP ofertante) throws SQLException, NamingException {
        DataSource DSProyecto = this.getConnection0();
        Connection con = DSProyecto.getConnection();
        Statement st = con.createStatement();
        //actividad = actividad1;
        String Query = "";
        Query = "update OFERTANTE SET USUARIONOMBRE = '"
                + ofertante.getUsuarioNombre() + "', USUARIOTIPO = '"
                + ofertante.getUsuarioTipo() + "', USUARIOSEXO = '"
                + ofertante.getUsuarioSexo() + "', USUARIOCONTRASENA = '"
                + ofertante.getUsuarioContrasena() + "' where USUARIOCI='"
                + ofertante.getUsuarioCI() + "'";
        st.executeUpdate(Query);
        con.close();
        st.close();
    }
    
    */
    
    

    
}
