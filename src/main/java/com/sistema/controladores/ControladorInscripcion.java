//Autor: luis
package com.sistema.controladores;

import com.sistema.datos.InscripcionDatos;
import com.sistema.modelos.Inscripcion;
import java.util.List;

public class ControladorInscripcion {
    
    private final InscripcionDatos inscripcionDatos;
    private final ControladorEstudiante controladorEstudiante;
    private final ControladorCurso controladorCurso;

    public ControladorInscripcion() {
        this.inscripcionDatos = new InscripcionDatos();
        this.controladorEstudiante = new ControladorEstudiante();
        this.controladorCurso = new ControladorCurso();
    }

    // 1. Registrar una nueva inscripción validando que existan el estudiante y el curso
    public boolean registrarInscripcion(String carnet, String codigoCurso) {
        // Validación de campos nulos o vacíos
        if (carnet == null || carnet.trim().isEmpty() || 
            codigoCurso == null || codigoCurso.trim().isEmpty()) {
            System.err.println("[ControladorInscripcion] El carnet y el código del curso son obligatorios.");
            return false;
        }

        // Validar que el estudiante exista en el sistema
        if (controladorEstudiante.buscarEstudiante(carnet.trim()) == null) {
            System.err.println("[ControladorInscripcion] Error: El estudiante con carnet " + carnet + " no existe.");
            return false;
        }

        // Validar que el curso exista en el sistema
        if (controladorCurso.buscarCurso(codigoCurso.trim()) == null) {
            System.err.println("[ControladorInscripcion] Error: El curso con código " + codigoCurso + " no existe.");
            return false;
        }

        // Generar un ID único para la inscripción basado en el tiempo actual
        // (Así evitamos que se dupliquen los IDs si inscriben a muchos rápido)
        String idInscripcion = "INS-" + System.currentTimeMillis();

        Inscripcion nueva = new Inscripcion(idInscripcion, carnet.trim(), codigoCurso.trim());
        
        // El método guardar de InscripcionDatos ya tiene la validación interna para no duplicar
        return inscripcionDatos.guardar(nueva);
    }

    // 2. Listar todas las inscripciones del sistema
    public List<Inscripcion> listarTodas() {
        return inscripcionDatos.obtenerTodos();
    }

    // 3. Buscar una inscripción por su ID
    public Inscripcion buscarPorId(String idInscripcion) {
        if (idInscripcion == null || idInscripcion.trim().isEmpty()) {
            return null;
        }
        return inscripcionDatos.buscarPorId(idInscripcion.trim());
    }

    // 4. MÉTODO PARA TU COMPAÑERO: Listar todos los alumnos inscritos en un curso específico
    public List<Inscripcion> listarPorCurso(String codigoCurso) {
        if (codigoCurso == null || codigoCurso.trim().isEmpty()) {
            return null;
        }
        return inscripcionDatos.buscarPorCurso(codigoCurso.trim());
    }

    // 5. Eliminar una inscripción por su ID (Útil para la pestaña de Administrador)
    public boolean eliminarInscripcion(String idInscripcion) {
        if (idInscripcion == null || idInscripcion.trim().isEmpty()) {
            System.err.println("[ControladorInscripcion] El ID de la inscripción es obligatorio para eliminar.");
            return false;
        }
        return inscripcionDatos.eliminar(idInscripcion.trim());
    }
}
    
