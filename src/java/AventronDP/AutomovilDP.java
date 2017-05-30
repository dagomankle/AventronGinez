/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AventronDP;

import AventronMD.AutomovilMD;
import java.util.Date;
import java.util.List;
import javax.sql.rowset.serial.SerialBlob;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author dagom
 */

@ManagedBean
@ViewScoped
public class AutomovilDP {
    
    private String autoPlaca;
    private Date autoAnio;
    private int autoAsientosMaximos;
    private  SerialBlob autoImagen;
    private AutomovilMD  controlMD  = new AutomovilMD();

    public void GuardarAutomovil(String tipo){
        if (tipo == "insertar")
            controlMD.InsertarAutomovil(this);
        if (tipo == "actualizar")
            controlMD.ActualizarAutomovil(this);
    }
    
    public AutomovilDP CargarAutomovil(String placa){
        return controlMD.RecuperarAutomovil(placa);
    }
    
    public List<AutomovilDP> CargarAutomoviles(){
        return controlMD.RecuperarAutomoviles();
    }
    
    public void DescartarAutomovil(String placa){
        controlMD.EliminarAutomovil(placa);
    }
    
    public boolean VerificarAutomovil(){
        return controlMD.ValidarAutomovil(this);
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

    public void setAutoAsientosMaximos(String autoSientosMaximos) {
        this.autoAsientosMaximos = Integer.parseInt(autoSientosMaximos);
    }

    public void setAutoImagen(SerialBlob autoImagen) {
        this.autoImagen = autoImagen;
    }

    public AutomovilDP(String autoPlaca, Date autoAnio, int autoSientosMaximos, SerialBlob autoImagen) {
        this.autoPlaca = autoPlaca;
        this.autoAnio = autoAnio;
        this.autoAsientosMaximos = autoSientosMaximos;
        this.autoImagen = autoImagen;
    }

    public AutomovilDP() {
        this.autoPlaca = null;
        this.autoAnio = null;
        this.autoAsientosMaximos = 0;
        this.autoImagen = null;
    }
}
