package com.emontec.examen.Modelo;

import java.io.Serializable;

/**
 * Created by CHENAO on 7/05/2017.
 */

public class Colaboradores implements Serializable {

    public Colaboradores(String id, String nombre, String longitud, String latitud, String correo) {
        this.id = id;
        this.nombre = nombre;
        this.longitud = longitud;
        this.latitud = latitud;
        this.correo = correo;
    }

    private String id;
    private String nombre;
    private String longitud;
    private String latitud;
    private String correo;


    public void setId(String id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }


    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getLongitud() {
        return longitud;
    }

    public String getLatitud() {
        return latitud;
    }

    public String getCorreo() {
        return correo;
    }




    public Colaboradores(){

    }


}
