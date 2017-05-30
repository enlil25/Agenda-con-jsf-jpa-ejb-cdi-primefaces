/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mitocode.controller;

import com.mitocode.ejb.PersonaFacadeLocal;
import com.mitocode.ejb.TelefonoFacadeLocal;
import com.mitocode.model.Persona;
import com.mitocode.model.Telefono;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author cesar
 */
@Named("consultarcontroller")
@ViewScoped
public class ConsultarController implements Serializable {

    @EJB
    private PersonaFacadeLocal personaEJB;
    @Inject
    private Persona persona;

    @EJB
    private TelefonoFacadeLocal telefonoEJB;

    private List<Telefono> listadoTelefonos;

    public void buscar() {
        listadoTelefonos = telefonoEJB.listarPorPersona(persona);
    }

    public List<Persona> lstPersonas() {
        return personaEJB.findAll();
    }

    public List<Telefono> getListadoTelefonos() {
        return listadoTelefonos;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

}
