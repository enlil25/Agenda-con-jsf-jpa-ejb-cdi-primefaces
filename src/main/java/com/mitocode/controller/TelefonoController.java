/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mitocode.controller;

import com.mitocode.ejb.TelefonoFacadeLocal;
import com.mitocode.model.Telefono;
import com.mitocode.model.Usuario;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author cesar
 */
@Named("telefonocontroller")
@RequestScoped
public class TelefonoController implements Serializable {

    @EJB
    private TelefonoFacadeLocal telefonoEJB;

    @Inject
    private Telefono telefono;

    public void registrar() {

        Usuario us = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        telefono.setPersona(us.getPersona());
        telefonoEJB.create(telefono);

    }

    public List<Telefono> lstTelefonos() {

        Usuario us = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");

        return telefonoEJB.listarPorPersona(us.getPersona());
    }

    public Telefono getTelefono() {
        return telefono;
    }

    public void setTelefono(Telefono telefono) {
        this.telefono = telefono;
    }

}
