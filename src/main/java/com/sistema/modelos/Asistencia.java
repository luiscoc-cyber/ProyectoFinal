//Autor: luis

package com.sistema.modelos;

public class Asistencia {

    //Declaracion de atributos
    private String idAsistencia;
    private String carnetEstudiante;
    private String codigoCurso;
    private String fecha;
    private String estado;
    
    //Constructor vacio
    public Asistencia(){
    }
    
    //Constructor con parametros
    public Asistencia(String idAsistencia, String carnetEstudiante, String codigoCurso, String fecha, String estado){
        this.idAsistencia = idAsistencia;
        this.carnetEstudiante = carnetEstudiante;
        this.codigoCurso = codigoCurso;
        this.fecha = fecha;
        this.estado = estado;
    }
    
    //Metodos getter y setter
    public String getIdAsistencia(){
        return idAsistencia;
    }
    
    public void setIdAsistencia(String idAsistencia){
        this.idAsistencia = idAsistencia;
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
    
    public String getFecha(){
        return fecha;
    }
    
    public void setFecha(String fecha){
        this.fecha = fecha;    
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    @Override
    public String toString(){
        return idAsistencia + "|" + carnetEstudiante + "|" + codigoCurso + "|" + fecha + "|" + estado;  
    }
    
}

