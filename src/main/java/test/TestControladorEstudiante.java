//Autor: Luis
package com.sistema.controladores;

import com.sistema.modelos.Estudiante;

public class TestControladorEstudiante {
    
    public static void main(String[] args) {
        System.out.println("--- INICIANDO PRUEBAS DE CONTROLADOR ESTUDIANTE ---");
        
        ControladorEstudiante controlador = new ControladorEstudiante();
        
        // 1. PRUEBA DE VALIDACIÓN: Campos vacíos
        System.out.println("\n1. Probando registrar con campos vacíos (debe dar error):");
        boolean resultadoVacio = controlador.registrarEstudiante("", "Juan", "Perez");
        System.out.println("Resultado (debe ser false): " + resultadoVacio);
        
        // 2. PRUEBA DE VALIDACIÓN: Carnet nulo
        System.out.println("\n2. Probando registrar con carnet nulo (debe dar error):");
        boolean resultadoNulo = controlador.registrarEstudiante(null, "Juan", "Perez");
        System.out.println("Resultado (debe ser false): " + resultadoNulo);
        
        // 3. PRUEBA DE REGISTRO EXITOSO
        System.out.println("\n3. Probando registrar un estudiante válido:");
        boolean registrado = controlador.registrarEstudiante("2023001", "Juan", "Perez");
        System.out.println("Resultado (debe ser true): " + registrado);
        
        // 4. PRUEBA DE DUPLICADO
        System.out.println("\n4. Probando registrar el mismo carnet (debe dar error):");
        boolean duplicado = controlador.registrarEstudiante("2023001", "Otro", "Nombre");
        System.out.println("Resultado (debe ser false): " + duplicado);
        
        // 5. PRUEBA DE BÚSQUEDA
        System.out.println("\n5. Probando buscar por carnet:");
        Estudiante buscado = controlador.buscarEstudiante("2023001");
        if (buscado != null) {
            System.out.println("Encontrado: " + buscado.getNombre() + " " + buscado.getApellido());
        } else {
            System.out.println("No encontrado.");
        }
        
        // 6. PRUEBA DE MODIFICACIÓN
        System.out.println("\n6. Probando modificar estudiante:");
        boolean modificado = controlador.modificarEstudiante("2023001", "Juan Carlos", "Perez Gomez");
        System.out.println("Resultado (debe ser true): " + modificado);
        
        // 7. VERIFICACIÓN DE MODIFICACIÓN
        System.out.println("\n7. Verificando modificación:");
        Estudiante modificadoLeido = controlador.buscarEstudiante("2023001");
        if (modificadoLeido != null) {
            System.out.println("Ahora es: " + modificadoLeido.getNombre() + " " + modificadoLeido.getApellido());
        }
        
        // 8. PRUEBA DE ELIMINACIÓN
        System.out.println("\n8. Probando eliminar estudiante:");
        boolean eliminado = controlador.eliminarEstudiante("2023001");
        System.out.println("Resultado (debe ser true): " + eliminado);
        
        // 9. VERIFICACIÓN FINAL
        System.out.println("\n9. Lista final de estudiantes:");
        if (controlador.listarEstudiantes().isEmpty()) {
            System.out.println("La lista está vacía (correcto).");
        } else {
            for (Estudiante e : controlador.listarEstudiantes()) {
                System.out.println(e.toString());
            }
        }
        
        System.out.println("\n--- PRUEBAS FINALIZADAS ---");
    }
}