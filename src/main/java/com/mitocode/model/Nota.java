/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mitocode.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author cesar
 */
@Entity
@Table(name = "nota")
public class Nota implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;
    private String encabezado;
    private String cuerpo;
    @Temporal(TemporalType.DATE) // modificado de timestamp a date
    private Date fecha;
    private String comentarioAdmin;
    private Integer valorizacion;
    @Temporal(TemporalType.TIME)
    private Date hora; //agregado recientemente

    @ManyToOne
    @JoinColumn(name = "codigo_persona", referencedColumnName = "codigo", nullable = false)
    private Persona persona;

    @ManyToOne
    @JoinColumn(name = "codigo_categoria", referencedColumnName = "codigo", nullable = false)
    private Categoria categoria;

    public Nota() {
        persona = new Persona();
        categoria = new Categoria();
    }

    public Nota(String encabezado, String cuerpo, Date fecha, String comentarioAdmin, Integer valorizacion, Date hora) {
        this.encabezado = encabezado;
        this.cuerpo = cuerpo;
        this.fecha = fecha;
        this.comentarioAdmin = comentarioAdmin;
        this.valorizacion = valorizacion;
        this.hora = hora;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getEncabezado() {
        return encabezado;
    }

    public void setEncabezado(String encabezado) {
        this.encabezado = encabezado;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getComentarioAdmin() {
        return comentarioAdmin;
    }

    public void setComentarioAdmin(String comentarioAdmin) {
        this.comentarioAdmin = comentarioAdmin;
    }

    public Integer getValorizacion() {
        return valorizacion;
    }

    public void setValorizacion(Integer valorizacion) {
        this.valorizacion = valorizacion;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.codigo);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Nota other = (Nota) obj;
        return Objects.equals(this.codigo, other.codigo);
    }

    @Override
    public String toString() {
        return "Nota{" + "codigo=" + codigo + ", encabezado=" + encabezado + ", cuerpo=" + cuerpo + ", fecha=" + fecha + ", comentarioAdmin=" + comentarioAdmin + ", valorizacion=" + valorizacion + ", hora=" + hora + ", persona=" + persona + ", categoria=" + categoria + '}';
    }

}
