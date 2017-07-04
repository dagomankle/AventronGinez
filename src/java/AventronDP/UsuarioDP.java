/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AventronDP;

import AventronMD.UsuarioMD;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
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
@Named(value = "usuarioDP")
@RequestScoped
public class UsuarioDP {

    private String ciUsuario;
    private String nombreUsuario;
    private String tipoUsuario;
    private String sexoUsuario;
    private String contrasenaUsuario;
    private Integer celularUsuario;

    public UsuarioDP(String ciUsuario, String nombreUsuario, String tipoUsuario, String sexoUsuario, String contrasenaUsuario, Integer celular) {
        this.ciUsuario = ciUsuario;
        this.nombreUsuario = nombreUsuario;
        this.tipoUsuario = tipoUsuario;
        this.sexoUsuario = sexoUsuario;
        this.contrasenaUsuario = contrasenaUsuario;
        this.celularUsuario = celular;
    }

    public Integer getCelularUsuario() {
        return celularUsuario;
    }

    public void setCelularUsuario(Integer celular) {
        this.celularUsuario = celular;
    }

    public String getCiUsuario() {
        return ciUsuario;
    }

    public void setCiUsuario(String ciUsuario) {
        this.ciUsuario = ciUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getSexoUsuario() {
        return sexoUsuario;
    }

    public void setSexoUsuario(String sexoUsuario) {
        this.sexoUsuario = sexoUsuario;
    }

    public String getContrasenaUsuario() {
        return contrasenaUsuario;
    }

    public void setContrasenaUsuario(String contrasenaUsuario) {
        this.contrasenaUsuario = contrasenaUsuario;
    }

    public UsuarioDP() {
    }

    private boolean verpaginas = false;

    public boolean isVerpaginas() {
        return verpaginas;
    }

    public void setVerpaginas(boolean verpaginas) {
        this.verpaginas = verpaginas;
    }

    public void modificar(String CI, String tipo) throws NamingException, SQLException {
        UsuarioMD usuario = new UsuarioMD();
        //Login nuevL = new Login();
        //this.ciUsuario = nuevL.usuario.getCiUsuario();
        this.ciUsuario = CI;
        this.tipoUsuario=tipo;
        UsuarioDP nueva = new UsuarioDP(ciUsuario, nombreUsuario, tipoUsuario, sexoUsuario, contrasenaUsuario, celularUsuario);
        if(nueva.sexoUsuario.equals("Masculino")){
            nueva.sexoUsuario="M";
        }
        else nueva.sexoUsuario = "F";
        usuario.Modificar(nueva);
        ciUsuario = "";
        tipoUsuario = "";
        nombreUsuario = "";
        contrasenaUsuario = "";
        sexoUsuario ="";
    }
    
    

    public List<SelectItem> RetornarCodigos() throws NamingException, SQLException {
        ArrayList<SelectItem> retorno = new ArrayList<SelectItem>();
        UsuarioMD nuevo = new UsuarioMD();
        List<UsuarioDP> usuarios = nuevo.consultaGeneral();
        for (int i = 0; i < usuarios.size(); i++) {
            retorno.add(new SelectItem(usuarios.get(i).getCiUsuario()));
        }
        return retorno;
    }

    public List<SelectItem> RetornarCodigosOfer() throws NamingException, SQLException {
        ArrayList<SelectItem> retorno = new ArrayList<SelectItem>();
        UsuarioMD nuevo = new UsuarioMD();
        List<UsuarioDP> usuarios = nuevo.ConsultaporParametrosOfer();
        for (int i = 0; i < usuarios.size(); i++) {
            retorno.add(new SelectItem(usuarios.get(i).getCiUsuario()));
        }
        return retorno;
    }

    public List<SelectItem> RetornarNombresUsu() throws NamingException, SQLException {
        ArrayList<SelectItem> retorno = new ArrayList<SelectItem>();
        UsuarioMD nuevo = new UsuarioMD();
        List<UsuarioDP> usuarios = nuevo.ConsultaporParametrosUsu();
        for (int i = 0; i < usuarios.size(); i++) {
            retorno.add(new SelectItem(usuarios.get(i).getNombreUsuario()));
        }
        return retorno;
    }

    public List<SelectItem> RetornarNombresOfer() throws NamingException, SQLException {
        ArrayList<SelectItem> retorno = new ArrayList<SelectItem>();
        UsuarioMD nuevo = new UsuarioMD();
        List<UsuarioDP> usuarios = nuevo.ConsultaporParametrosOfer();
        for (int i = 0; i < usuarios.size(); i++) {
            retorno.add(new SelectItem(usuarios.get(i).getNombreUsuario()));
        }
        return retorno;
    }

    public List<SelectItem> RetornarNombres() throws NamingException, SQLException {
        ArrayList<SelectItem> retorno = new ArrayList<SelectItem>();
        UsuarioMD nuevo = new UsuarioMD();
        List<UsuarioDP> usuarios = nuevo.consultaGeneral();
        for (int i = 0; i < usuarios.size(); i++) {
            retorno.add(new SelectItem(usuarios.get(i).getNombreUsuario()));
        }
        return retorno;
    }
    private boolean validar;

    public boolean isValidar() {
        return validar;
    }

    public void setValidar(boolean validar) {
        this.validar = validar;
    }

    public void validar1(ValueChangeEvent event) throws NamingException, SQLException {
        Object valor = event.getNewValue();
        if (valor != null) {
            UsuarioMD nuevo = new UsuarioMD();
            UsuarioDP nueva = new UsuarioDP();
            nueva = nuevo.ConsultaporParametros(valor.toString());
            if (nueva != null) {
                this.setCiUsuario(nueva.getCiUsuario());
                this.setNombreUsuario(nueva.getNombreUsuario());
                this.setContrasenaUsuario(nueva.getContrasenaUsuario());
                this.setTipoUsuario(nueva.getTipoUsuario());
                this.setSexoUsuario(nueva.getTipoUsuario());
                this.setValidar(true);
                return;
            }
        }
        setValidar(false);
    }

    public void validar3(ValueChangeEvent event) throws NamingException, SQLException {
        Object valor = event.getNewValue();
        if (valor != null) {
            UsuarioMD nuevo = new UsuarioMD();
            UsuarioDP nueva = new UsuarioDP();
            nueva = nuevo.ConsultaporParametros1(valor.toString());
            if (nueva != null) {
                this.setCiUsuario(nueva.getCiUsuario());
                this.setNombreUsuario(nueva.getNombreUsuario());
                this.setContrasenaUsuario(nueva.getContrasenaUsuario());
                this.setTipoUsuario(nueva.getTipoUsuario());
                this.setSexoUsuario(nueva.getTipoUsuario());
                this.setValidar(true);
                return;
            }
        }
        setValidar(false);
    }

    public void validar2(String valor) throws NamingException, SQLException {
        if (valor != null) {
            UsuarioMD nuevo = new UsuarioMD();
            UsuarioDP nueva = new UsuarioDP();
            nueva = nuevo.ConsultaporParametros(valor);
            if (nueva != null) {
                this.setCiUsuario(nueva.getCiUsuario());
                this.setNombreUsuario(nueva.getNombreUsuario());
                this.setContrasenaUsuario(nueva.getContrasenaUsuario());
                this.setTipoUsuario(nueva.getTipoUsuario());
                this.setSexoUsuario(nueva.getTipoUsuario());
                this.setValidar(true);
                return;
            }
        }
        setValidar(false);
    }

    public String aprobar() {
        if (validar == true) {
            return "true";
        } else {
            return "false";
        }
    }
    private boolean verDialogo1;

    public boolean isVerDialogo1() {
        return verDialogo1;
    }

    public void setVerDialogo1(boolean verDialogo1) {
        this.verDialogo1 = verDialogo1;
    }

    public void SI(String ci, ActionEvent actionEvent) throws SQLException, NamingException {
        UsuarioMD nuevo = new UsuarioMD();
        UsuarioDP nueva = new UsuarioDP(ciUsuario, nombreUsuario, tipoUsuario, sexoUsuario, contrasenaUsuario,celularUsuario);
        nueva.setCiUsuario(ci);
        nuevo.Eliminar(nueva);
        this.setError(nuevo.getError());
        this.setLista(nuevo.getLista());
        this.setError1(nuevo.getError1());
        this.setVerDialogo1(true);
    }
    
    public void ConfModi(ActionEvent actionEvent, String ci, String tipo) throws SQLException, NamingException {
        this.ciUsuario=ci;
        this.tipoUsuario=tipo;
        UsuarioMD nuevo = new UsuarioMD();
        UsuarioDP nueva = new UsuarioDP(ciUsuario, nombreUsuario, tipoUsuario, sexoUsuario, contrasenaUsuario,celularUsuario);
        if(nueva.sexoUsuario.equals("Masculino")){
            nueva.sexoUsuario="M";
        }
        else nueva.sexoUsuario = "F";
        nuevo.Modificar(nueva);
        
        this.setError(nuevo.getError());
        this.setLista(nuevo.getLista());
        this.setError1(nuevo.getError1());
        this.setVerDialogo1(true);
    }
    private String retorno;

    public String getRetorno() {
        return retorno;
    }

    public void setRetorno(String retorno) {
        this.retorno = retorno;
    }

    public void No(ActionEvent actionEvent) {
        retorno = "No se ha eliminado el Usuario";
    }
    private List<UsuarioDP> usuario = new ArrayList();

    public List ConsultaGeneral() throws SQLException, NamingException {
        UsuarioMD consulta = new UsuarioMD();
        usuario = consulta.consultaGeneral();
        return usuario;
    }

    public List<UsuarioDP> getUsuario() {
        return usuario;
    }

    public void setUsuario(List<UsuarioDP> usuario) {
        this.usuario = usuario;
    }

    public void blurListener(AjaxBehaviorEvent event) {
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

    }
    private static HashMap<String, Integer> severityMap = new HashMap<String, Integer>() {
        {
            put("Ci", 2);
            put("Nombre", 2);
            put("Contrasena", 2);
        }
    };

    public void hide(ActionEvent actionEvent) {
        this.setVerDialogo(false);
    }

    public void cerrarDialogo() {
        this.setVerDialogo(false);

    }

    private boolean verDialogo = false;

    public boolean isVerDialogo() {
        return verDialogo;
    }

    public void setVerDialogo(boolean verDialogo) {
        this.verDialogo = verDialogo;
    }
    private String error = "";

    private String error1;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getError1() {
        return error1;
    }

    public void setError1(String error1) {
        this.error1 = error1;
    }

    private ArrayList<String> Lista = new ArrayList();

    public ArrayList<String> getLista() {
        return Lista;
    }

    public void setLista(ArrayList<String> Lista) {
        this.Lista = Lista;
    }

    public void guardar() {
        UsuarioMD usuario = new UsuarioMD();
        UsuarioDP nueva = new UsuarioDP(ciUsuario, nombreUsuario, tipoUsuario, sexoUsuario, contrasenaUsuario, celularUsuario);
        
        if(nueva.sexoUsuario.equals("Masculino")){
            nueva.sexoUsuario="M";
        }
        else nueva.sexoUsuario = "F";
    
        usuario.Insertar(nueva);
        this.setError(usuario.getError());
        if (this.error.compareTo("Ingreso Exitoso") == 0) {
            this.setCiUsuario("");
            this.setTipoUsuario("");
            this.setContrasenaUsuario("");
            this.setNombreUsuario("");
            this.setSexoUsuario("");
            this.setCelularUsuario(0);

        } else {
            this.setVerDialogo(true);
        }

        this.setVerDialogo(true);

    }

    ////////////////////PARA PERMISOS DE USUARIO//////////////////////////////
    

    private boolean estadoSesion;

    public boolean isEstadoSesion() {
        return estadoSesion;
    }

    public void setEstadoSesion(boolean estadoSesion) {
        this.estadoSesion = estadoSesion;
    }

    public UsuarioDP(boolean estadoSesion) {
        this.estadoSesion = estadoSesion;
    }
    
    public void CambiarEstadoSes(){
    
        if(this.isEstadoSesion()==true){
            this.setEstadoSesion(false);
        }
        else this.setEstadoSesion(true);
                
    }
}
