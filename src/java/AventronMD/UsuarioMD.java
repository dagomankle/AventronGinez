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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author ANDRES
 */
public class UsuarioMD {

    UsuarioDP usuario = new UsuarioDP();
    ArrayList<UsuarioDP> nueva = new ArrayList();

    public void Insertar(UsuarioDP usuario1) {
        try {
            DataSource DSUsuario = this.getAventronPool();
            Connection con = DSUsuario.getConnection();
            Statement st = con.createStatement();
            usuario = usuario1;
            String Query = "";
            this.setRenderizar(true);
            Query = "insert into usuario(CIUSUARIO,NOMBREUSUARIO,TIPOUSUARIO,SEXOUSUARIO,CONTRASENAUSUARIO, CELULARUSUARIO) values('"
                    + usuario.getCiUsuario() + "','"
                    + usuario.getNombreUsuario() + "','"
                    + usuario.getTipoUsuario() + "','"
                    + usuario.getSexoUsuario() + "','"
                    + usuario.getContrasenaUsuario() + "',"
                    + usuario.getCelularUsuario() + ")";

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
                setError("El valor ingresado para CODIGO DE USUARIO ya existe");

            }

        }
    }

    public void Modificar(UsuarioDP usuario1) throws SQLException, NamingException {
        DataSource DSUsuario = this.getAventronPool();
        Connection con = DSUsuario.getConnection();
        Statement st = con.createStatement();
        usuario = usuario1;
        String Query = "";
        Query = "update usuario set tipousuario='" + usuario.getTipoUsuario()
                + "',nombreusuario='" + usuario.getNombreUsuario()
                + "',contrasenausuario= '" + usuario.getContrasenaUsuario()
                + "',sexousuario= '" + usuario.getSexoUsuario()
                + "',celularusuario= " + usuario.getCelularUsuario()
                + " where ciusuario='" + usuario.getCiUsuario() + "'";
        st.executeUpdate(Query);
        con.close();
        st.close();
    }

    public void Eliminar(UsuarioDP usuario1) {
        try {
            DataSource DSUsuario = this.getAventronPool();
            Connection con = DSUsuario.getConnection();
            Statement st = con.createStatement();
            usuario = usuario1;
            String Query = "";
            Query = " delete from usuario where ciusuario = '" + usuario.getCiUsuario() + "'";
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

    public ArrayList consultaGeneral() throws NamingException, SQLException {
        DataSource DSUsuario = this.getAventronPool();
        Connection con = DSUsuario.getConnection();
        Statement st = con.createStatement();
        String cadena = "";
        cadena = "select * from usuario";
        ResultSet rs = st.executeQuery(cadena);
        while (rs.next()) {
            String ciuser = rs.getString("CIUSUARIO");
            String nomuser = rs.getString("NOMBREUSUARIO");
            String tipouser = rs.getString("TIPOUSUARIO");
            String sexuser = rs.getString("SEXOUSUARIO");
            String passuser = rs.getString("CONTRASENAUSUARIO");
            Integer celuser = rs.getInt("CELULARUSUARIO");

            usuario = new UsuarioDP(ciuser, nomuser, tipouser, sexuser, passuser,celuser);
            this.nueva.add(usuario);
        }
        con.close();
        st.close();
        return this.nueva;
    }

    public UsuarioDP ConsultaporParametros(String codigo) throws NamingException, SQLException {
        DataSource DSUsuario = this.getAventronPool();
        Connection con = DSUsuario.getConnection();
        Statement st = con.createStatement();
        String cadena = "";
        cadena = "select * from usuario where ciusuario='" + codigo + "'";
        ResultSet rs = st.executeQuery(cadena);
        while (rs.next()) {
            String ciuser = rs.getString("CIUSUARIO");
            String nomuser = rs.getString("NOMBREUSUARIO");
            String tipouser = rs.getString("TIPOUSUARIO");
            String sexuser = rs.getString("SEXOUSUARIO");
            String passuser = rs.getString("CONTRASENAUSUARIO");
            Integer celuser = rs.getInt("CELULARUSUARIO");
            usuario = new UsuarioDP(ciuser, nomuser, tipouser, sexuser, passuser, celuser);
        }
        con.close();
        st.close();
        return usuario;
    }

    public UsuarioDP ConsultaporParametros1(String codigo) throws NamingException, SQLException {
        DataSource DSUsuario = this.getAventronPool();
        Connection con = DSUsuario.getConnection();
        Statement st = con.createStatement();
        String cadena = "";
        cadena = "select * from usuario where nombreusuario='" + codigo + "'";
        ResultSet rs = st.executeQuery(cadena);
        while (rs.next()) {
            String ciuser = rs.getString("CIUSUARIO");
            String nomuser = rs.getString("NOMBREUSUARIO");
            String tipouser = rs.getString("TIPOUSUARIO");
            String sexuser = rs.getString("SEXOUSUARIO");
            String passuser = rs.getString("CONTRASENAUSUARIO");
            Integer celuser = rs.getInt("CELULARUSUARIO");
            usuario = new UsuarioDP(ciuser, nomuser, tipouser, sexuser, passuser, celuser);
        }
        con.close();
        st.close();
        return usuario;
    }

    /*public UsuarioDP ConsultaporParametros2(String codigo) throws NamingException, SQLException {
        DataSource DSUsuario = this.getAventronPool();
        Connection con = DSUsuario.getConnection();
        Statement st = con.createStatement();
        String cadena = "";
        cadena = "select * from usuario where nombreusuario='" + codigo + "'";
        ResultSet rs = st.executeQuery(cadena);
        while (rs.next()) {
            String codigousuarios = rs.getString("codigousuario");
            String tipousuario = rs.getString("tipousuario");
            String contrasenia = rs.getString("contrasenausuario");
            String nombreUsuario = rs.getString("nombreusuario");
            boolean usuario0 = rs.getBoolean("usuario0");
            boolean usuario1 = rs.getBoolean("usuario1");
            boolean usuario2 = rs.getBoolean("usuario2");
            boolean usuario3 = rs.getBoolean("usuario3");
            boolean usuario4 = rs.getBoolean("usuario4");
            boolean usuario5 = rs.getBoolean("usuario5");

            boolean finaciador0 = rs.getBoolean("financiador0");
            boolean finaciador1 = rs.getBoolean("financiador1");
            boolean finaciador2 = rs.getBoolean("financiador2");
            boolean finaciador3 = rs.getBoolean("financiador3");

            boolean proyecto0 = rs.getBoolean("proyecto0");
            boolean proyecto1 = rs.getBoolean("proyecto1");
            boolean proyecto2 = rs.getBoolean("proyecto2");
            boolean proyecto3 = rs.getBoolean("proyecto3");
            boolean proyecto4 = rs.getBoolean("proyecto4");
            boolean proyecto5 = rs.getBoolean("proyecto5");
            boolean proyecto6 = rs.getBoolean("proyecto6");

            boolean resultado0 = rs.getBoolean("resultado0");
            boolean resultado1 = rs.getBoolean("resultado1");
            boolean resultado2 = rs.getBoolean("resultado2");

            boolean actividad0 = rs.getBoolean("actividad0");
            boolean actividad1 = rs.getBoolean("actividad1");
            boolean actividad2 = rs.getBoolean("actividad2");

            boolean partida0 = rs.getBoolean("partida0");
            boolean partida1 = rs.getBoolean("partida1");
            boolean partida2 = rs.getBoolean("partida2");

            boolean ejeactividad0 = rs.getBoolean("ejeactividad0");
            boolean ejeactividad1 = rs.getBoolean("ejeactividad1");
            boolean ejeactividad2 = rs.getBoolean("ejeactividad2");
            boolean ejeactividad3 = rs.getBoolean("ejeactividad3");
            boolean ejeactividad4 = rs.getBoolean("ejeactividad4");
            boolean ejeactividad5 = rs.getBoolean("ejeactividad5");
            boolean ejeactividad6 = rs.getBoolean("ejeactividad6");
            boolean ejeactividad7 = rs.getBoolean("ejeactividad7");

            boolean proceso0 = rs.getBoolean("proceso0");
            boolean proceso1 = rs.getBoolean("proceso1");
            boolean proceso2 = rs.getBoolean("proceso2");
            boolean proceso3 = rs.getBoolean("proceso3");
            boolean proceso4 = rs.getBoolean("proceso4");

            usuario = new Usuario(codigousuarios, tipousuario, contrasenia, nombreUsuario, usuario0, usuario1, usuario2, usuario3, usuario4, usuario5, finaciador0, finaciador1, finaciador2, finaciador3, proyecto0, proyecto1, proyecto2, proyecto3, proyecto4, proyecto5, proyecto6, resultado0, resultado1, resultado2, actividad0, actividad1, actividad2, partida0, partida1, partida2, ejeactividad0, ejeactividad1, ejeactividad2, ejeactividad3, ejeactividad4, ejeactividad5, ejeactividad6, ejeactividad7, proceso0, proceso1, proceso2, proceso3, proceso4);
        }
        con.close();
        st.close();
        return usuario;
    }*/
    public ArrayList ConsultaporParametrosOfer() throws NamingException, SQLException {
        DataSource DSUsuario = this.getAventronPool();
        Connection con = DSUsuario.getConnection();
        Statement st = con.createStatement();
        String cadena = "";
        cadena = "select * from usuario where tipousuario= 'Ofertante'";
        ResultSet rs = st.executeQuery(cadena);
        while (rs.next()) {
            String ciuser = rs.getString("CIUSUARIO");
            String nomuser = rs.getString("NOMBREUSUARIO");
            String tipouser = rs.getString("TIPOUSUARIO");
            String sexuser = rs.getString("SEXOUSUARIO");
            String passuser = rs.getString("CONTRASENAUSUARIO");
            Integer celuser = rs.getInt("CELULARUSUARIO");
            usuario = new UsuarioDP(ciuser, nomuser, tipouser, sexuser, passuser, celuser);
            this.nueva.add(usuario);
        }
        con.close();
        st.close();
        return this.nueva;
    }

    public ArrayList ConsultaporParametrosUsu() throws NamingException, SQLException {
        DataSource DSUsuario = this.getAventronPool();
        Connection con = DSUsuario.getConnection();
        Statement st = con.createStatement();
        String cadena = "";
        cadena = "select * from usuario where tipousuario= 'Usuario'";
        ResultSet rs = st.executeQuery(cadena);
        while (rs.next()) {
            String ciuser = rs.getString("CIUSUARIO");
            String nomuser = rs.getString("NOMBREUSUARIO");
            String tipouser = rs.getString("TIPOUSUARIO");
            String sexuser = rs.getString("SEXOUSUARIO");
            String passuser = rs.getString("CONTRASENAUSUARIO");
            Integer celuser = rs.getInt("CELULARUSUARIO");
            usuario = new UsuarioDP(ciuser, nomuser, tipouser, sexuser, passuser, celuser);
            this.nueva.add(usuario);
        }
        con.close();
        st.close();
        return this.nueva;
    }

    public UsuarioDP ConsultaporParametrosNombre(String nombre) throws NamingException, SQLException {
        DataSource DSUsuario = this.getAventronPool();
        Connection con = DSUsuario.getConnection();
        Statement st = con.createStatement();
        String cadena = "";
        cadena = "select * from usuario where nombreusuario='" + nombre + "'";
        ResultSet rs = st.executeQuery(cadena);
        while (rs.next()) {
            String ciuser = rs.getString("CIUSUARIO");
            String nomuser = rs.getString("NOMBREUSUARIO");
            String tipouser = rs.getString("TIPOUSUARIO");
            String sexuser = rs.getString("SEXOUSUARIO");
            String passuser = rs.getString("CONTRASENAUSUARIO");
            Integer celuser = rs.getInt("CELULARUSUARIO");
            usuario = new UsuarioDP(ciuser, nomuser, tipouser, sexuser, passuser, celuser);

        }
        con.close();
        st.close();
        return usuario;
    }

    boolean visible = false;

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public void show(ActionEvent actionEvent) {

        visible = true;
    }

    public void hide(ActionEvent actionEvent) {
        this.visible = false;
    }

    public void closeFAjax(AjaxBehaviorEvent event) {
        this.visible = false;
    }
    private String error;

    private String error1;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getError1() {
        return error1;
    }

    public void setError1(String error1) {
        this.error1 = error1;
    }

    private ArrayList<String> Lista = new ArrayList();

    public ArrayList<String> getLista() {
        return Lista;
    }

    public void setLista(ArrayList<String> Lista) {
        this.Lista = Lista;
    }

    private boolean renderizar;

    public boolean isRenderizar() {
        return renderizar;
    }

    public void setRenderizar(boolean renderizar) {
        this.renderizar = renderizar;
    }

    public UsuarioDP Autenticar(String ci, String clave) throws NamingException, SQLException {
        DataSource DSUsuario = this.getAventronPool();
        Connection con = DSUsuario.getConnection();
        Statement st = con.createStatement();
        String cadena = "";
        cadena = "select * from usuario where ciusuario='" + ci + "' and contrasenausuario='" + clave + "'";
        ResultSet rs = st.executeQuery(cadena);
        while (rs.next()) {
            String ciuser = rs.getString("CIUSUARIO");
            String nomuser = rs.getString("NOMBREUSUARIO");
            String tipouser = rs.getString("TIPOUSUARIO");
            String sexuser = rs.getString("SEXOUSUARIO");
            String passuser = rs.getString("CONTRASENAUSUARIO");
            Integer celuser = rs.getInt("CELULARUSUARIO");
            /*boolean usuario0 = rs.getBoolean("usuario0");
            boolean usuario1 = rs.getBoolean("usuario1");
            boolean usuario2 = rs.getBoolean("usuario2");
            boolean usuario3 = rs.getBoolean("usuario3");
            boolean usuario4 = rs.getBoolean("usuario4");
            boolean usuario5 = rs.getBoolean("usuario5");

            boolean finaciador0 = rs.getBoolean("financiador0");
            boolean finaciador1 = rs.getBoolean("financiador1");
            boolean finaciador2 = rs.getBoolean("financiador2");
            boolean finaciador3 = rs.getBoolean("financiador3");

            boolean proyecto0 = rs.getBoolean("proyecto0");
            boolean proyecto1 = rs.getBoolean("proyecto1");
            boolean proyecto2 = rs.getBoolean("proyecto2");
            boolean proyecto3 = rs.getBoolean("proyecto3");
            boolean proyecto4 = rs.getBoolean("proyecto4");
            boolean proyecto5 = rs.getBoolean("proyecto5");
            boolean proyecto6 = rs.getBoolean("proyecto6");

            boolean resultado0 = rs.getBoolean("resultado0");
            boolean resultado1 = rs.getBoolean("resultado1");
            boolean resultado2 = rs.getBoolean("resultado2");

            boolean actividad0 = rs.getBoolean("actividad0");
            boolean actividad1 = rs.getBoolean("actividad1");
            boolean actividad2 = rs.getBoolean("actividad2");

            boolean partida0 = rs.getBoolean("partida0");
            boolean partida1 = rs.getBoolean("partida1");
            boolean partida2 = rs.getBoolean("partida2");

            boolean ejeactividad0 = rs.getBoolean("ejeactividad0");
            boolean ejeactividad1 = rs.getBoolean("ejeactividad1");
            boolean ejeactividad2 = rs.getBoolean("ejeactividad2");
            boolean ejeactividad3 = rs.getBoolean("ejeactividad3");
            boolean ejeactividad4 = rs.getBoolean("ejeactividad4");
            boolean ejeactividad5 = rs.getBoolean("ejeactividad5");
            boolean ejeactividad6 = rs.getBoolean("ejeactividad6");
            boolean ejeactividad7 = rs.getBoolean("ejeactividad7");

            boolean proceso0 = rs.getBoolean("proceso0");
            boolean proceso1 = rs.getBoolean("proceso1");
            boolean proceso2 = rs.getBoolean("proceso2");
            boolean proceso3 = rs.getBoolean("proceso3");
            boolean proceso4 = rs.getBoolean("proceso4");*/

            usuario = new UsuarioDP(ciuser, nomuser, tipouser, sexuser, passuser, celuser);
        }
        con.close();
        st.close();
        return usuario;
    }

    /*public void ModificarPermisos(UsuarioDP usuario) throws NamingException, SQLException {
        DataSource DSUsuario = this.getConexion();
        Connection con = DSUsuario.getConnection();
        Statement st = con.createStatement();
        String Query = "";
        Query = "update usuario set usuario0='" + usuario.getUsuario00()
                + "',usuario1='" + usuario.getUsuario01()
                + "',usuario2= '" + usuario.getUsuario02()
                + "',usuario3= '" + usuario.getUsuario03()
                + "',usuario4= '" + usuario.getUsuario04()
                + "',usuario5= '" + usuario.getUsuario05()
                + "',financiador0= '" + usuario.getFinanciador00()
                + "',financiador1= '" + usuario.getFinanciador01()
                + "',financiador2= '" + usuario.getFinanciador02()
                + "',financiador3= '" + usuario.getFinanciador03()
                + "',proyecto0= '" + usuario.getProyecto00()
                + "',proyecto1= '" + usuario.getProyecto01()
                + "',proyecto2= '" + usuario.getProyecto02()
                + "',proyecto3= '" + usuario.getProyecto03()
                + "',proyecto4= '" + usuario.getProyecto04()
                + "',proyecto5= '" + usuario.getProyecto05()
                + "',proyecto6= '" + usuario.getProyecto06()
                + "',resultado0= '" + usuario.getResultado00()
                + "',resultado1= '" + usuario.getResultado01()
                + "',resultado2= '" + usuario.getResultado02()
                + "',actividad0= '" + usuario.getActividad00()
                + "',actividad1= '" + usuario.getActividad01()
                + "',actividad2= '" + usuario.getActividad02()
                + "',partida0= '" + usuario.getPartida00()
                + "',partida1= '" + usuario.getPartida01()
                + "',partida2= '" + usuario.getPartida02()
                + "',ejeactividad0= '" + usuario.getEjeactividad00()
                + "',ejeactividad1= '" + usuario.getEjeactividad01()
                + "',ejeactividad2= '" + usuario.getEjeactividad02()
                + "',ejeactividad3= '" + usuario.getEjeactividad03()
                + "',ejeactividad4= '" + usuario.getEjeactividad04()
                + "',ejeactividad5= '" + usuario.getEjeactividad05()
                + "',ejeactividad6= '" + usuario.getEjeactividad06()
                + "',ejeactividad7= '" + usuario.getEjeactividad07()
                + "',proceso0= '" + usuario.getProceso00()
                + "',proceso1= '" + usuario.getProceso01()
                + "',proceso2= '" + usuario.getProceso02()
                + "',proceso3= '" + usuario.getProceso03()
                + "',proceso4= '" + usuario.getProceso04()
                + "' where codigousuario='" + usuario.getCodigousuarios() + "'";
        st.executeUpdate(Query);
        con.close();
        st.close();
    }*/

    private DataSource getAventronPool() throws NamingException {
        Context c = new InitialContext();
        return (DataSource) c.lookup("java:comp/env/AventronPool");
    }

}
