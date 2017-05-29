/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AventronDP;

import AventronMD.OfertanteMD;

/**
 *
 * @author ANDRES
 */
public class OfertanteDP {
    
    private int usuarioCI;
    private String usuarioNombre;
    private String usuarioTipo;
    private String usuarioSexo;
    private String usuarioContrasena;
    private String ofertanteTipoLic;

    public OfertanteDP(int usuarioCI, String usuarioNombre, String usuarioTipo, String usuarioSexo, String usuarioContrasena, String ofertanteTipoLic) {
        this.usuarioCI = usuarioCI;
        this.usuarioNombre = usuarioNombre;
        this.usuarioTipo = usuarioTipo;
        this.usuarioSexo = usuarioSexo;
        this.usuarioContrasena = usuarioContrasena;
        this.ofertanteTipoLic = ofertanteTipoLic;
    }

    public int getUsuarioCI() {
        return usuarioCI;
    }

    public void setUsuarioCI(int usuarioCI) {
        this.usuarioCI = usuarioCI;
    }

    public String getUsuarioNombre() {
        return usuarioNombre;
    }

    public void setUsuarioNombre(String usuarioNombre) {
        this.usuarioNombre = usuarioNombre;
    }

    public String getUsuarioTipo() {
        return usuarioTipo;
    }

    public void setUsuarioTipo(String usuarioTipo) {
        this.usuarioTipo = usuarioTipo;
    }

    public String getUsuarioSexo() {
        return usuarioSexo;
    }

    public void setUsuarioSexo(String usuarioSexo) {
        this.usuarioSexo = usuarioSexo;
    }

    public String getUsuarioContrasena() {
        return usuarioContrasena;
    }

    public void setUsuarioContrasena(String usuarioContrasena) {
        this.usuarioContrasena = usuarioContrasena;
    }

    public String getOfertanteTipoLic() {
        return ofertanteTipoLic;
    }

    public void setOfertanteTipoLic(String ofertanteTipoLic) {
        this.ofertanteTipoLic = ofertanteTipoLic;
    }
    
    

    
    
    
    
}
