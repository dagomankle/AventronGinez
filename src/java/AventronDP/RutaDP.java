/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AventronDP;

import AventronMD.AutomovilMD;
import AventronMD.RutaMD;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.naming.NamingException;

/**
 *
 * @author dagom
 */
@Named(value = "rutaDP")
@RequestScoped
public class RutaDP {
    
    private String rutaNombre;
    private String rutaDescripcion;
    private LinkedList<UbicacionDP> rutaUbicaciones;
    private LinkedList<RutaDP> rutaVecinos;
    private String usuarioCI;
    private String rutaNombresUbicaciones;

    /**
     * Creates a new instance of RutaDP
     */
    public RutaDP() {
    }

    public RutaDP(String rutaNombre, String rutaDescripcion, LinkedList<UbicacionDP> rutaUbicaciones) {
        this.rutaNombre = rutaNombre;
        this.rutaDescripcion = rutaDescripcion;
        this.rutaUbicaciones = rutaUbicaciones;
         this.rutaNombresUbicaciones = this.determinarRutaUbicacionesNombres();
        this.rutaVecinos = null;
    }

    public RutaDP(String rutaNombre, String rutaDescripcion, String rutaNombresUbicaciones) {
        this.rutaNombre = rutaNombre;
        this.rutaDescripcion = rutaDescripcion;
        this.rutaNombresUbicaciones = rutaNombresUbicaciones;
        this.rutaUbicaciones = this.determinarRutaUbicaciones();
        this.rutaVecinos = null;
    }    
   
    private String determinarRutaUbicacionesNombres(){
        String nombres = "";
        for (UbicacionDP item : this.rutaUbicaciones) {
                  nombres = nombres +item.getCodigo()+", ";
        }    
        nombres = nombres.substring(0, nombres.length() -2);
        return nombres;
    }

    private LinkedList<UbicacionDP> determinarRutaUbicaciones(){
        LinkedList<UbicacionDP> ubicaciones = new LinkedList<>();
        
        List<String> ids = Arrays.asList(this.rutaNombresUbicaciones.split(", "));
        for (String item : ids) {
            UbicacionDP ubication = new UbicacionDP();
            ubication.cargarUbicacion(item, this.usuarioCI);            
            ubicaciones.add(ubication);
        }
        return ubicaciones;
    }    
    
    public String getRutaNombresUbicaciones() {
        return rutaNombresUbicaciones;
    }

    public void setRutaNombresUbicaciones(String rutaNombresUbicaciones) {
        this.rutaNombresUbicaciones = rutaNombresUbicaciones;
    }

    public void setUsuarioCI(String usuarioCI) {
        this.usuarioCI = usuarioCI;
    }

    public String getUsuarioCI() {
        return usuarioCI;
    }

    public String getRutaNombre() {
        return rutaNombre;
    }

    public String getRutaDescripcion() {
        return rutaDescripcion;
    }

    public LinkedList<UbicacionDP> getRutaUbicaciones() {
        return rutaUbicaciones;
    }

    public LinkedList<RutaDP> getRutaVecinos() {
        return rutaVecinos;
    }

    public void setRutaNombre(String rutaNombre) {
        this.rutaNombre = rutaNombre;
    }

    public void setRutaDescripcion(String rutaDescripcion) {
        this.rutaDescripcion = rutaDescripcion;
    }

    public void setRutaUbicaciones(LinkedList<UbicacionDP> rutaUbicaciones) {
        this.rutaUbicaciones = rutaUbicaciones;
    }

    public void setRutaVecinos(LinkedList<RutaDP> rutaVecinos) {
        this.rutaVecinos = rutaVecinos;
    }
    
    public void guardarRuta(String ci){
        this.usuarioCI = ci;
        RutaMD controlMD = new RutaMD(this);

        if(controlMD.validarRuta())
            controlMD.insertarRuta();
    }
   
    public void cargarRuta(){
        RutaMD controlMD = new RutaMD(this);
        RutaDP sera = controlMD.recuperarRuta();
        this.rutaNombre = sera.rutaNombre;
        this.rutaDescripcion = sera.rutaDescripcion;
        this.rutaUbicaciones = sera.rutaUbicaciones;
    }
    
    public LinkedList<RutaDP> cargarRutas(){
        RutaMD controlMD = new RutaMD(this);
        return controlMD.recuperarRutas();
    }
  
    public void descartarRuta(String nombre){
        this.rutaNombre = nombre;
        RutaMD controlMD = new RutaMD(this);
        controlMD.eliminarRuta();
    }
   
    public boolean verificarRuta(){
        RutaMD controlMD = new RutaMD(this);
        return controlMD.validarRuta();
    }   

    public LinkedList<RutaDP> listarVecinos(String ci){
        this.usuarioCI = ci;
        this.rutaVecinos = this.cargarRutas();
        return this.rutaVecinos;
    }    
    
     public void validar1(ValueChangeEvent event) throws NamingException, SQLException {
        Object valor = event.getNewValue();
        if (valor != null) {
            RutaMD nuevo = new RutaMD(this);
            RutaDP nueva = new RutaDP();
            nueva = nuevo.ConsultaporParametros2(valor.toString());
            if (nueva != null) {
                this.setRutaNombre(nueva.getRutaNombre());
                this.setRutaDescripcion(nueva.getRutaDescripcion());
                this.setRutaUbicaciones(nueva.getRutaUbicaciones());
                this.setRutaVecinos(nueva.getRutaVecinos());
              
                this.setValidar(true);
                return;
            }
        }
        setValidar(false);
    }
    private boolean validar;

    public boolean isValidar() {
        return validar;
    }

    public void setValidar(boolean validar) {
        this.validar = validar;
    }

    public List<SelectItem> RetornarRutasU(String userCI) throws NamingException, SQLException {
        ArrayList<SelectItem> retorno = new ArrayList<SelectItem>();
        RutaMD nuevo = new RutaMD(this);
        List<String> rutas = nuevo.consultaPorUsuario(userCI);
        for (int i = 0; i < rutas.size(); i++) {
            retorno.add(new SelectItem(rutas.get(i)));
        }
        return retorno;
    }
    
    
}
