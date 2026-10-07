//Autor: Luis
package com.sistema.modelos;

public class Estudiante extends Persona {

    //Constructor vacio
    public Estudiante(){
        super();
    }
    
    //Constructor con parametros
    public Estudiante(String id, String nombre, String apellido) {
        super(id, nombre, apellido);
    }
    
    //Metodos getter u setter para trabajar con carnet
    //El carnet es el identificador unico de un estudiante por lo que reutilizaremos el Id de persona
    public String getCarnet(){
        return getId();
    }
    
    public void setCarnet(String carnet){
        setId(carnet);
    }
    
    @Override
    public String toString(){
        return getId() + "|" + getNombre() + "|" + getApellido();
    }
    
}