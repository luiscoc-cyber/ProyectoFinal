package com.sistema.datos;

import com.sistema.modelos.Estudiante;

public class TestEstudianteDatos {

    public static void main(String[] args){
        System.out.println("---INICIANDO PRUEBAS DE ESTUDIANTE DATOS---");
    
        EstudianteDatos datos = new EstudianteDatos();
    
        //1. Prueba guardar
        System.out.println("\n1. Probando guardar...");
        Estudiante e1 = new Estudiante("2023001", "Juan", "Perez");
        Estudiante e2 = new Estudiante("2023002", "María", "Gómez");
      
        System.out.println("Guardando a Juan: " + datos.guardar(e1));
        System.out.println("Guardando a María: " + datos.guardar(e2));
        
        System.out.println("Intentando guardar a Juan de nuevo: " + datos.guardar(e1));
        
        //2. Prueba obtener todos
        System.out.println("\n1. Probando ObtenerTodos...");
        for (Estudiante est : datos.obtenerTodos()){
            System.out.println("Encontrado: " + est.toString());
        }
        
        // 3. PRUEBA BUSCAR POR CARNET
        System.out.println("\n3. Probando buscarPorCarnet...");
        Estudiante buscado = datos.buscarPorCarnet("2023002");
        if (buscado != null) {
            System.out.println("Encontrado: " + buscado.getNombre() + " " + buscado.getApellido());
            buscado.setNombre("María Fernanda");
            buscado.setApellido("Gómez Ruiz");
            System.out.println("Actualizando a María: " + datos.actualizar(buscado));
        } else {
            System.out.println("No se encontró.");
        }   
        
        // 5. PRUEBA ELIMINAR
        System.out.println("\n5. Probando eliminar...");
        System.out.println("Eliminando a Juan: " + datos.eliminar("2023001"));

        // 6. VERIFICACIÓN FINAL
        System.out.println("\n6. Estado final del archivo:");
        for (Estudiante est : datos.obtenerTodos()) {
            System.out.println(est.toString());
        }

        System.out.println("\n--- PRUEBAS FINALIZADAS ---");
        
    }
    
}