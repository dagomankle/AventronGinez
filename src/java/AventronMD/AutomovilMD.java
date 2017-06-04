/* * To change this license header, choose License Headers in Project Properties. * To change this template file, choose Tools | Templates * and open the template in the editor. */package AventronMD;import AventronDP.AutomovilDP;import java.sql.Blob;import java.sql.Connection;import java.sql.DriverManager;import java.sql.ResultSet;import java.sql.SQLException;import java.sql.Statement;import java.util.Date;import java.util.List;import javax.naming.Context;import javax.naming.InitialContext;import javax.naming.NamingException;import javax.sql.DataSource;/** * * @author dagom */public class AutomovilMD {    private AutomovilDP automovilDP;    private Connection con;    private Statement stm;    private String cadena;    private ResultSet rs;     private String error;    public AutomovilMD(AutomovilDP automovilDP) {        this.automovilDP = automovilDP;    }        public AutomovilDP recuperarAutomovil(){        AutomovilDP automovilDPL = new AutomovilDP();         try {            DataSource DSAutomovil = this.getAventronPool();            this.con = DSAutomovil.getConnection();            this.stm = this.con.createStatement();            this.cadena = "SELECT * FROM AUTOMOVIL WHERE AUTOPLACA = '"+automovilDP.getAutoPlaca()                    + "'";            rs = this.stm.executeQuery(this.cadena);            while (rs.next()) {                String autoPlaca = rs.getString("AUTOPLACA");                String usuarioCI = rs.getString("USUARIOCI");                Date autoAnio = rs.getDate("AUTOANIO");                int autoAsientosMaximos = rs.getInt("AUTOASIENTOSMAXIMOS");                Blob autoImagen = rs.getBlob("AUTOIMAGEN");                /*System.out.println(coffeeName + "\t" + supplierID +                                   "\t" + price + "\t" + sales +                                   "\t" + total);*/                automovilDPL = new AutomovilDP(autoPlaca,usuarioCI,autoAnio, autoAsientosMaximos,autoImagen);            }            con.close();            this.stm.close();                               } catch (NamingException ex) {            /////Logger.getLogger(Actividad.class.getName()).log(Level.SEVERE, null, ex);        } catch (SQLException ex) {            //Logger.getLogger(Actividad.class.getName()).log(Level.SEVERE, null, ex);    }           }               return automovilDPL;    }        public List<AutomovilDP> recuperarAutomoviles(AutomovilDP automovilDP){        List<AutomovilDP> retorno = null;         AutomovilDP automovilDPL = new AutomovilDP();         try {            DataSource DSAutomovil = this.getAventronPool();            this.con = DSAutomovil.getConnection();            this.stm = this.con.createStatement();            this.cadena = "SELECT * FROM AUTOMOVIL WHERE AUTOPLACA = '"+automovilDP.getAutoPlaca()                    + "'";            rs = this.stm.executeQuery(this.cadena);            while (rs.next()) {                String autoPlaca = rs.getString("AUTOPLACA");                String usuarioCI = rs.getString("USUARIOCI");                Date autoAnio = rs.getDate("AUTOANIO");                int autoAsientosMaximos = rs.getInt("AUTOASIENTOSMAXIMOS");                Blob autoImagen = rs.getBlob("AUTOIMAGEN");                /*System.out.println(coffeeName + "\t" + supplierID +                                   "\t" + price + "\t" + sales +                                   "\t" + total);*/                automovilDPL = new AutomovilDP(autoPlaca,usuarioCI,autoAnio, autoAsientosMaximos,autoImagen);                retorno.add(automovilDPL);            }            con.close();            this.stm.close();                               } catch (NamingException ex) {            /////Logger.getLogger(Actividad.class.getName()).log(Level.SEVERE, null, ex);        } catch (SQLException ex) {            //Logger.getLogger(Actividad.class.getName()).log(Level.SEVERE, null, ex);    }           }                return retorno;            }    public void insertarAutomovil(){        try {            DataSource DSAutomovil = this.getAventronPool();            this.con = DSAutomovil.getConnection();           cadena = "INSERT INTO AUTOMOVIL  (AUTOPLACA , USUARIOCI, AUTOANIO,  AUTOASIENTOSMAXIMOS, AUTOIMAGEN ) values('"                    + automovilDP.getAutoPlaca() + "','"                    +automovilDP.getUsuarioCI() +"', to_date('"                    + automovilDP.getAutoAnio() + "','yyyy-mm-dd'),"                    + automovilDP.getAutoAsientosMaximos() + ",'"                    + automovilDP.getAutoImagen() + "')";            this.stm.executeUpdate(this.cadena);            this.con.close();            this.stm.close();        } catch (NamingException ex) {            /////Logger.getLogger(Actividad.class.getName()).log(Level.SEVERE, null, ex);        } catch (SQLException ex) {            //Logger.getLogger(Actividad.class.getName()).log(Level.SEVERE, null, ex);        }     }        public void eliminarAutomovil(String placa){         try {            DataSource DSAutomovil = this.getAventronPool();            this.con = DSAutomovil.getConnection();            this.stm = this.con.createStatement();            this.cadena = "DELTE FROM AUTOMOVIL WHERE AUTOPLACA = " + automovilDP.getAutoPlaca() ;            this.stm.executeUpdate(this.cadena);            con.close();            this.stm.close();                    } catch (NamingException ex) {            /////Logger.getLogger(Actividad.class.getName()).log(Level.SEVERE, null, ex);        } catch (SQLException ex) {            //Logger.getLogger(Actividad.class.getName()).log(Level.SEVERE, null, ex);        }          }        public void actualizarAutomovil(){                 try {            DataSource DSAutomovil = this.getAventronPool();            this.con = DSAutomovil.getConnection();            this.stm = this.con.createStatement();            this.cadena = "UPDATE AUTOMOVIL  SET AUTOANIO= '"+automovilDP.getAutoAnio()                     + "',  AUTOASIENTOSMAXIMOS = "+automovilDP.getAutoAsientosMaximos()                     +", AUTOIMAGEN ="+ automovilDP.getAutoImagen() +"WHERE AUTOPLACA = "+automovilDP.getAutoPlaca() ;            this.stm.executeUpdate(this.cadena);            con.close();            this.stm.close();                    } catch (NamingException ex) {            /////Logger.getLogger(Actividad.class.getName()).log(Level.SEVERE, null, ex);        } catch (SQLException ex) {            //Logger.getLogger(Actividad.class.getName()).log(Level.SEVERE, null, ex);        }        }        public boolean validarAutomovil(){        boolean resultado = true;           /* this.stm = this.con.createStatement();            //actividad = actividad1;            //String detalle = auto.getAutoPlaca();            this.cadena = "select * from automovil where autoPlaca ='" + auto.getAutoPlaca() + "'";            ResultSet rs = this.stm.executeQuery(this.cadena);            if (rs.next()) {                error = "El usuario que desea crear ya existe.";                con.close();                this.stm.close();                this.cadena = "";            } else {*/             try {            DataSource DSAutomovil = this.getAventronPool();            this.con = DSAutomovil.getConnection();            this.stm = this.con.createStatement();            this.cadena = "SELECT * from AUTOMOVIL WHERE AUTOPLACA='" + this.automovilDP.getAutoPlaca() + "'" ;            this.rs = this.stm.executeQuery(this.cadena);            if (this.rs != null)                resultado = false;                        this.con.close();            this.stm.close();                    } catch (NamingException ex) {            /////Logger.getLogger(Actividad.class.getName()).log(Level.SEVERE, null, ex);        } catch (SQLException ex) {            //Logger.getLogger(Actividad.class.getName()).log(Level.SEVERE, null, ex);        }         return resultado;    }        private DataSource getAventronPool() throws NamingException {        Context c = new InitialContext();        return (DataSource) c.lookup("java:comp/env/AventronPool");   }        }