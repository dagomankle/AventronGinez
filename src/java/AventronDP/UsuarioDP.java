/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AventronDP;

import AventronMD.UsuarioMD;
import java.sql.SQLException;

/**
 *
 * @author romin
 */
public class UsuarioDP {
    
    private char usuarioCI;
    private String usuarioNombre;
    private String usuarioTipo;
    private char usuarioSexo;
    private char usuarioContrasena; 
    private UsuarioMD usuario = new UsuarioMD();; 

    public UsuarioDP() {
    }
    
    public UsuarioDP(char usuarioCI, String usuarioNombre, String usuarioTipo, char usuarioSexo, char usuarioContrasena) {
        this.usuarioCI = usuarioCI;
        this.usuarioNombre = usuarioNombre;
        this.usuarioTipo = usuarioTipo;
        this.usuarioSexo = usuarioSexo;
        this.usuarioContrasena = usuarioContrasena;
    }

    public char getUsuarioCI() {
        return usuarioCI;
    }

    public void setUsuarioCI(char usuarioCI) {
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

    public char getUsuarioSexo() {
        return usuarioSexo;
    }

    public void setUsuarioSexo(char usuarioSexo) {
        this.usuarioSexo = usuarioSexo;
    }

    public char getUsuarioContrasena() {
        return usuarioContrasena;
    }

    public void setUsuarioContrasena(char usuarioContrasena) {
        this.usuarioContrasena = usuarioContrasena;
    }
    /*
    public boolean insertarUsuarioeDP(UsuarioDP usr) throws ClassNotFoundException {
       return usuario.insertarUsuarioMD(usr);
   }
    
    public boolean modificarUsuarioDP(UsuarioDP usr) throws ClassNotFoundException, SQLException {
        return usuario.modificarUsuarioMD(usr);
   }
    
    public boolean eliminarUsuarioDP(String codigo) {
         return UsuarioMD.eliminarUsuarioMD(codigo);     
   }
    
    public String[] consultarUsuarioDP(String codigo) {
         return UsuarioMD.consultarUsuarioMD(codigo);
        
   }
    */
    
}
