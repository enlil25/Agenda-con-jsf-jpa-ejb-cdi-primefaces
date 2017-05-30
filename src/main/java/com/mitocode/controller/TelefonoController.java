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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author cesar
 */
@Named("telefonocontroller")
@ViewScoped
public class TelefonoController implements Serializable {

    @EJB
    private TelefonoFacadeLocal telefonoEJB;

    @Inject
    private Telefono telefono;

    private String accion;
    private static final Logger LOG = Logger.getLogger(TelefonoController.class.getName());

    public void registrar() {

        Usuario us = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        telefono.setPersona(us.getPersona());

        LOG.log(Level.SEVERE, "TELEFONO ANTES DE PERSISTIR 11" + telefono.toString());
        telefonoEJB.create(telefono);
        //pongo telefono a null
        

        LOG.log(Level.SEVERE, "TELEFONO DESPUES DE PERSISTIR 22" + telefono.toString());

    }

    public void editar() {
        telefonoEJB.edit(telefono);
    }

    //REGISTRAR(R) , MODIFICAR(M)
    public void leer(Telefono telefonosel) {
        this.telefono = telefonosel;
        this.setAccion("M");
        LOG.log(Level.SEVERE, "telefono capturado:" + telefono.toString());
    }
    
    public void presionaNuevo(){
        setAccion("R");
        setTelefono(new Telefono());
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

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

}
