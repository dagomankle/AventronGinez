/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AventronDP;

import AventronMD.OfertanteMD;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.naming.NamingException;
import org.icefaces.ace.component.textentry.TextEntry;

/**
 *
 * @author ANDRES
 */

@ManagedBean
@ViewScoped
public class OfertanteDP implements Serializable {
    
    private int usuarioCI;
    private String usuarioNombre;
    private String usuarioTipo;
    private String usuarioSexo;
    private String usuarioContrasena;
    private String ofertanteTipoLic;
    private String error;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public OfertanteDP() {
    }
    
    

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
    
    private boolean verpaginas=false;

    public boolean isVerpaginas() {
        return verpaginas;
    }

    public void setVerpaginas(boolean verpaginas) {
        this.verpaginas = verpaginas;
    }

    private boolean VerDialogo;

    public boolean isVerDialogo() {
        return VerDialogo;
    }

    public void setVerDialogo(boolean VerDialogo) {
        this.VerDialogo = VerDialogo;
    }
    
    
    public void Insertar() throws NamingException, SQLException {
        OfertanteMD nuevo = new OfertanteMD();
        //UsuarioMD consulta = new UsuarioMD();
        //codigoUsuario = consulta.ConsultaporParametrosNombre(nombreUsuario).getCodigousuarios();
        //Actividad nueva = new Actividad(codigoActividad, codigoUsuario, codigoResultado, numeracionActividad, detalleActividad);
        if(usuarioSexo.equals("Masculino"))
        {
            usuarioSexo = "M";
        }
        else usuarioSexo = "F";
        OfertanteDP nuevoOf = new OfertanteDP(usuarioCI, usuarioNombre, usuarioTipo, usuarioSexo, usuarioContrasena, ofertanteTipoLic);
        nuevo.Insertar(nuevoOf);
        this.setError(nuevo.getError());
        this.setVerDialogo(true);
    }
    
    /*public void blurListener(AjaxBehaviorEvent event) {
        TextEntry textEntry = (TextEntry) event.getComponent();
        String value = textEntry.getValue().toString().trim();
        String label = textEntry.getLabel();
        if (value.equals("") || value.equalsIgnoreCase(label)) {
            int index = severityMap.get(label);
            String message;
            if (value.equals("")) {
                message = "Atención: El valor de " + label + " no es válido";
            } else {
                message = "Atención: El valor de " + label + " no es válido";
            }
            FacesMessage facesMessage = new FacesMessage((FacesMessage.Severity) FacesMessage.VALUES.get(index), message, message);
            FacesContext.getCurrentInstance().addMessage(textEntry.getClientId(), facesMessage);
        }
        
        private static HashMap<String, Integer> severityMap = new HashMap<String, Integer>() {
        {
            put("Codigo", 2);
            put("Nombre", 2);
            put("Contraseña", 2);
        }
    
        
        

    };*/
    
    public void guardar() {
        OfertanteMD usuario = new OfertanteMD();
        OfertanteDP nueva = new OfertanteDP(usuarioCI, usuarioNombre, usuarioTipo, usuarioSexo, usuarioContrasena, ofertanteTipoLic);
        usuario.Insertar(nueva);
        this.setError(usuario.getError());
        if (this.error.compareTo("Ingreso Exitoso") == 0) {
            this.setUsuarioCI(0);
            this.setOfertanteTipoLic("");
            this.setUsuarioContrasena("");
            this.setUsuarioNombre("");
            this.setUsuarioSexo("");
            this.setUsuarioTipo("");
            
            
        }else{
        this.setVerDialogo(true);
        }
        
        this.setVerDialogo(true);

    }
    
    
}
