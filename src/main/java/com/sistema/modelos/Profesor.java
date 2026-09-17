//Autor: luis
package com.sistema.modelos;

public class Profesor extends Persona {

    //Constructor vacio
    public Profesor (){
        super();
    }

    //Constructor con parametros
    public Profesor (String id, String nombre, String apellido){
        super(id, nombre, apellido);
    }

    //Metodos getter y setter
    public String getIdProfesor(){
        return getId();
    }

    public void setIdProfesor (String idProfesor){
        setId(idProfesor);
    }

    @Override
    public String toString(){
        return getId() + " | " + getNombre() + " | " + getApellido();
    }
}