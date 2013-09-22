/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ub.bda.ubticket.beans;

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
    private Float longitud;
    private Float latitud;

    public Espacio() {
    }

    public Espacio(String nombre, Integer aforo, String telefono, String email,
            String direccion, Float longitud, Float latitud) {
        super();
        this.nombre = nombre;
        this.aforo = aforo;
        this.telefono = telefono;
        this.email = email;
        this.direccion = direccion;
        this.longitud = longitud;
        this.latitud = latitud;
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

    /**
     * @return the aforo
     */
    public Integer getAforo() {
        return aforo;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param aforo the aforo to set
     */
    public void setAforo(Integer aforo) {
        this.aforo = aforo;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the longitud
     */
    public Float getLongitud() {
        return longitud;
    }

    /**
     * @return the latitud
     */
    public Float getLatitud() {
        return latitud;
    }

    /**
     * @param longitud the longitud to set
     */
    public void setLongitud(Float longitud) {
        this.longitud = longitud;
    }

    /**
     * @param latitud the latitud to set
     */
    public void setLatitud(Float latitud) {
        this.latitud = latitud;
    }

}