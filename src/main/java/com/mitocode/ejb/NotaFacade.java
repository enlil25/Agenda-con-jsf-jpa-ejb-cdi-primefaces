/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mitocode.ejb;

import com.mitocode.model.Categoria;
import com.mitocode.model.Nota;
import com.mitocode.model.Persona;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;

/**
 *
 * @author cesar
 */
@Stateless
public class NotaFacade extends AbstractFacade<Nota> implements NotaFacadeLocal {

    @PersistenceContext(unitName = "primePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public NotaFacade() {

        super(Nota.class);
    }

    private static final Logger LOG = Logger.getLogger(NotaFacade.class.getName());

    @Override
    public List<Nota> buscarPorFechayCategoria(Nota nota) {

        List<Nota> busq = new ArrayList();
        String query = "";

        Categoria categoria = nota.getCategoria();
        Date fecha = nota.getFecha();
        Persona persona = nota.getPersona();

        //si no existe categoria y existe fecha , se busca por fecha
        if (null == categoria.getCodigo() && null != fecha) {
            query = "select p from Nota p where p.fecha = :fecha and p.persona.codigo = :codigo order by p.fecha desc";
            busq = em.createQuery(query, Nota.class).setParameter("fecha", fecha, TemporalType.DATE).setParameter("codigo", persona.getCodigo()).getResultList();
            //si existe categoria y existe fecha , se busca por ambos
        } else if (null != categoria.getCodigo() && null != fecha) {
            query = "select p from Nota p where p.categoria.codigo = :codcat and p.fecha = :fecha and p.persona.codigo = :codigo order by p.fecha desc";
            busq = em.createQuery(query, Nota.class).setParameter("fecha", fecha, TemporalType.DATE).setParameter("codcat", categoria.getCodigo()).setParameter("codigo", persona.getCodigo()).getResultList();
            //si existe categoria y no existe fecha , se busca por categoria
        } else if (null != categoria.getCodigo() && null == fecha) {
            query = "select p from Nota p where p.categoria.codigo=:codcat and p.persona.codigo = :codigo order by p.fecha desc";
            busq = em.createQuery(query, Nota.class).setParameter("codcat", categoria.getCodigo()).setParameter("codigo", persona.getCodigo()).getResultList();
            //si no existe categoria y  no existe fecha , busca todos     
        } else if (null == categoria.getCodigo() && null == fecha) {
            query = "select p from Nota p where p.persona.codigo = :codigo order by p.fecha desc";
            busq = em.createQuery(query, Nota.class).setParameter("codigo", persona.getCodigo()).getResultList();
        }

        return busq;
    }

}
