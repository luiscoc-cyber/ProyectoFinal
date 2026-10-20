    //Autor: luis
package com.sistema.modelos;

public class Profesor extends Persona {
    private String password;
    
    //Constructor vacio
    public Profesor (){
        super();
    }

    //Constructor con parametros
    public Profesor (String id, String nombre, String apellido, String password){
        super(id, nombre, apellido);
        this.password = password;
    }

    //Metodos getter y setter
    public String getIdProfesor(){
        return getId();
    }

    public void setIdProfesor (String idProfesor){
        setId(idProfesor);
    }
    
    public String getPassword(){
        return this.password;
    }
    
    public void setPassword (String password){
        this.password = password;
    }

    @Override
    public String toString(){
        return getId() + "|" + getNombre() + "|" + getApellido() + "|" + getPassword()    ;
    }
}