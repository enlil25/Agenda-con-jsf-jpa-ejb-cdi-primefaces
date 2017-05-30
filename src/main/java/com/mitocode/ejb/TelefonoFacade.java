/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mitocode.ejb;

import com.mitocode.model.Persona;
import com.mitocode.model.Telefono;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author cesar
 */
@Stateless
public class TelefonoFacade extends AbstractFacade<Telefono> implements TelefonoFacadeLocal {

    @PersistenceContext(unitName = "primePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TelefonoFacade() {
        super(Telefono.class);
    }

    public List<Telefono> listarPorPersona(Persona persona) {
        
        return em.createQuery("select p from Telefono p where p.persona.codigo=:codigo", Telefono.class).setParameter("codigo", persona.getCodigo()).getResultList();
    }

}
