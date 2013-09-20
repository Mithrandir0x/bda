/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ub.bda.ubticket.beans;

import java.util.Set;

/**
 *
 * @author domenicocitera
 */
public class Sesion {

    private Integer id;
    private String fecha_inicio;
    private String fecha_finalizacion;
    private Integer entradas_vendidas;
    private Set<Espectaculo> espectaculo;
    private Set<Espacio> espacio;

    public Sesion() {
    }

    public Sesion(String fecha_inicio, String fecha_finalizacion, Integer entradas_vendidas) {
        this.fecha_inicio = fecha_inicio;
        this.fecha_finalizacion = fecha_finalizacion;
        this.entradas_vendidas = entradas_vendidas;
    }

    //GETTERS I SETTERS DE LE RELACIO N-N
    public Set<Espectaculo> getEspectaculo() {
        return espectaculo;
    }

    public void setEspectaculo(Set<Espectaculo> espectaculo) {
        this.espectaculo = espectaculo;
    }

    public Set<Espacio> getEspacio() {
        return espacio;
    }

    public void setSesion(Set<Espacio> espacio) {
        this.espacio = espacio;
    }
}
