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

    public ControladorCurso() {
        this.cursoDatos = new CursoDatos();
    }

    // 1. Registrar nuevo curso 
public boolean registrarCurso(String codigo, String nombre) {
    if (codigo == null || codigo.trim().isEmpty() || nombre == null || nombre.trim().isEmpty()) {
        System.err.println("[ControladorCurso] Todos los campos son obligatorios para poder guardar");
        return false;
    }

    // Validación de duplicados por código 
    if (buscarCurso(codigo) != null) {
        System.err.println("[ControladorCurso] Ya existe un curso con el código " + codigo);
        return false; 
    }

    // Guarda el nuevo curso sin duplicado
    Curso nuevo = new Curso(codigo.trim(), nombre.trim());
    return cursoDatos.guardar(nuevo);
}

    // 2. Listar todos los cursos
    public List<Curso> listarCursos() {
        return cursoDatos.obtenerTodos();
    }

    // 3. Buscar curso por codigo
    public Curso buscarCurso(String codigo) {
        if (codigo == null || codigo.trim().isEmpty()) {
            return null;
        }
        return cursoDatos.buscarPorCodigo(codigo.trim());
    }

    // 4. Modificar curso 
    public boolean modificarCurso(String codigo, String nuevoNombre) {
        if (codigo == null || codigo.trim().isEmpty() || nuevoNombre == null || nuevoNombre.trim().isEmpty()) {
            System.out.println("[ControladorCurso] Es necesario llenar todos los campos para poder modificar");
            return false;
        }

        // Verificar que el curso si exista antes de modificar 
        if (buscarCurso(codigo) == null) {
            System.err.println("[ControladorCurso] No se encontró el curso con código " + codigo);
            return false;
        }

        Curso curso = new Curso(codigo.trim(), nuevoNombre.trim());
        return cursoDatos.actualizar(curso);
    }

    // 5. Eliminar curso por codigo 
    public boolean eliminarCurso(String codigo) {
        if (codigo == null || codigo.trim().isEmpty()) {
            System.out.println("[ControladorCurso] No se puede eliminar el curso sin su codigo");
            return false;
        }
        return cursoDatos.eliminar(codigo.trim());
    }
}
