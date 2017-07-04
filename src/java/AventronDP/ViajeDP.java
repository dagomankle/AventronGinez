/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AventronDP;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import AventronMD.ViajeMD;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.naming.NamingException;

/**
 *
 * @author ANDRES
 */
@Named(value = "viajeDP")
@RequestScoped
public class ViajeDP {

    private String idViaje;
    private String autoPlaca;
    private String ciUsuario;
    private String rutaNombre;
    private String salidaViaje;
    private String llegadaViaje;
    private Date fechaViaje;
    private int plazasViaje;
    private LinkedList<ViajeDP> viajesDP;

    public ViajeDP(String idViaje, String autoPlaca, String ciUsuario, String rutaNombre, String salidaViaje, String llegadaViaje, Date fechaViaje, int plazasViaje) {
        this.idViaje = idViaje;
        this.autoPlaca = autoPlaca;
        this.ciUsuario = ciUsuario;
        this.rutaNombre = rutaNombre;
        this.salidaViaje = salidaViaje;
        this.llegadaViaje = llegadaViaje;
        this.fechaViaje = fechaViaje;
        this.plazasViaje = plazasViaje;
    }

    public String getCiUsuario() {
        return ciUsuario;
    }

    public void setCiUsuario(String ciUsuario) {
        this.ciUsuario = ciUsuario;
    }

    

    public LinkedList<ViajeDP> getViajesDP() {
        return viajesDP;
    }

    public void setViajesDP(LinkedList<ViajeDP> viajesDP) {
        this.viajesDP = viajesDP;
    }


    

    public int getPlazasViaje() {
        return plazasViaje;
    }

    public void setPlazasViaje(int plazasViaje) {
        this.plazasViaje = plazasViaje;
    }

    public String getIdViaje() {
        return idViaje;
    }

    public void setIdViaje(String idViaje) {
        this.idViaje = idViaje;
    }

    public String getAutoPlaca() {
        return autoPlaca;
    }

    public void setAutoPlaca(String autoPlaca) {
        this.autoPlaca = autoPlaca;
    }

    public String getRutaNombre() {
        return rutaNombre;
    }

    public void setRutaNombre(String rutaNombre) {
        this.rutaNombre = rutaNombre;
    }

    public String getSalidaViaje() {
        return salidaViaje;
    }

    public void setSalidaViaje(String salidaViaje) {
        this.salidaViaje = salidaViaje;
    }

    public String getLlegadaViaje() {
        return llegadaViaje;
    }

    public void setLlegadaViaje(String llegadaViaje) {
        this.llegadaViaje = llegadaViaje;
    }

    public Date getFechaViaje() {
        return fechaViaje;
    }

    public void setFechaViaje(Date fechaViaje) {
        this.fechaViaje = fechaViaje;
    }

    /**
     * Creates a new instance of ViajeDP
     */
    public ViajeDP() {
    }

    public void InsertarViaje(String ciusuario) {
        UsuarioDP user = new UsuarioDP();
        user.setCiUsuario(ciusuario);

        
        AutomovilDP auto = new AutomovilDP();
        auto.setAutoPlaca(this.autoPlaca);
        auto = auto.obtenerPlazas(auto);

        this.plazasViaje = auto.getAutoAsientosMaximos();
        
        if(this.idViaje.equals(""))
        {
            int i = this.fechaViaje.getMinutes();
            String str = Integer.toString(i);
            String id = ""+str+this.autoPlaca;
            this.idViaje=id;
        }

        ViajeMD viaje = new ViajeMD();
        viaje.InsertarMD(this, user);
        this.setError(this.getError());
        if (this.error.compareTo("Ingreso Exitoso") == 0) {
            this.setAutoPlaca("");
            this.setFechaViaje(null);
            this.setIdViaje("");
            this.setLlegadaViaje("");
            this.setSalidaViaje("");
            this.setPlazasViaje(0);
            this.setRutaNombre("");

        } else {
            this.setVerDialogo(true);
        }

        this.setVerDialogo(true);

    }

    private boolean verDialogo = false;

    public boolean isVerDialogo() {
        return verDialogo;
    }
    
    private boolean validar;

    public boolean isValidar() {
        return validar;
    }

    public void setValidar(boolean validar) {
        this.validar = validar;
    }

    private boolean validar1;

    public boolean isValidar1() {
        return validar1;
    }

    public void setValidar1(boolean validar1) {
        this.validar1 = validar1;
    }
    
    public void setVerDialogo(boolean verDialogo) {
        this.verDialogo = verDialogo;
    }
    
    private String error = "";

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
    
