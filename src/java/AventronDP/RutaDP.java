/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AventronDP;

import AventronMD.RutaMD;
import java.util.LinkedList;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

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

    /**
     * Creates a new instance of RutaDP
     */
    public RutaDP() {
    }

    public RutaDP(String rutaNombre, String rutaDescripcion, LinkedList<UbicacionDP> rutaUbicaciones) {
        this.rutaNombre = rutaNombre;
        this.rutaDescripcion = rutaDescripcion;
        this.rutaUbicaciones = rutaUbicaciones;
        this.rutaVecinos = null;
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
    
    public void guardarAutomovil(String ci){
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
    
    
}
