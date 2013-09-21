/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ub.bda.ubticket.beans;

import java.sql.Timestamp;

/**
 *
 * @author domenicocitera
 */
public class Sesion {

    private Integer id;
    private Timestamp fecha_inicio;
    private Timestamp fecha_finalizacion;
    private Integer entradas_vendidas;
    private Espectaculo espectaculo;
    private Espacio espacio;

    public Sesion() {
    }

    public Sesion(Timestamp fecha_inicio, Timestamp fecha_finalizacion, Integer entradas_vendidas) {
        this.fecha_inicio = fecha_inicio;
        this.fecha_finalizacion = fecha_finalizacion;
        this.entradas_vendidas = entradas_vendidas;
    }

    public Espectaculo getEspectaculo() {
        return espectaculo;
    }

    public void setEspectaculo(Espectaculo espectaculo) {
        this.espectaculo = espectaculo;
    }

    public Espacio getEspacio() {
        return espacio;
    }

    public void setSesion(Espacio espacio) {
        this.espacio = espacio;
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @return the fecha_inicio
     */
    public Timestamp getFecha_inicio() {
        return fecha_inicio;
    }

    /**
     * @return the fecha_finalizacion
     */
    public Timestamp getFecha_finalizacion() {
        return fecha_finalizacion;
    }

    /**
     * @return the entradas_vendidas
     */
    public Integer getEntradas_vendidas() {
        return entradas_vendidas;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @param fecha_inicio
     */
    public void setFecha_inicio(Timestamp fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    /**
     * @param fecha_finalizacion
     */
    public void setFecha_finalizacion(Timestamp fecha_finalizacion) {
        this.fecha_finalizacion = fecha_finalizacion;
    }

    /**
     * @param entradas_vendidas
     */
    public void setEntradas_vendidas(Integer entradas_vendidas) {
        this.entradas_vendidas = entradas_vendidas;
    }

}
