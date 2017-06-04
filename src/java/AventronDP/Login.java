/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AventronDP;

import AventronDP.UsuarioDP;
import AventronMD.UsuarioMD;
//import MD.UsuarioMD;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.naming.NamingException;
import javax.faces.context.FacesContext;

/**
 *
 * @author Jose
 */
@ManagedBean
@SessionScoped
public class Login implements Serializable {

    UsuarioDP usuario;

    public UsuarioDP getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDP usuario) {
        this.usuario = usuario;
    }

    public Login() {
        usuario = new UsuarioDP();
    }

    public void autenticar() throws NamingException, SQLException, IOException {
        UsuarioMD comprobar = new UsuarioMD();
        usuario = comprobar.Autenticar(usuario.getNombreUsuario(), usuario.getContrasenaUsuario());
        usuario.setEstadoSesion(true);
        if (usuario.getCiUsuario() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("Usuario", usuario);
            FacesContext.getCurrentInstance().getExternalContext().redirect("/LoginGUI/VentanaInicioAventron.xhtml");
        } else {
            String message = "POR FAVOR INGRESE LOS DATOS CORRECTOS";
            FacesMessage facesMessage = new FacesMessage((FacesMessage.Severity) FacesMessage.VALUES.get(2), message, message);
            FacesContext.getCurrentInstance().addMessage(message, facesMessage);
        }
    }

    public void Cerrar() throws IOException {
       // this.setConfirmar(true);
        usuario.setEstadoSesion(false);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear();
        FacesContext.getCurrentInstance().getExternalContext().redirect("../VentanaInicioAventron.xhtml");

    }

}