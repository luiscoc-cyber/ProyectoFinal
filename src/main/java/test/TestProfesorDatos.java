package com.sistema.datos;

import com.sistema.modelos.Profesor;

public class TestProfesorDatos {
    
    public static void main(String[] args) {
        System.out.println("--- INICIANDO PRUEBAS DE PROFESOR DATOS ---");
        
        ProfesorDatos datos = new ProfesorDatos();
        
        // 1. PRUEBA GUARDAR
        System.out.println("\n1. Probando guardar...");
        Profesor p1 = new Profesor("P001", "Carlos", "Ruiz");
        Profesor p2 = new Profesor("P002", "Ana", "Martínez");
        
        System.out.println("Guardando a Carlos: " + datos.guardar(p1));
        System.out.println("Guardando a Ana: " + datos.guardar(p2));
        
        // Intentar guardar a Carlos otra vez (debe dar error de duplicado)
        System.out.println("Intentando guardar a Carlos de nuevo (debe ser false): " + datos.guardar(p1));
        
        // 2. PRUEBA OBTENER TODOS
        System.out.println("\n2. Probando obtenerTodos...");
        for (Profesor prof : datos.obtenerTodos()) {
            System.out.println("Encontrado: " + prof.toString());
        }
        
        // 3. PRUEBA BUSCAR POR ID
        System.out.println("\n3. Probando buscarPorIdProfesor...");
        Profesor buscado = datos.buscarPorIdProfesor("P002");
        if (buscado != null) {
            System.out.println("Encontrado: " + buscado.getNombre() + " " + buscado.getApellido());
            
            // 4. PRUEBA ACTUALIZAR (Solo si lo encontró)
            System.out.println("\n4. Probando actualizar...");
            buscado.setNombre("Ana María");
            buscado.setApellido("Martínez López");
            System.out.println("Actualizando a Ana: " + datos.actualizar(buscado));
            
        } else {
            System.out.println("No se encontró el profesor con ID P002.");
        }
        
        // 5. PRUEBA ELIMINAR
        System.out.println("\n5. Probando eliminar...");
        System.out.println("Eliminando a Carlos (P001): " + datos.eliminar("P001"));
        
        // 6. VERIFICACIÓN FINAL
        System.out.println("\n6. Estado final del archivo:");
        for (Profesor prof : datos.obtenerTodos()) {
            System.out.println(prof.toString());
        }
        
        System.out.println("\n--- PRUEBAS FINALIZADAS ---");
    }
}