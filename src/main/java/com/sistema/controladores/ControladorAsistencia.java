/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sistema.controladores;

import com.sistema.datos.AsistenciaDatos;
import com.sistema.modelos.Asistencia;
import java.util.List;
/**
 *
 * @author CompuFire
 */
public class ControladorAsistencia {
     
   
    private final AsistenciaDatos asistenciaDatos;
    private final ControladorCurso controladorCurso;
    private final ControladorEstudiante controladorEstudiante;

    public ControladorAsistencia() {
        this.asistenciaDatos = new AsistenciaDatos();
        this.controladorCurso = new ControladorCurso();
        this.controladorEstudiante = new ControladorEstudiante();
    }

    // 1. Registrar nueva asistencia validando la existencia del estudiante y del curso
    public boolean registrarAsistencia(String idAsistencia, String carnet, String codigoCurso, String fecha, String estado) {
        // Validación de campos nulos o vacíos
        if (idAsistencia == null || idAsistencia.trim().isEmpty() ||
            carnet == null || carnet.trim().isEmpty() ||
            codigoCurso == null || codigoCurso.trim().isEmpty() ||
            fecha == null || fecha.trim().isEmpty() ||
            estado == null || estado.trim().isEmpty()) {
            System.out.println("[ControladorAsistencia] Todos los campos son obligatorios para poder guardar ");
            return false;
        }
        
          // Validación de duplicados por ID de asistencia
        if (buscarAsistencia(idAsistencia) != null) {
            System.err.println("[ControladorAsistencia] Ya existe una asistencia con el ID " + idAsistencia);
            return false;
        }

        // Validacion de que el estudiante si existe 
        if (controladorEstudiante.buscarEstudiante(carnet.trim()) == null) {
            System.err.println("[ControladorAsistencia] Error: El carné " + carnet + " no existe.");
            return false;
        }

        // Validacion de que el curso si existe
        if (controladorCurso.buscarCurso(codigoCurso.trim()) == null) {
            System.err.println("[ControladorAsistencia] Error: El curso " + codigoCurso + " no existe.");
            return false;
        }

        Asistencia nueva = new Asistencia(
            idAsistencia.trim(),
            carnet.trim(),
            codigoCurso.trim(),
            fecha.trim(),
            estado.trim()
        );

        return asistenciaDatos.guardar(nueva);
    }

    // 2. Listado de todas las asistencias
    public List<Asistencia> listarAsistencias() {
        return asistenciaDatos.obtenerTodos();
    }

    // 3. Buscar asistencia por ID
    public Asistencia buscarAsistencia(String idAsistencia) {
        if (idAsistencia == null || idAsistencia.trim().isEmpty()) {
            System.out.println("[ControladorAsistencia] El ID de busqueda esta vacio");
            return null;
        }
        return asistenciaDatos.buscarPorId(idAsistencia.trim());
    }

    // 4. Modificar registro de asistencia validando que sigan existiendo el estudiante y el curso
    public boolean modificarAsistencia(String idAsistencia, String carnet, String codigoCurso, String fecha, String estado) {
        if (idAsistencia == null || idAsistencia.trim().isEmpty() ||
            carnet == null || carnet.trim().isEmpty() ||
            codigoCurso == null || codigoCurso.trim().isEmpty() ||
            fecha == null || fecha.trim().isEmpty() ||
            estado == null || estado.trim().isEmpty()) {
            System.out.println("[ControladorAsistencia]  Todos los campos son obligatorios para poder guardar");
            return false;
        }
        
         // Verificar que la asistencia sí exista antes de modificar
        if (buscarAsistencia(idAsistencia) == null) {
            System.err.println("[ControladorAsistencia] No se encontró la asistencia con ID " + idAsistencia);
            return false;
        }

         // Verificar existencia relacional de estudiantea modificar 
        if (controladorEstudiante.buscarEstudiante(carnet.trim()) == null) {
            System.err.println("[ControladorAsistencia] Error: El estudiante con carné " + carnet + " no existe.");
            return false;
        }

        // Verificar existencia relacional de curso a modificar 
        if (controladorCurso.buscarCurso(codigoCurso.trim()) == null) {
            System.err.println("[ControladorAsistencia] Error: El curso con código " + codigoCurso + " no existe.");
            return false;
        }
        Asistencia asistencia = new Asistencia(
            idAsistencia.trim(),
            carnet.trim(),
            codigoCurso.trim(),
            fecha.trim(),
            estado.trim()
        );
        return asistenciaDatos.actualizar(asistencia);
    }

    // 5. Eliminar registro por ID
    public boolean eliminarAsistencia(String idAsistencia) {
        if (idAsistencia == null || idAsistencia.trim().isEmpty()) {
            System.out.println("[ControladorAsistencia] Necesita colocar el ID para poder eliminar la asistencia");
            return false;
        }
        return asistenciaDatos.eliminar(idAsistencia.trim());
    }
}
