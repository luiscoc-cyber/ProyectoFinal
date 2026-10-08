//Autor: Luis
package com.sistema.controladores;

import com.sistema.datos.ProfesorDatos;
import com.sistema.modelos.Profesor;
import java.util.List;

public class ControladorProfesor {

    private final ProfesorDatos profesorDatos;

    public ControladorProfesor() {
        this.profesorDatos = new ProfesorDatos();
    }

    // 1. Registrar un nuevo profesor
    public boolean registrarProfesor(String idProfesor, String nombre, String apellido) {
        // Validación de campos obligatorios
        if (idProfesor == null || idProfesor.trim().isEmpty() ||
            nombre == null || nombre.trim().isEmpty() ||
            apellido == null || apellido.trim().isEmpty()) {
            
            System.err.println("[ControladorProfesor] Todos los campos son obligatorios.");
            return false;
        }

        // Validación de duplicados por ID
        if (buscarProfesor(idProfesor) != null) {
            System.err.println("[ControladorProfesor] Ya existe un profesor con el ID " + idProfesor);
            return false;
        }

        Profesor nuevo = new Profesor(
            idProfesor.trim(),
            nombre.trim(),
            apellido.trim()
        );

        return profesorDatos.guardar(nuevo);
    }

    // 2. Listar todos los profesores
    public List<Profesor> listarProfesores() {
        return profesorDatos.obtenerTodos();
    }

    // 3. Buscar profesor por ID
    public Profesor buscarProfesor(String idProfesor) {
        if (idProfesor == null || idProfesor.trim().isEmpty()) {
            return null;
        }
        return profesorDatos.buscarPorIdProfesor(idProfesor.trim());
    }

    // 4. Modificar datos de un profesor existente
    public boolean modificarProfesor(String idProfesor, String nombre, String apellido) {
        // Validación de campos obligatorios
        if (idProfesor == null || idProfesor.trim().isEmpty() ||
            nombre == null || nombre.trim().isEmpty() ||
            apellido == null || apellido.trim().isEmpty()) {
            
            System.err.println("[ControladorProfesor] Todos los campos son obligatorios para modificar.");
            return false;
        }

        // Verificar que el profesor exista antes de modificar
        if (buscarProfesor(idProfesor) == null) {
            System.err.println("[ControladorProfesor] No se encontró el profesor con ID " + idProfesor);
            return false;
        }

        Profesor modificado = new Profesor(
            idProfesor.trim(),
            nombre.trim(),
            apellido.trim()
        );

        return profesorDatos.actualizar(modificado);
    }

    // 5. Eliminar profesor por ID
    public boolean eliminarProfesor(String idProfesor) {
        if (idProfesor == null || idProfesor.trim().isEmpty()) {
            return false;
        }
        return profesorDatos.eliminar(idProfesor.trim());
    }
}