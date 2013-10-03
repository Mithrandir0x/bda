package edu.ub.bda.ubticket.beans;

import java.sql.Timestamp;

/**
 * Esta clase define l'usuario
 *
 * @author domenicocitera
 */
public class Usuario {

    private Integer id;
    private String login;
    private String password;
    private String nombre;
    private Timestamp fecha_alta;
    private static Integer tipo_usuario;

    public Usuario() {
        login = "";
        password = "";
        nombre = "";
       
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
    public Timestamp getFecha_alta() {
        return fecha_alta;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @param login the login to set
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @param fecha_alta the fecha_alta to set
     */
    public void setFecha_alta(Timestamp fecha_alta) {
        this.fecha_alta = fecha_alta;
    }

    /**
     * @return the tipo_usuario
     */
    public static Integer getTipo_usuario() {
        return tipo_usuario;
    }

    /**
     * @param aTipo_usuario the tipo_usuario to set
     */
    public static void setTipo_usuario(Integer aTipo_usuario) {
        tipo_usuario = aTipo_usuario;
    }

}
