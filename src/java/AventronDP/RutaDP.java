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
        AutomovilMD controlMD = new AutomovilMD(this);

        if(controlMD.validarAutomovil())
            controlMD.insertarAutomovil();
    }

    public void modificarAutomovil(String ci){
        this.usuarioCI = ci;
        AutomovilMD controlMD = new AutomovilMD(this);
        if(controlMD.validarAutomovil())
            controlMD.actualizarAutomovil();
    }
    
    public void cargarAutomovil(){
        RutaMD controlMD = new RutaMD(this);
        /*AutomovilDP sera = controlMD.recuperarAutomovil();
        this.autoPlaca = sera.autoPlaca;
        this.usuarioCI = sera.usuarioCI;
        this.autoAnio = sera.autoAnio;
        this.autoAsientosMaximos = sera.autoAsientosMaximos;
        this.autoImagen = sera.autoImagen;*/
    }
    
    public LinkedList<AutomovilDP> cargarAutomoviles(){
        RutaMD controlMD = new RutaMD(this);
        return controlMD.recuperarRutas(this);
    }
  
    public void descartarAutomovil(String nombre){
        this.rutaNombre = nombre;
        RutaMD controlMD = new AutomovilMD(this);
        controlMD.eliminarAutomovil();
    }
   
    public boolean verificarAutomovil(){
        AutomovilMD controlMD = new AutomovilMD(this);
        return controlMD.validarAutomovil();
    }   

    public LinkedList<AutomovilDP> listarVecinos(String ci){
        this.usuarioCI = ci;
        this.vecinos = this.cargarAutomoviles();
        return this.vecinos;
    }    
    
    
}
