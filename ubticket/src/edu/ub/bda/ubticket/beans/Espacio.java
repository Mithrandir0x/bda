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
public class Espacio {

    private Integer id;
    private String nombre;
    private Integer aforo;
    private String telefono;
    private String email;
    private String direccion;
    private Integer cordinadax;
    private Integer cordinaday;
    private Set<Espectaculo> espectaculo;
    private Set<Sesion> sesion;

    public Espacio() {
    }

    public Espacio(String nombre, Integer aforo, String telefono, String email,
            String direccion, Integer cordinadax, Integer cordinatay) {
        super();
        this.nombre = nombre;
        this.aforo = aforo;
        this.telefono = telefono;
        this.email = email;
        this.direccion = direccion;
        this.cordinadax = cordinadax;
        this.cordinaday = cordinaday;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    
    //GETTERS I SETTERS DE LE RELACIO N-N
    public Set<Espectaculo> getEspectaculo() {
        return espectaculo;
    }

    public void setEspectaculo(Set<Espectaculo> espectaculo) {
        this.espectaculo = espectaculo;
    }
    
    public Set<Sesion>getSesion() {
        return sesion;
    }
    
    public void setSesion(Set<Sesion> sesion) {
        this.sesion=sesion;
    }
}
