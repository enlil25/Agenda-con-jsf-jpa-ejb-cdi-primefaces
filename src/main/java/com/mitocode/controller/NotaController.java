/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mitocode.controller;

import com.mitocode.ejb.CategoriaFacadeLocal;
import com.mitocode.model.Categoria;
import com.mitocode.model.Nota;
import com.mitocode.ejb.NotaFacadeLocal;
import com.mitocode.model.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author cesar
 */
@Named("notacontroller")
@ViewScoped
public class NotaController implements Serializable {
    
    @EJB
    private NotaFacadeLocal notaEJB;
    @Inject
    private Nota nota;
    @Inject
    private Categoria categoria;
    
    @EJB
    private CategoriaFacadeLocal categoriaEJB;
    
    private static final Logger LOG = Logger.getLogger(NotaController.class.getName());
    private List<Nota> notasBusq;
    
    public List<Categoria> lsCategorias() {
        return categoriaEJB.findAll();
    }
    
    public void registrarNota() {
        
        try {
            //insertar categoria (codigocategoria)
            //insertar persona(codigopersona)
            //insertar nota
            //las categorias se cargan en un combobox
            //el codigo de la persona se obtiene de la variable de sesion usuario
            Usuario us = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
            // el usuario tiene que existir sino lanzar una excepcion
            //persona on
            nota.setCategoria(categoria);
            nota.setFecha(new Date());
            nota.setHora(new Date());
            nota.setPersona(us.getPersona());
            
            notaEJB.create(nota);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Nota guardada", "Nota de ID:" + nota.getCodigo() + " se guardo en la base de datos"));
            
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nota no guardada", "Nota no se guardo en la base de datos"));
            
        }
        
    }
    
    public void buscar() {
        
        try {
            //buscar notas por fecha y categoria
            
            Usuario us = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
            //usuario debe existir sino lanza una exception
            nota.setPersona(us.getPersona());
            notasBusq = notaEJB.buscarPorFechayCategoria(nota);
        } catch (Exception e) {
            //si usuario es nulo , significa que no existe sesion y no deberias estar aca
        }
        
    }
    
    public List<Nota> getNotasBusq() {
        return notasBusq;
    }
    
    public Nota getNota() {
        return nota;
    }
    
    public void setNota(Nota nota) {
        this.nota = nota;
    }
    
    public Categoria getCategoria() {
        return categoria;
    }
    
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    
}
