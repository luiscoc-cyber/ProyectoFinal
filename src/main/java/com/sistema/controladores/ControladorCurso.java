/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sistema.controladores;
import com.sistema.datos.CursoDatos;
import com.sistema.modelos.Curso;
import java.util.List;
/**
 *
 * @author CompuFire
 */
public class ControladorCurso {
    private final CursoDatos cursoDatos;

    public ControladorCurso(){
        this.cursoDatos = new CursoDatos();
    }

    // 1. Registrar
    public boolean registrarCurso(String codigo, String nombre) {
        if (codigo == null || codigo.trim().isEmpty() || nombre == null || nombre.trim().isEmpty()) {
            System.err.println("[ControladorCurso] Código y nombre son obligatorios.");
            return false;
        }

        // Validación de duplicados
        if (buscarCurso(codigo) != null) {
            System.err.println("[ControladorCurso] El código del curso ya existe.");
            return false;
        }

        Curso nuevo = new Curso(codigo.trim(), nombre.trim());
        return cursoDatos.guardar(nuevo);
    }

    // 2. Listar
    public List<Curso> listarCursos() {
        return cursoDatos.obtenerTodos();
    }

    // 3. Buscar
    public Curso buscarCurso(String codigo) {
        if (codigo == null || codigo.trim().isEmpty()) {
            return null;
        }
        return cursoDatos.buscarPorCodigo(codigo.trim());
    }

    // 4. Modificar
    public boolean modificarCurso(String codigo, String nuevoNombre) {
        if (codigo == null || codigo.trim().isEmpty() || nuevoNombre == null || nuevoNombre.trim().isEmpty()) {
            return false;
        }
        Curso curso = new Curso(codigo.trim(), nuevoNombre.trim());
        return cursoDatos.actualizar(curso);
    }

    // 5. Eliminar
    public boolean eliminarCurso(String codigo) {
        if (codigo == null || codigo.trim().isEmpty()) {
            return false;
        }
        return cursoDatos.eliminar(codigo.trim());
    }
}
