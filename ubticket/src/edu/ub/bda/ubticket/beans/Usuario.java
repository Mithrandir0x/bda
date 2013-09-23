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
public class Usuario {

    private Integer id;
    private String login;
    private String password;
    private String nombre;
    private Timestamp fecha_alta;

    public Usuario(String login, String password, String nombre, Timestamp fecha_alta) {
        this.login = login;
        this.password = password;
        this.nombre = nombre;
        this.fecha_alta = fecha_alta;
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @return the fecha_alta
     */
    public Timestamp getFechaAlta() {
        return fecha_alta;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @param login
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @param fecha_alta
     */
    public void setFechaAlta(Timestamp fecha_alta) {
        this.fecha_alta = fecha_alta;
    }
}
