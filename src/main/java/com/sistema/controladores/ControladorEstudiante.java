//Autor: Luis
    package com.sistema.controladores;

import com.sistema.datos.EstudianteDatos;
import com.sistema.modelos.Estudiante;
import java.util.List;

public class ControladorEstudiante {
    
    private final EstudianteDatos estudianteDatos;
    
    public ControladorEstudiante(){
        this.estudianteDatos = new EstudianteDatos();
    }
    
    //Registrar un nuevo estudiante
    public boolean registrarEstudiante(String carnet, String nombre, String apellido){
        if (carnet == null || carnet.trim().isEmpty() ||
            nombre == null || nombre.trim().isEmpty() ||
            apellido == null || apellido.trim().isEmpty()) {
        
            System.err.println("[ControladorEstudiante] todos los campos son obligatorios.");
            return false;
        }
        
        //Validacion de duplicados por carnet
        if (buscarEstudiante(carnet) != null){
            System.err.println("[ControladorEstudiante] Ya existe un estudiante con el carnet " + carnet);
            return false;
        }
        
        Estudiante nuevo = new Estudiante(
            carnet.trim(),
            nombre.trim(),
            apellido.trim());
        return estudianteDatos.guardar(nuevo);
    }
    
    //Listar todos los estudiantes
    public List<Estudiante> listarEstudiantes() {
        return estudianteDatos.obtenerTodos();
    }
    
    //Buscar estudiante por carnet
    public Estudiante buscarEstudiante(String carnet) {
        if (carnet == null || carnet.trim().isEmpty()) {
            return null;
        }
        return estudianteDatos.buscarPorCarnet(carnet.trim());
    }
    
    //Modificar datos de un estudiante existente
    public boolean modificarEstudiante(String carnet, String nombre, String apellido) {
        // Validación de campos obligatorios
        if (carnet == null || carnet.trim().isEmpty() ||
            nombre == null || nombre.trim().isEmpty() ||
            apellido == null || apellido.trim().isEmpty()) {
            
            System.err.println("[ControladorEstudiante] Todos los campos son obligatorios para modificar.");
            return false;
        }

        // Verificar que el estudiante exista antes de modificar
        if (buscarEstudiante(carnet) == null) {
            System.err.println("[ControladorEstudiante] No se encontró el estudiante con carnet " + carnet);
            return false;
        }
         Estudiante modificado = new Estudiante(
            carnet.trim(),
            nombre.trim(),
            apellido.trim()
        );

        return estudianteDatos.actualizar(modificado);
    }

    //Eliminar estudiante por carnet
    public boolean eliminarEstudiante(String carnet) {
        if (carnet == null || carnet.trim().isEmpty()) {
            return false;
        }
        return estudianteDatos.eliminar(carnet.trim());
    }
   
}