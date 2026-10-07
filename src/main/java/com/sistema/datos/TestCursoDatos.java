package com.sistema.datos;

import com.sistema.modelos.Curso;

public class TestCursoDatos {

    public static void main(String[] args) {
        System.out.println("======= INICIANDO PRUEBAS DE CURSODATOS ========");

        CursoDatos cursoDatos = new CursoDatos();

        // 1. Probando guardar
        System.out.println("\n1. Probando guardar...");
        Curso c1 = new Curso("C001", "Programación I");
        Curso c2 = new Curso("C002", "Bases de Datos");

        System.out.println("Guardando C001: " + cursoDatos.guardar(c1));
        System.out.println("Guardando C002: " + cursoDatos.guardar(c2));
        
        // Intentar guardar un duplicado
        System.out.println("Intentando guardar duplicado C001: " + cursoDatos.guardar(c1));

        // 2. Probando obtenerTodos
        System.out.println("\n2. Probando obtenerTodos...");
        for (Curso c : cursoDatos.obtenerTodos()) {
            System.out.println("Encontrado: " + c.getCodigoCurso() + " | " + c.getNombreCurso());
        }

        // 3. Probando buscarPorCodigo
        System.out.println("\n3. Probando buscarPorCodigo...");
        Curso buscado = cursoDatos.buscarPorCodigo("C001");
        if (buscado != null) {
            System.out.println("Buscado C001: " + buscado.getNombreCurso());
        } else {
            System.out.println("Buscado C001: No encontrado");
        }

        // 4. Probando actualizar
        System.out.println("\n4. Probando actualizar...");
        Curso modificado = new Curso("C001", "Programación Avanzada");
        System.out.println("Actualizando C001 a " + modificado.getNombreCurso() + ": " + cursoDatos.actualizar(modificado));

        // 5. Probando eliminar
        System.out.println("\n5. Probando eliminar...");
        System.out.println("Eliminando C002: " + cursoDatos.eliminar("C002"));

        // 6. Estado final del archivo
        System.out.println("\n6. Estado final del archivo:");
        for (Curso c : cursoDatos.obtenerTodos()) {
            System.out.println(c.getCodigoCurso() + " | " + c.getNombreCurso());
        }

        System.out.println("\n--- PRUEBAS FINALIZADAS ---");
    }
}