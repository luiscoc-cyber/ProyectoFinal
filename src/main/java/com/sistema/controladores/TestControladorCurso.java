/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sistema.controladores;

import com.sistema.modelos.Curso;
import java.util.List;
/**
 *
 * @author Angel
 */
public class TestControladorCurso {
       public static void main(String[] args) {
        System.out.println("======= PRUEBA DE CONTROLADOR CURSO ========");

        ControladorCurso controlador = new ControladorCurso();

        // 1. Probar registro de un nuevo curso
        System.out.println("\n--- 1. Registrando un curso ---");
        boolean registrado = controlador.registrarCurso("CC103", "Algoritmos");
        System.out.println("¿Curso registrado exitosamente?: " + registrado);

        // 2. Probar listar todos los cursos
        System.out.println("\n--- 2. Listando todos los cursos guardados ---");
        List<Curso> lista = controlador.listarCursos();
        for (Curso c : lista) {
            System.out.println("Código: " + c.getCodigoCurso() + " | Nombre: " + c.getNombreCurso());
        }

        // 3. Probar búsqueda de curso por código
        System.out.println("\n--- 3. Buscando curso con código CC103 ---");
        Curso buscado = controlador.buscarCurso("CC103");
        if (buscado != null) {
            System.out.println("Curso encontrado: " + buscado.getNombreCurso());
        } else {
            System.out.println("Curso no encontrado.");
        }

        System.out.println("\n=== FIN DE LA PRUEBA ===");
    }
}
