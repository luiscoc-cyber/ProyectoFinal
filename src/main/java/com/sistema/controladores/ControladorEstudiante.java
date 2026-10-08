/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sistema.controladores;

import com.sistema.datos.EstudianteDatos;
import com.sistema.modelos.Estudiante;
import java.util.List;
/**
 *
 * @author CompuFire
 */
public class ControladorEstudiante {
    
     private final EstudianteDatos estudianteDatos;

    public ControladorEstudiante() {
        this.estudianteDatos = new EstudianteDatos();
    }

    // 1. Registrar un nuevo estudiante
    public boolean registrarEstudiante(String carnet, String nombre, String apellido) {
        // Validación de campos obligatorios
        if (carnet == null || carnet.trim().isEmpty() ||
            nombre == null || nombre.trim().isEmpty() ||
            apellido == null || apellido.trim().isEmpty()) {
            
            System.err.println("[ControladorEstudiante] Todos los campos son obligatorios.");
            return false;
        }

        // Validación de duplicados por carnet
        if (buscarEstudiante(carnet) != null) {
            System.err.println("[ControladorEstudiante] Ya existe un estudiante con el carnet " + carnet);
            return false;
        }

        Estudiante nuevo = new Estudiante(
            carnet.trim(),
            nombre.trim(),
            apellido.trim()
        );

        return estudianteDatos.guardar(nuevo);
    }

    // 2. Listar todos los estudiantes
    public List<Estudiante> listarEstudiantes() {
        return estudianteDatos.obtenerTodos();
    }

    // 3. Buscar estudiante por carnet
    public Estudiante buscarEstudiante(String carnet) {
        if (carnet == null || carnet.trim().isEmpty()) {
            return null;
        }
        return estudianteDatos.buscarPorCarnet(carnet.trim());
    }

    // 4. Modificar datos de un estudiante existente
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

    // 5. Eliminar estudiante por carnet
    public boolean eliminarEstudiante(String carnet) {
        if (carnet == null || carnet.trim().isEmpty()) {
            return false;
        }
        return estudianteDatos.eliminar(carnet.trim());
    }
    
}
