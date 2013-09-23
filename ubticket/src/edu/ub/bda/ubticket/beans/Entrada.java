/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ub.bda.ubticket.beans;

/**
 *
 * @author domenicocitera
 */
public class Entrada {

    private Integer id;
    private Integer precio;
    private Integer fila;
    private Integer asiento;
    private Sesion sesion;
    private Usuario usuario;

    public Entrada(Integer precio, Integer fila, Integer asiento) {
        this.precio = precio;
        this.fila = fila;
        this.asiento = asiento;
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @return the precio
     */
    public Integer getPrecio() {
        return precio;
    }

    /**
     * @return the fila
     */
    public Integer getFila() {
        return fila;
    }

    /**
     * @return the asiento
     */
    public Integer getAsiento() {
        return asiento;
    }

    /**
     * @return the sesion
     */
    public Sesion getSesion() {
        return sesion;
    }

    /**
     * @return the usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @param precio
     */
    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    /**
     * @param fila
     */
    public void setFila(Integer fila) {
        this.fila = fila;
    }

    /**
     * @param asiento
     */
    public void setAsiento(Integer asiento) {
        this.asiento = asiento;
    }

    /**
     * @param sesion
     */
    public void setSesion(Sesion sesion) {
        this.sesion = sesion;
    }

    /**
     * @param usuario
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
