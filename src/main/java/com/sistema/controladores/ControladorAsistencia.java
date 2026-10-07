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

    public ControladorAsistencia() {
        this.asistenciaDatos = new AsistenciaDatos();
    }

    // 1. Registrar una nueva asistencia con validaciones de campos obligatorios
    public boolean registrarAsistencia(String idAsistencia, String carnet, String codigoCurso, String fecha, String estado) {
        if (idAsistencia == null || idAsistencia.trim().isEmpty() ||
            carnet == null || carnet.trim().isEmpty() ||
            codigoCurso == null || codigoCurso.trim().isEmpty() ||
            fecha == null || fecha.trim().isEmpty() ||
            estado == null || estado.trim().isEmpty()) {
            
            return false;
        }

        Asistencia nueva = new Asistencia(
            idAsistencia.trim(),
            carnet.trim(),
            codigoCurso.trim(),
            fecha.trim(),
            estado.trim()
        );

        // asistenciaDatos.guardar ya valida si el ID existe y retorna false si es duplicado
        return asistenciaDatos.guardar(nueva);
    }

    // 2. Listar todas las asistencias desde el archivo .txt
    public List<Asistencia> listarAsistencias() {
        return asistenciaDatos.obtenerTodos();
    }

    // 3. Buscar asistencia por su ID
    public Asistencia buscarAsistencia(String idAsistencia) {
        if (idAsistencia == null || idAsistencia.trim().isEmpty()) {
            return null;
        }
        return asistenciaDatos.buscarPorId(idAsistencia.trim());
    }

    // 4. Modificar registro de asistencia existente (con validación de nulos completa)
    public boolean modificarAsistencia(String idAsistencia, String carnet, String codigoCurso, String fecha, String estado) {
        if (idAsistencia == null || idAsistencia.trim().isEmpty() ||
            carnet == null || carnet.trim().isEmpty() ||
            codigoCurso == null || codigoCurso.trim().isEmpty() ||
            fecha == null || fecha.trim().isEmpty() ||
            estado == null || estado.trim().isEmpty()) {
            
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

    // 5. Eliminar registro de asistencia por ID
    public boolean eliminarAsistencia(String idAsistencia) {
        if (idAsistencia == null || idAsistencia.trim().isEmpty()) {
            return false;
        }
        return asistenciaDatos.eliminar(idAsistencia.trim());
    }
}
