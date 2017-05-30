/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mitocode.controller;

import com.mitocode.ejb.PersonaFacadeLocal;
import com.mitocode.ejb.UsuarioFacadeLocal;
import com.mitocode.model.Persona;
import com.mitocode.model.Usuario;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.transaction.Transactional;

/**
 *
 * @author cesar
 */
@Named("usuariocontroller")
@RequestScoped
public class UsuarioController implements Serializable {

    @EJB
    private UsuarioFacadeLocal usuarioEJB;
    @EJB
    private PersonaFacadeLocal personaEJB;

    private Usuario usuario;
    private Persona persona;
    private static final Logger LOG = Logger.getLogger(UsuarioController.class.getName());

    @PostConstruct
    private void init() {
        usuario = new Usuario();
        persona = new Persona();
    }

    public void registrar() {
        
        FacesContext ctx = FacesContext.getCurrentInstance();
        try {

            personaEJB.create(persona);
            this.usuario.setPersona(persona);
            usuarioEJB.create(usuario);

            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Persona registrada", "Persona de Id:" + persona.getCodigo() + " se registro en la base de datos"));

        } catch (Exception e) {
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "No se registro", "Hubo un error"));

        }

    }
    
    
   
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

}
