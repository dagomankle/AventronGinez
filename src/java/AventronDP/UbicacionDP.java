/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AventronDP;

import AventronMD.UbicacionMD;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author JUANCARLOS
 */
@Named(value = "ubicacionDP")
@RequestScoped
public class UbicacionDP {
    private String Codigo;
private double ubiLongitud;
private double ubiLatitud;
private String ubiDescripcion;
private String ciUsuario; //

    /**
     * Creates a new instance of UbicacionDP
     */
    public UbicacionDP() {
    }

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String Codigo) {
        this.Codigo = Codigo;
    }

    public double getUbiLongitud() {
        return ubiLongitud;
    }

    public void setUbiLongitud(double ubiLongitud) {
        this.ubiLongitud = ubiLongitud;
    }

    public double getUbiLatitud() {
        return ubiLatitud;
    }

    public void setUbiLatitud(double ubiLatitud) {
        this.ubiLatitud = ubiLatitud;
    }

    public String getUbiDescripcion() {
        return ubiDescripcion;
    }

    public void setUbiDescripcion(String ubiDescripcion) {
        this.ubiDescripcion = ubiDescripcion;
    }

    public String getCiUsuario() {
        return ciUsuario;
    }

    public void setCiUsuario(String ciUsuario) {
        this.ciUsuario = ciUsuario;
    }
    public void guardar(String ci)
    {
        this.setCiUsuario(ci);
        UbicacionMD ubicacionmd=new UbicacionMD(this);
        ubicacionmd.guardar();
    }
    public void borrar()
    {
        UbicacionMD ubicacionmd=new UbicacionMD(this);
        ubicacionmd.borrar();
    }
    public void cambiar()
    {
        UbicacionMD ubicacionmd=new UbicacionMD(this);
        ubicacionmd.cambiar();
    }
    public List<UbicacionDP> consultageneral()
    {
        UbicacionMD ubicacionmd=new UbicacionMD(this);
        return ubicacionmd.consultarubicaciones();
    }
    
}
