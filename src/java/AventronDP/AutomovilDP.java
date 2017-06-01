/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AventronDP;

import AventronMD.AutomovilMD;
import java.util.Date;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.sql.rowset.serial.SerialBlob;

/**
 *
 * @author dagom
 */
@Named(value = "automovilDP")
@RequestScoped
public class AutomovilDP {

    private String autoPlaca;
    private Date autoAnio;
    private int autoAsientosMaximos;
    private  SerialBlob autoImagen;    
    
    /**
     * Creates a new instance of AutomovilDP
     */
    public AutomovilDP() {
    }

    public AutomovilDP(String autoPlaca, Date autoAnio, int autoAsientosMaximos, SerialBlob autoImagen) {
        this.autoPlaca = autoPlaca;
        this.autoAnio = autoAnio;
        this.autoAsientosMaximos = autoAsientosMaximos;
        this.autoImagen = autoImagen;
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

    public SerialBlob getAutoImagen() {
        return autoImagen;
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

    public void setAutoImagen(SerialBlob autoImagen) {
        this.autoImagen = autoImagen;
    }
 
    public void guardarAutomovil(String tipo){
        AutomovilMD controlMD = new AutomovilMD(this);
        if (tipo == "insertar")
            controlMD.insertarAutomovil(this);
        if (tipo == "actualizar")
            controlMD.actualizarAutomovil(this);
    }
    
    public AutomovilDP cargarAutomovil(String placa){
        AutomovilMD controlMD = new AutomovilMD(this);
        return controlMD.recuperarAutomovil(placa);
    }
    
    public List<AutomovilDP> cargarAutomoviles(){
        AutomovilMD controlMD = new AutomovilMD(this);
        return controlMD.recuperarAutomoviles();
    }
    
    public void descartarAutomovil(String placa){
        AutomovilMD controlMD = new AutomovilMD(this);
        controlMD.eliminarAutomovil(placa);
    }
    
    public boolean verificarAutomovil(){
        AutomovilMD controlMD = new AutomovilMD(this);
        return controlMD.validarAutomovil(this);
    }    
}
