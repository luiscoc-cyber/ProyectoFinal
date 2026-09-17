// Autor: Luis
package com.sistema.modelos;

public class Persona {
    
    //Atributos privados (encapsulamiento)
    private String id;
    private String nombre;
    private String apellido;
    
    //Constructor vacio
    public Persona (){
    }
    
     //Constructor con parametros
    public Persona(String id, String nombre, String apellido){
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
    }
     
    //Metodos getter y sette
    public String getId(){
        return id;
    }
    
    public void setId(String id){
        this.id = id;
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public void setNombre(String nombre){
        this.nombre = nombre;   
    }
    
    public String getApellido(){
        return apellido;
    }
    
    public void setApellido(String apellido){
        this.apellido = apellido;
    }
    
    @Override
    public String toString(){
        return id + " | " + nombre + " | " + apellido;
    }
}
