//Autor: Luis

package com.sistema.controladores;

import com.sistema.modelos.Profesor;

public class TestControladorProfesor {
    
    public static void main(String[] args) {
        System.out.println("--- INICIANDO PRUEBAS DE CONTROLADOR PROFESOR ---");
        
        ControladorProfesor controlador = new ControladorProfesor();
        
        // 1. Validación de campos vacíos
        System.out.println("\n1. Probando registrar con campos vacíos (debe dar error):");
        boolean resultadoVacio = controlador.registrarProfesor("", "Carlos", "Ruiz");
        System.out.println("Resultado (debe ser false): " + resultadoVacio);
        
        // 2. Registro exitoso
        System.out.println("\n2. Probando registrar un profesor válido:");
        boolean registrado = controlador.registrarProfesor("P001", "Carlos", "Ruiz");
        System.out.println("Resultado (debe ser true): " + registrado);
        
        // 3. Duplicado
        System.out.println("\n3. Probando registrar el mismo ID (debe dar error):");
        boolean duplicado = controlador.registrarProfesor("P001", "Otro", "Nombre");
        System.out.println("Resultado (debe ser false): " + duplicado);
        
        // 4. Búsqueda
        System.out.println("\n4. Probando buscar por ID:");
        Profesor buscado = controlador.buscarProfesor("P001");
        if (buscado != null) {
            System.out.println("Encontrado: " + buscado.getNombre() + " " + buscado.getApellido());
        }
        
        // 5. Modificación
        System.out.println("\n5. Probando modificar profesor:");
        boolean modificado = controlador.modificarProfesor("P001", "Carlos Alberto", "Ruiz Lopez");
        System.out.println("Resultado (debe ser true): " + modificado);
        
        // 6. Verificación de modificación
        System.out.println("\n6. Verificando modificación:");
        Profesor modificadoLeido = controlador.buscarProfesor("P001");
        if (modificadoLeido != null) {
            System.out.println("Ahora es: " + modificadoLeido.getNombre() + " " + modificadoLeido.getApellido());
        }
        
        // 7. Eliminación
        System.out.println("\n7. Probando eliminar profesor:");
        boolean eliminado = controlador.eliminarProfesor("P001");
        System.out.println("Resultado (debe ser true): " + eliminado);
        
        // 8. Lista final
        System.out.println("\n8. Lista final de profesores:");
        if (controlador.listarProfesores().isEmpty()) {
            System.out.println("La lista está vacía (correcto).");
        } else {
            for (Profesor p : controlador.listarProfesores()) {
                System.out.println(p.toString());
            }
        }
        
        System.out.println("\n--- PRUEBAS FINALIZADAS ---");
    }
}