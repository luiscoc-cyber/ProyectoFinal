//Autor: Luis 
package com.sistema.modelos;

//Declaracion de atributos
public class Inscripcion {
    private String idInscripcion;
    private String carnetEstudiante;
    private String codigoCurso;
    
    //Constructor vacio
    public Inscripcion(){
    }
    
    public Inscripcion(String idInscripcion, String carnetEstudiante, String codigoCurso){
        this.idInscripcion = idInscripcion;
        this.carnetEstudiante = carnetEstudiante;
        this.codigoCurso = codigoCurso;
    }
    
    //Metodos getter y setter
    public String getIdInscripcion(){
        return idInscripcion;
    }
    
    public void setIdInscripcion(String idInscripcion){
        this.idInscripcion = idInscripcion;
    }
    
    public String getCarnetEstudiante(){
        return carnetEstudiante;
    }
    
    public void setCarnetEstudiante(String carnetEstudiante){
        this.carnetEstudiante = carnetEstudiante;
    }
    
    public String getCodigoCurso(){
        return codigoCurso;
    }
    
    public void setCodigoCurso(String codigoCurso){
        this.codigoCurso = codigoCurso;
    }
    
    @Override
    public String toString(){
        return idInscripcion + "|" + carnetEstudiante + "|" + codigoCurso; 
    }
}
