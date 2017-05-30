/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mitocode.controller;

import com.mitocode.ejb.MenuFacadeLocal;
import com.mitocode.model.Menu;
import com.mitocode.model.Usuario;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

/**
 *
 * @author cesar
 */
@Named("menucontroller")
@ViewScoped
public class MenuController implements Serializable {

    @EJB
    private MenuFacadeLocal menuEJB;
    private List<Menu> listaMenus;

    private MenuModel menuModel;

    @PostConstruct
    public void init() {
        listarMenus();
        menuModel = new DefaultMenuModel();
        establecerPermisos();
    }

    private void listarMenus() {

        try {
            listaMenus = menuEJB.findAll();
        } catch (Exception e) {
            //mensaje jsf
        }

    }

    public void establecerPermisos() {
        
        Usuario us = (Usuario)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        
        for (Menu m : listaMenus) {
            if (m.getTipo().equals("S") && m.getTipoUsuario().equals(us.getTipo())) {
                DefaultSubMenu firstSubmenu = new DefaultSubMenu(m.getNombre());
                for (Menu i : listaMenus) {
                    Menu submenu = i.getSubmenu();
                    if (submenu != null) {
                        if (submenu.getCodigo() == m.getCodigo()) {
                            DefaultMenuItem item = new DefaultMenuItem(i.getNombre());
                            //item.setUrl(i.getUrl());
                            item.setHref(i.getUrl());
                            
                            firstSubmenu.addElement(item);
                        }
                    }
                }
                menuModel.addElement(firstSubmenu);
            } else {
                if (m.getSubmenu() == null && m.getTipoUsuario().equals(us.getTipo())) {
                    DefaultMenuItem item = new DefaultMenuItem(m.getNombre());
                    //item.setUrl(m.getUrl());
                    item.setHref(m.getUrl());
                    menuModel.addElement(item);
                }

            }
        }
    }

    public List<Menu> getListaMenus() {
        return listaMenus;
    }

    public MenuModel getMenuModel() {
        return menuModel;
    }

    public void setMenuModel(MenuModel menuModel) {
        this.menuModel = menuModel;
    }

}
