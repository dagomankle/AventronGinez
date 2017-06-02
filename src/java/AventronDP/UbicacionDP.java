/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AventronDP;

import AventronMD.UbicacionMD;
import java.util.List;

/**
 *
 * @author JUANCARLOS
 */

public class UbicacionDP {
    private int ubicacionId;
    private int usuarioCI;
    private float ubicacionLongitud;
    private float ubicacionLatitud;
    private String ubicacionDescripcion;

    public UbicacionDP() {
    }

    public UbicacionDP(int ubicacionId, int usuarioCI, float ubicacionLongitud, float ubicacionLatitud, String ubicacionDescripcion) {
        this.ubicacionId = ubicacionId;
        this.usuarioCI = usuarioCI;
        this.ubicacionLongitud = ubicacionLongitud;
        this.ubicacionLatitud = ubicacionLatitud;
        this.ubicacionDescripcion = ubicacionDescripcion;
    }

    public int getUbicacionId() {
        return ubicacionId;
    }

    public void setUbicacionId(int ubicacionId) {
        this.ubicacionId = ubicacionId;
    }

    public int getUsuarioCI() {
        return usuarioCI;
    }

    public void setUsuarioCI(int usuarioCI) {
        this.usuarioCI = usuarioCI;
    }

    public float getUbicacionLongitud() {
        return ubicacionLongitud;
    }

    public void setUbicacionLongitud(float ubicacionLongitud) {
        this.ubicacionLongitud = ubicacionLongitud;
    }

    public float getUbicacionLatitud() {
        return ubicacionLatitud;
    }

    public void setUbicacionLatitud(float ubicacionLatitud) {
        this.ubicacionLatitud = ubicacionLatitud;
    }

    public String getUbicacionDescripcion() {
        return ubicacionDescripcion;
    }

    public void setUbicacionDescripcion(String ubicacion) {
        this.ubicacionDescripcion = ubicacion;
    }
    
    //*********************************************************************************************************
    public boolean GuardarUbicacion()
    {
        UbicacionMD ubicacion=new UbicacionMD();
        return ubicacion.GuardarUbicacion(this);
    }
    public List<UbicacionDP> CargarUbicaciones()
    {
       UbicacionMD ubicacion=new UbicacionMD();       
        return ubicacion.CargarUbicaciones(this);
    }
    public UbicacionDP CargarUbicacion()
    {
        UbicacionMD ubicacion=new UbicacionMD();
        UbicacionDP ubi=ubicacion.CargarUbicacion(this);
        if(ubi!=null)
        {
        this.setUbicacionDescripcion(ubi.getUbicacionDescripcion());
        this.setUbicacionId(ubi.getUbicacionId());
        this.setUbicacionLatitud(ubi.getUbicacionLatitud());
        this.setUbicacionLongitud(ubi.getUbicacionLongitud());
        this.setUsuarioCI(ubi.getUsuarioCI());  
        }        
        return ubi;
    }//*****************************
    public boolean CambiarUbicacion()
    {
        UbicacionMD ubicacion=new UbicacionMD();
        
        return ubicacion.CambiarUbicacion(this);
    }
    public boolean BorrarUbicacion()
    {
        UbicacionMD ubicacion=new UbicacionMD();
        
        return ubicacion.BorrarUbicacion(this);
    }
    public List<UbicacionDP> CargarUbicacionesGeneral()
    {
        UbicacionMD ubicacion=new UbicacionMD();       
        return ubicacion.CargarUbicacionesGeneral();     
    }
    public List<UbicacionDP> CargarUbicacionesPorDescripcion()
    {
        UbicacionMD ubicacion=new UbicacionMD();       
        return ubicacion.BuscarUbicacionesPorDescripcion(this);
    }
    public boolean ValidarUbicacion()
    {
        UbicacionMD ubicacion=new UbicacionMD();       
        return ubicacion.ValidarUbicacion(this);
    }
}
