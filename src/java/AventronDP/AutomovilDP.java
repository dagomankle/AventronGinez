/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AventronDP;

import java.util.Date;
import java.util.List;
import javax.sql.rowset.serial.SerialBlob;

/**
 *
 * @author dagom
 */
public class AutomovilDP {
    
    private String autoPlaca;
    private Date autoAnio;
    private int autoSientosMaximos;
    private  SerialBlob autoImagen;

    public void GuardarAutomovil(){
    
    }
    
    /*public AutomovilDP CargarAutomovil(){
        ;
    }
    
    public List<AutomovilDP> CargarAutomoviles(){
        
    }
    
    public void DescartarAutomovil(){
    
    }
    
    public boolean VerificarAutomovil(){
        
    }*/
    
    public String getAutoPlaca() {
        return autoPlaca;
    }

    public Date getAutoAnio() {
        return autoAnio;
    }

    public int getAutoSientosMaximos() {
        return autoSientosMaximos;
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

    public void setAutoSientosMaximos(int autoSientosMaximos) {
        this.autoSientosMaximos = autoSientosMaximos;
    }

    public void setAutoImagen(SerialBlob autoImagen) {
        this.autoImagen = autoImagen;
    }

    public AutomovilDP(String autoPlaca, Date autoAnio, int autoSientosMaximos, SerialBlob autoImagen) {
        this.autoPlaca = autoPlaca;
        this.autoAnio = autoAnio;
        this.autoSientosMaximos = autoSientosMaximos;
        this.autoImagen = autoImagen;
    }
    
    
}
