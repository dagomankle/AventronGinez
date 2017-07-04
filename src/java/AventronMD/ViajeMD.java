/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AventronMD;

import AventronDP.UsuarioDP;
import AventronDP.ViajeDP;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author ANDRES
 */
public class ViajeMD {

    ViajeDP Viaje = new ViajeDP();

    public ViajeMD() {
    }

    private boolean renderizar;

    public boolean isRenderizar() {
        return renderizar;
    }

    public void setRenderizar(boolean renderizar) {
        this.renderizar = renderizar;
    }

    private String error;

    //private String error1;
    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public void InsertarMD(ViajeDP viaje, UsuarioDP usuario) {
        try {
            DataSource DSUsuario = this.getAventronPool();
            Connection con = DSUsuario.getConnection();
            Statement st = con.createStatement();
            String Query = "";
            this.setRenderizar(true);
            String fechanew = conversorDate(viaje.getFechaViaje());
            Query = "insert into VIAJE(IDVIAJE,AUTOPLACA,RUTANOMBRE,CIUSUARIO, PLAZASVIAJE, SALIDAVIAJE, LLEGADAVIAJE, FECHAVIAJE) values('"
                    + viaje.getIdViaje() + "','"
                    + viaje.getAutoPlaca() + "','"
                    + viaje.getRutaNombre() + "','"
                    + usuario.getCiUsuario() + "',"
                    + viaje.getPlazasViaje() + ",'"
                    + viaje.getSalidaViaje() + "','"
                    + viaje.getLlegadaViaje() + "', to_date('"
                    + fechanew + "','yyyy-mm-dd'))";

            System.out.println(Query);
            st.executeUpdate(Query);

            error = "Ingreso Exitoso";
            con.close();
            st.close();
        } catch (NamingException ex) {
            Logger.getLogger(UsuarioMD.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("error1");
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioMD.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("error2");
            int codigoError = ex.getErrorCode();
            System.out.println("" + codigoError);
            if (codigoError == 1062) {
                setError("El valor ingresado para Viaje ID ya existe");

            }

        }
    }

    public String conversorDate(Date param) {
        Date hola = new Date(param.getTime());
        String[] elementos = hola.toString().split(" ");

        return elementos[5] + "-" + elementos[1] + "-" + elementos[2];
    }
    
    /*public ViajeDP ConsultaporParametros(String idv) throws NamingException, SQLException {
        DataSource DSViaje = this.getAventronPool();
        Connection con = DSViaje.getConnection();
        Statement st = con.createStatement();
        String cadena = "";
        cadena = "select * from VIAJE where ='" + codigo + "'";
        ResultSet rs = st.executeQuery(cadena);
        while (rs.next()) {
            String codigousuarios = rs.getString("codigousuario");
            String tipousuario = rs.getString("tipousuario");
            String contrasenia = rs.getString("contrasenausuario");
            String nombreUsuario = rs.getString("nombreusuario");
            usuario = new Usuario(codigousuarios, tipousuario, contrasenia, nombreUsuario);
        }
        con.close();
        st.close();
        return usuario;
    }*/

    private DataSource getAventronPool() throws NamingException {
        Context c = new InitialContext();
        return (DataSource) c.lookup("java:comp/env/AventronPool");
    }

}
