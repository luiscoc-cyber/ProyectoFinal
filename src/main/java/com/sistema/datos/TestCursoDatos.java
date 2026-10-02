package com.sistema.datos;

import com.sistema.datos.CursoDatos;
import com.sistema.modelos.Curso;
import java.util.List;

public class TestCursoDatos {

    public static void main(String[] args) {
        System.out.println("--- INICIANDO PRUEBAS DE CURSO DATOS ---");

        CursoDatos cursoDatos = new CursoDatos();

        // 1. Probando guardar
        System.out.println("\n1. Probando guardar...");
        Curso c1 = new Curso("CC101", "Programacion I");
        Curso c2 = new Curso("CC102", "Fisica I");

        System.out.println("Guardando Programacion I: " + cursoDatos.guardar(c1));
        System.out.println("Guardando Fisica I: " + cursoDatos.guardar(c2));
        System.out.println("Intentando guardar duplicado CC101: " + cursoDatos.guardar(c1));

        // 2. Probando obtenerTodos
        System.out.println("\n2. Probando obtenerTodos...");
        List<Curso> cursos = cursoDatos.obtenerTodos();
        for (Curso c : cursos) {
            System.out.println("Encontrado: " + c);
        }

        // 3. Probando buscarPorCodigo
        System.out.println("\n3. Probando buscarPorCodigo...");
        Curso buscado = cursoDatos.buscarPorCodigo("CC101");
        System.out.println("Buscado CC101: " + (buscado != null ? buscado.getNombreCurso() : "No encontrado"));

        // 4. Probando actualizar
        System.out.println("\n4. Probando actualizar...");
        Curso cModificado = new Curso("CC101", "Programacion I (Avanzada)");
        System.out.println("Actualizando CC101: " + cursoDatos.actualizar(cModificado));

        // 5. Probando eliminar
        System.out.println("\n5. Probando eliminar...");
        System.out.println("Eliminando CC102: " + cursoDatos.eliminar("CC102"));

        // 6. Estado final
        System.out.println("\n6. Estado final del archivo:");
        for (Curso c : cursoDatos.obtenerTodos()) {
            System.out.println(c);
        }

        System.out.println("\n--- PRUEBAS FINALIZADAS ---");
    }
}