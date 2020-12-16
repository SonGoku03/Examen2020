package com.emontec.examen.Utilid;


public class Utilidades {

    //Constantes campos tabla usuario
    public static final String TABLA_USUARIO="usuario";
    public static final String CAMPO_ID="id";
    public static final String CAMPO_NOMBRE="nombre";
    public static final String CAMPO_CORREO="correo";
    public static final String CAMPO_LATITUD="latitud";
    public static final String CAMPO_LONGITUD="longitud";

    public static final String CREAR_TABLA_USUARIO="CREATE TABLE " +
            ""+TABLA_USUARIO+" ("+CAMPO_ID+" " +
            "INTEGER, "+CAMPO_NOMBRE+" TEXT,"+CAMPO_CORREO+" TEXT,"+CAMPO_LATITUD+" TEXT,"+CAMPO_LONGITUD+" TEXT)";


}