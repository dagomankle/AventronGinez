/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package AventronDP;

import AventronMD.AutomovilMD;
import java.sql.Blob;
import java.util.Date;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author dagom
 */

@Named(value = "automovilDP")
@RequestScoped

public class AutomovilDP {

    private String autoPlaca;
    private String usuarioCI;
    private Date autoAnio;
    private int autoAsientosMaximos;
    private Blob autoImagen;
    private List<AutomovilDP> vecinos;

    /**

     * Creates a new instance of AutomovilDP

     */

    public AutomovilDP() {
        //this.vecinos = this.cargarAutomoviles();

    }

    public AutomovilDP(String autoPlaca, String usuarioCI, Date autoAnio, int autoAsientosMaximos, Blob autoImagen) {
        this.autoPlaca = autoPlaca;
        this.usuarioCI = usuarioCI;
        this.autoAnio = autoAnio;
        this.autoAsientosMaximos = autoAsientosMaximos;
        this.autoImagen = autoImagen;
        this.vecinos = null;
    }



    public String getAutoPlaca() {
        return autoPlaca;
    }

    public Date getAutoAnio() {
        return autoAnio;
    }

    public int getAutoAsientosMaximos() {
        return autoAsientosMaximos;
    }

    public Blob getAutoImagen() {
        return autoImagen;
    }

    public String getUsuarioCI() {
        return usuarioCI;
    }

    public List<AutomovilDP> getVecinos() {
        return vecinos;
    }
    
    public void setAutoPlaca(String autoPlaca) {
        this.autoPlaca = autoPlaca;
    }

    public void setAutoAnio(Date autoAnio) {
        this.autoAnio = autoAnio;
    }

    public void setAutoAsientosMaximos(int autoAsientosMaximos) {
        this.autoAsientosMaximos = autoAsientosMaximos;
    }

    public void setAutoImagen(Blob autoImagen) {
        this.autoImagen = autoImagen;
    }

    public void setUsuarioCI(String usuarioCI) {
        this.usuarioCI = usuarioCI;
    }

    public void setVecinos(List<AutomovilDP> vecinos) {
        this.vecinos = vecinos;
    }
     
    public void guardarAutomovil(){
        AutomovilMD controlMD = new AutomovilMD(this);
        
        if(controlMD.validarAutomovil())
            controlMD.insertarAutomovil();
    }

    public void modificarAutomovil(){
        AutomovilMD controlMD = new AutomovilMD(this);
        if(controlMD.validarAutomovil())
            controlMD.actualizarAutomovil();
    }
    
    public void cargarAutomovil(){
        AutomovilMD controlMD = new AutomovilMD(this);
        AutomovilDP sera = controlMD.recuperarAutomovil();
        this.autoPlaca = sera.autoPlaca;
        this.usuarioCI = sera.usuarioCI;
        this.autoAnio = sera.autoAnio;
        this.autoAsientosMaximos = sera.autoAsientosMaximos;
        this.autoImagen = sera.autoImagen;
    }
    
    public List<AutomovilDP> cargarAutomoviles(){
        AutomovilMD controlMD = new AutomovilMD(this);
        return controlMD.recuperarAutomoviles(this);
    }
  
    public void descartarAutomovil(){
        AutomovilMD controlMD = new AutomovilMD(this);
        controlMD.eliminarAutomovil(this.autoPlaca);
    }
   
    public boolean verificarAutomovil(){
        AutomovilMD controlMD = new AutomovilMD(this);
        return controlMD.validarAutomovil();
    }   

}

