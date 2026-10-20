//Autor: luis
package com.sistema.modelos;

public class Curso {
    
    //Declaracion de atributos
    private String codigoCurso;
    private String nombreCurso;
    private String idProfesor;
    
    //Constructor vacio
    public Curso(){
    }
    
    //Constructor con parametros
    public Curso(String codigoCurso, String nombreCurso, String idProfesor) {
        this.codigoCurso = codigoCurso;
        this.nombreCurso = nombreCurso;
        this.idProfesor = idProfesor;
    }
    
    //Metodos getter y setter
    public String getCodigoCurso(){
        return codigoCurso;
    }
    
    public void setCodigoCurso(String codigoCurso){
        this.codigoCurso = codigoCurso;
    }
    
    public String getNombreCurso(){
        return nombreCurso;
    }
    
    public void setNombreCurso(String nombreCurso){
        this.nombreCurso = nombreCurso;
    }
    
    public String getIdProfesor(){
        return idProfesor;
    }
    
    public void setIdProfesor(String idProfesor){
        this.idProfesor = idProfesor;
    }
 
    //Formato de salida
    @Override
    public String toString(){
        return codigoCurso + "|" + nombreCurso + "|" + idProfesor;
    }
}