/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mitocode.controller;

import com.mitocode.ejb.NotaFacadeLocal;
import com.mitocode.model.Nota;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author cesar
 */
@Named("comentarcontroller")
@RequestScoped
public class ComentarController implements Serializable {

    @EJB
    private NotaFacadeLocal notaEJB;

    private Nota nota;

    public List<Nota> lstNotas() {
        return notaEJB.findAll();
    }

    public void asignar(Nota nota) {
        this.nota = nota;
    }

    public Nota getNota() {
        return nota;
    }

    public void setNota(Nota nota) {
        this.nota = nota;
    }

}
