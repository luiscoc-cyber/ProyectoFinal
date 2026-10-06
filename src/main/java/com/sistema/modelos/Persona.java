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
     
    //Metodos getter y setter
    public String getId(){
        return id;
    }
    
    public void setId(String id){
        if (id == null || id.trim().isEmpty()){
            throw new IllegalArgumentException("Error: El ID no puede estar vacio");
        }
        this.id = id.trim();
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
        return id + "|" + nombre + "|" + apellido;
    }
}
