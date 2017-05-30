/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mitocode.controller;

import com.mitocode.ejb.UsuarioFacadeLocal;
import com.mitocode.model.Usuario;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

/**
 *
 * @author cesar
 */
@Named("logincontroller")
@SessionScoped
public class LoginController implements Serializable {

    @EJB
    private UsuarioFacadeLocal usuarioEJB;

    @Inject
    private Usuario usuario;

    public String iniciarSesion() {

        usuario = usuarioEJB.iniciarSesion(usuario);

        if (null == usuario) {
            FacesContext ctx = FacesContext.getCurrentInstance();
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "no puedes iniciar sesion", "Usuario o password son incorrectos"));
            return "index.xhtml";
        }

        return "home.xhtml?faces-redirect=true";
    }
    
    public void cerrarSesion(){
     
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        
//         Object sesion =ctx.getExternalContext().getSession(false);
//         
//         HttpSession httpSession = (HttpSession) sesion;
//         
//         httpSession.invalidate();
        
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        
        //return "index.xhtml";
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    
}
