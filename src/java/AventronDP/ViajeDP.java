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
import java.util.Date;
import javax.faces.event.ValueChangeEvent;
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
    private String rutaNombre;
    private String salidaViaje;
    private String llegadaViaje;
    private Date fechaViaje;
    private int plazasViaje;

    public ViajeDP(String idViaje, String autoPlaca, String rutaNombre, String salidaViaje, String llegadaViaje, Date fechaViaje, int plazasViaje) {
        this.idViaje = idViaje;
        this.autoPlaca = autoPlaca;
        this.rutaNombre = rutaNombre;
        this.salidaViaje = salidaViaje;
        this.llegadaViaje = llegadaViaje;
        this.fechaViaje = fechaViaje;
        this.plazasViaje = plazasViaje;
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
 

}