    /*public void validar1(ValueChangeEvent event) throws NamingException, SQLException {
        Object valor = event.getNewValue();
        if (valor != null) {
            ViajeMD nuevo = new ViajeMD();
            ViajeDP nueva = new ViajeDP();
            nueva = nuevo.ConsultaporParametros(valor.toString());
            if (nueva != null) {
                this.setCodigousuarios(nueva.getCodigousuarios());
                this.setNombreUsuario(nueva.getNombreUsuario());
                this.setContrasenia(nueva.getContrasenia());
                this.setTipousuario(nueva.getTipousuario());
                this.setValidar(true);
                return;
            }
        }
        setValidar(false);
    }*/
    
     private boolean estadoBusqueda;

    public boolean isEstadoBusqueda() {
        return estadoBusqueda;
    }

    public void setEstadoBusqueda(boolean estadoSesion) {
        this.estadoBusqueda = estadoSesion;
    }

    public ViajeDP(boolean estadoSesion) {
        this.estadoBusqueda = estadoSesion;
    }
    
    public void CambiarEstadoBus(){
    
        if(this.isEstadoBusqueda()==true){
            this.setEstadoBusqueda(false);
        }
        else this.setEstadoBusqueda(true);
                
    }
    
    public void generarBusqueda(){
        
    }
    
    public LinkedList<ViajeDP> ConsultaPorParametros(String userCI) throws NamingException, SQLException{
    
        ViajeMD Consulta = new ViajeMD();
        
        this.viajesDP=Consulta.consultaPorUsuario(userCI);
        
        
        return viajesDP;
    }
    
    public void validar1(ValueChangeEvent event) throws NamingException, SQLException {
        Object valor = event.getNewValue();
        if (valor != null) {
            ViajeMD nuevo = new ViajeMD();
            ViajeDP nueva = new ViajeDP();
            nueva = nuevo.ConsultaporParametros(valor.toString());
            if (nueva != null) {
                this.setIdViaje(nueva.getIdViaje());
                this.setAutoPlaca(nueva.getAutoPlaca());
                this.setRutaNombre(nueva.getRutaNombre());
                this.setCiUsuario(nueva.getCiUsuario());
                this.setSalidaViaje(nueva.getSalidaViaje());
                this.setLlegadaViaje(nueva.getLlegadaViaje());
                this.setFechaViaje(nueva.getFechaViaje());
                this.setPlazasViaje(nueva.getPlazasViaje());
                this.setValidar1(true);
                return;
            }
        }
        setValidar1(false);
    }
    
    public void validar2(ValueChangeEvent event) throws NamingException, SQLException {
        Object valor = event.getNewValue();
        if (valor != null) {
            ViajeMD nuevo = new ViajeMD();
            ViajeDP nueva = new ViajeDP();
            nueva = nuevo.ConsultaporParametros(valor.toString());
            if (nueva != null) {
                this.setIdViaje(nueva.getIdViaje());
                this.setAutoPlaca(nueva.getAutoPlaca());
                this.setRutaNombre(nueva.getRutaNombre());
                this.setCiUsuario(nueva.getCiUsuario());
                this.setSalidaViaje(nueva.getSalidaViaje());
                this.setLlegadaViaje(nueva.getLlegadaViaje());
                this.setFechaViaje(nueva.getFechaViaje());
                this.setPlazasViaje(nueva.getPlazasViaje());
                this.setValidar(true);
                return;
            }
        }
        setValidar(false);
    }
    
    public List<SelectItem> RetornarCodigos(String UserCI) throws NamingException, SQLException {
        ArrayList<SelectItem> retorno = new ArrayList<SelectItem>();
        ViajeMD nuevo = new ViajeMD();
        List<ViajeDP> viajes = nuevo.consultaPorUsuarioF(UserCI);
        for (int i = 0; i < viajes.size(); i++) {
            retorno.add(new SelectItem(viajes.get(i).getIdViaje()));
        }
        return retorno;
    }
    public String aprobar() {
        if (validar == true) {
            return "true";
        } else {
            return "false";
        }
    }
    
    public String aprobar1() {
        if (validar1 == true) {
            return "true";
        } else {
            return "false";
        }
    }
    
    private String retorno;

    public String getRetorno() {
        return retorno;
    }

    public void setRetorno(String retorno) {
        this.retorno = retorno;
    }
    
    public void SI(ActionEvent actionEvent) throws SQLException, NamingException {
        ViajeMD nuevo = new ViajeMD();
        ViajeDP nueva = new ViajeDP(idViaje, autoPlaca, ciUsuario, rutaNombre, salidaViaje, llegadaViaje, fechaViaje, plazasViaje);
        nuevo.Eliminar(nueva);
        this.setError(nuevo.getError());
        //this.setLista(nuevo.getLista());
        //this.setError1(nuevo.getError1());
        this.setVerDialogo1(true);
    }
    
     private boolean verDialogo1;

    public boolean isVerDialogo1() {
        return verDialogo1;
    }

    public void setVerDialogo1(boolean verDialogo1) {
        this.verDialogo1 = verDialogo1;
    }
 
    public void No(ActionEvent actionEvent) {
        retorno = "No se ha eliminado el Viaje";
    }

}
