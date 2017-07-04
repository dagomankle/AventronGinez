/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AventronMD;

import AventronDP.AutomovilDP;
import AventronDP.UsuarioDP;
import AventronDP.ViajeDP;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
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
    private LinkedList<ViajeDP> viajesDP;

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
    
    public LinkedList consultaPorUsuario(String userCI) throws NamingException, SQLException {
        DataSource DSUsuario = this.getAventronPool();
        Connection con = DSUsuario.getConnection();
        Statement st = con.createStatement();
        String cadena = "";
        cadena = "select * from VIAJE where CIUSUARIO='" + userCI + "'";
        ResultSet rs = st.executeQuery(cadena);
        viajesDP = new LinkedList<>();
        while (rs.next()) {
            String idViaje = rs.getString("IDVIAJE");
            String autoPlaca = rs.getString("AUTOPLACA");
            String rutaNom = rs.getString("RUTANOMBRE");
            String usuarioCI = rs.getString("CIUSUARIO");
            String salidaViaje = rs.getString("SALIDAVIAJE");
            String llegadaViaje = rs.getString("LLEGADAVIAJE");
            Date fechaViaje = rs.getDate("FECHAVIAJE");
            int plazasViaje = rs.getInt("PLAZASVIAJE");
            ViajeDP nuevo = new ViajeDP(idViaje, autoPlaca, usuarioCI, rutaNom, salidaViaje, llegadaViaje, fechaViaje, plazasViaje);
            this.viajesDP.add(nuevo);
        }
        con.close();
        st.close();
        return this.viajesDP;
    }
    
    public LinkedList consultaPorUsuarioF(String userCI) throws NamingException, SQLException {
        DataSource DSUsuario = this.getAventronPool();
        Connection con = DSUsuario.getConnection();
        Statement st = con.createStatement();
        String cadena = "";
        cadena = "select * from VIAJE where CIUSUARIO='" + userCI + "' AND FECHAVIAJE >= TRUNC(sysdate)";
        ResultSet rs = st.executeQuery(cadena);
        viajesDP = new LinkedList<>();
        while (rs.next()) {
            String idViaje = rs.getString("IDVIAJE");
            String autoPlaca = rs.getString("AUTOPLACA");
            String rutaNom = rs.getString("RUTANOMBRE");
            String usuarioCI = rs.getString("CIUSUARIO");
            String salidaViaje = rs.getString("SALIDAVIAJE");
            String llegadaViaje = rs.getString("LLEGADAVIAJE");
            Date fechaViaje = rs.getDate("FECHAVIAJE");
            int plazasViaje = rs.getInt("PLAZASVIAJE");
            ViajeDP nuevo = new ViajeDP(idViaje, autoPlaca, usuarioCI, rutaNom, salidaViaje, llegadaViaje, fechaViaje, plazasViaje);
            this.viajesDP.add(nuevo);
        }
        con.close();
        st.close();
        return this.viajesDP;
    }
    
    
    
    public ViajeDP ConsultaporParametros(String codigo) throws NamingException, SQLException {
        DataSource DSUsuario = this.getAventronPool();
        Connection con = DSUsuario.getConnection();
        Statement st = con.createStatement();
        String cadena = "";
        cadena = "select * from VIAJE where IDVIAJE='" + codigo + "'";
        ResultSet rs = st.executeQuery(cadena);
        while (rs.next()) {
            String idViaje = rs.getString("IDVIAJE");
            String autoPlaca = rs.getString("AUTOPLACA");
            String rutaNom = rs.getString("RUTANOMBRE");
            String usuarioCI = rs.getString("CIUSUARIO");
            String salidaViaje = rs.getString("SALIDAVIAJE");
            String llegadaViaje = rs.getString("LLEGADAVIAJE");
            Date fechaViaje = rs.getDate("FECHAVIAJE");
            int plazasViaje = rs.getInt("PLAZASVIAJE");
            Viaje = new ViajeDP(idViaje, autoPlaca, usuarioCI, rutaNom, salidaViaje, llegadaViaje, fechaViaje, plazasViaje);
        }
        con.close();
        st.close();
        return Viaje;
    }

    private DataSource getAventronPool() throws NamingException {
        Context c = new InitialContext();
        return (DataSource) c.lookup("java:comp/env/AventronPool");
    }

    public void Eliminar(ViajeDP viaje) {
        try {
            DataSource DSUsuario = this.getAventronPool();
            Connection con = DSUsuario.getConnection();
            Statement st = con.createStatement();
            //usuario = usuario1;
            String Query = "";
            Query = " delete from VIAJE where IDVIAJE = '" + viaje.getIdViaje() + "'";
            st.executeUpdate(Query);
            con.close();
            st.close();
            error = "Eliminacion Exitosa";
        } catch (NamingException ex) {
            Logger.getLogger(UsuarioMD.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("error1");
        } catch (SQLException ex) {

            /*System.out.println("error2");
            int codigoError = ex.getErrorCode();
            if (codigoError == 1451) {
                try {
                    String error = ex.getMessage();
                    String tablaAsociada = "";
                    int i = 0;
                    while (error.substring(i, i + 1).compareTo(".") != 0) {
                        if (error.substring(i + 1, i + 2).compareTo(".") == 0) {
                            while (error.substring(i + 3, i + 4).compareTo("`") != 0) {
                                tablaAsociada = tablaAsociada + error.substring(i + 3, i + 4);
                                i++;
                            }
                            break;
                        }
                        i++;
                    }
                    String[] Separacion = error.split(" ");
                    String TablaPrincipal = "";
                    for (int j = 0; j < Separacion.length; j++) {
                        if (Separacion[j].compareTo("REFERENCES") == 0) {
                            TablaPrincipal = Separacion[j + 1];
                        }
                    }
                    TablaPrincipal = TablaPrincipal.substring(1, TablaPrincipal.length() - 1);
                    try {
                        DataSource DSUsuario = this.getConexion();
                        Connection con = DSUsuario.getConnection();
                        Statement st = con.createStatement();
                        String Query = "select * from " + tablaAsociada + " where codigousuario='" + usuario.getCodigousuarios() + "'";
                        ResultSet rs = st.executeQuery(Query);

                        while (rs.next()) {
                            if (tablaAsociada.compareTo("actividad") == 0) {
                                Lista.add(rs.getString("detalleactividad"));
                            }
                            if (tablaAsociada.compareTo("proyecto") == 0) {
                                Lista.add(rs.getString("tituloproyecto"));
                            }
                        }
                        con.close();
                        st.close();
                    } catch (SQLException ex1) {
            Logger.getLogger(ProcesoMD.class.getName()).log(Level.SEVERE, null, ex1);
                    }
                    setError("El " + TablaPrincipal + " esta asociado a: " + tablaAsociada);
                    setError1("por lo que no es posible eliminar");
                } catch (NamingException ex1) {
            Logger.getLogger(ProcesoMD.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }*/
        }
    }
}
