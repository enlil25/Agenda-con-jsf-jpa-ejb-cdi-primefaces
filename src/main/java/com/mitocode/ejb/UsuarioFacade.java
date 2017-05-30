/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mitocode.ejb;

import com.mitocode.model.Usuario;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author cesar
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> implements UsuarioFacadeLocal {

    @PersistenceContext(unitName = "primePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }

    @Override
    public Usuario iniciarSesion(Usuario usuario) {

        try {
            Usuario usr = em.createQuery("select p from Usuario p where p.usuario=:usuario and p.clave=:pwd", Usuario.class)
                    .setParameter("usuario", usuario.getUsuario())
                    .setParameter("pwd", usuario.getClave())
                    .getSingleResult();
            return usr;
        } catch (NoResultException | NonUniqueResultException e) {
            return null;
        }
    }

}
