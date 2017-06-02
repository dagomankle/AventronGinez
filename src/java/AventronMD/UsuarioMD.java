/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AventronMD;

import AventronDP.UsuarioDP;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author romin
 */
public class UsuarioMD {
    
     private String error;

    public UsuarioMD() {
    }

     
     
    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public boolean insertarUsuarioMD(UsuarioDP usuario) {
        try {
            DataSource DSActividad = this.getConnection0();
            Connection con = DSActividad.getConnection();
            Statement st = con.createStatement();
            //String CI = usuario.getUsuarioCI();
            String Query = "";
            Query = "select * from USUARIOS where USUARIOCI='" + usuario.getUsuarioCI() + "'";
            ResultSet rs = st.executeQuery(Query);
            if (rs.next()) {
                error = "El CI ya esta asociado a un usuario.";
                con.close();
                st.close();
                Query = "";
               
                
            } else {
                Query = "insert into USUARIOS  (USUARIOCI, USUARIONOMBRE, USUARIOTIPO, USUARIOSEXO, USUARIOCONTRASENA) values('"
                        + usuario.getUsuarioCI() + "','"
                        + usuario.getUsuarioNombre() + "','"
                        + usuario.getUsuarioTipo() + "','"
                        + usuario.getUsuarioSexo() + "','"
                        + usuario.getUsuarioContrasena() + "','";
                error = "Ingreso Exitoso..";
                st.executeUpdate(Query);
                con.close();
                st.close();
                return true; 
            }
            
        } catch (NamingException ex) {
            Logger.getLogger(ex.getExplanation());
        } catch (SQLException ex) {
            Logger.getLogger(ex.toString());
        } 
        return false; /*Comprobar*/
    }

    public boolean modificarUsuarioMD(UsuarioDP usuario) throws SQLException, NamingException {
        DataSource DSProyecto = this.getConnection0();
        Connection con = DSProyecto.getConnection();
        Statement st = con.createStatement();
        //actividad = actividad1;
        String Query = "";
        Query = "update USUARIOS SET USUARIONOMBRE = '"
                + usuario.getUsuarioNombre() + "', USUARIOTIPO = '"
                + usuario.getUsuarioTipo() + "', USUARIOSEXO = '"
                + usuario.getUsuarioSexo() + "', USUARIOCONTRASENA = '"
                + usuario.getUsuarioContrasena() 
                + "' where USUARIOCI='"
                + usuario.getUsuarioCI() + "'";
        st.executeUpdate(Query);
        con.close();
        st.close();
        return true;
    }

    private DataSource getConnection0() throws NamingException {
        Context c = new InitialContext();
        return (DataSource) c.lookup("java:comp/env/connection0");
    }
    
}
