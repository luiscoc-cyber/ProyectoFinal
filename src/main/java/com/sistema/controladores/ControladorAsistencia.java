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

    // 1. Registrar asistencia validando la existencia del estudiante y del curso
    public boolean registrarAsistencia(String idAsistencia, String carnet, String codigoCurso, String fecha, String estado) {
        // Validación de campos nulos o vacíos
        if (idAsistencia == null || idAsistencia.trim().isEmpty() ||
            carnet == null || carnet.trim().isEmpty() ||
            codigoCurso == null || codigoCurso.trim().isEmpty() ||
            fecha == null || fecha.trim().isEmpty() ||
            estado == null || estado.trim().isEmpty()) {
            
            return false;
        }

        // VALIDACIÓN RELACIONAL 1: El estudiante debe existir
        if (controladorEstudiante.buscarEstudiante(carnet.trim()) == null) {
            System.err.println("[ControladorAsistencia] Error: El carné " + carnet + " no existe.");
            return false;
        }

        // VALIDACIÓN RELACIONAL 2: El curso debe existir
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

    // 2. Listar todas las asistencias
    public List<Asistencia> listarAsistencias() {
        return asistenciaDatos.obtenerTodos();
    }

    // 3. Buscar asistencia por ID
    public Asistencia buscarAsistencia(String idAsistencia) {
        if (idAsistencia == null || idAsistencia.trim().isEmpty()) {
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
            return false;
        }

        // Verificar existencia relacional
        if (controladorEstudiante.buscarEstudiante(carnet.trim()) == null ||
            controladorCurso.buscarCurso(codigoCurso.trim()) == null) {
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
            return false;
        }
        return asistenciaDatos.eliminar(idAsistencia.trim());
    }
}
