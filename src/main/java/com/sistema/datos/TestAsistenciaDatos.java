/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sistema.datos;

import com.sistema.modelos.Asistencia;
import java.util.List;
/**
 *
 * @author Angel
 */
public class TestAsistenciaDatos {
      public static void main(String[] args) {
        System.out.println("--- INICIANDO PRUEBAS DE ASISTENCIA DATOS ---");

        AsistenciaDatos asistenciaDatos = new AsistenciaDatos();

        // 1. Probando guardar
        System.out.println("\n1. Probando guardar...");
        Asistencia a1 = new Asistencia("A001", "2023001", "CC101", "02/10/2026", "Presente");
        Asistencia a2 = new Asistencia("A002", "2023002", "CC101", "02/10/2026", "Ausente");

        System.out.println("Guardando A001: " + asistenciaDatos.guardar(a1));
        System.out.println("Guardando A002: " + asistenciaDatos.guardar(a2));
        System.out.println("Intentando guardar duplicado A001: " + asistenciaDatos.guardar(a1));

        // 2. Probando obtenerTodos
        System.out.println("\n2. Probando obtenerTodos...");
        List<Asistencia> lista = asistenciaDatos.obtenerTodos();
        for (Asistencia a : lista) {
            System.out.println("Encontrado: " + a.getIdAsistencia() + " | " + a.getCarnetEstudiante() + " | " + a.getEstado());
        }

        // 3. Probando buscarPorId
        System.out.println("\n3. Probando buscarPorId...");
        Asistencia buscada = asistenciaDatos.buscarPorId("A001");
        System.out.println("Buscado A001: " + (buscada != null ? buscada.getEstado() : "No encontrado"));

        // 4. Probando actualizar
        System.out.println("\n4. Probando actualizar...");
        Asistencia aModificada = new Asistencia("A002", "2023002", "CC101", "02/10/2026", "Justificado");
        System.out.println("Actualizando A002 a Justificado: " + asistenciaDatos.actualizar(aModificada));

        // 5. Probando eliminar
        System.out.println("\n5. Probando eliminar...");
        System.out.println("Eliminando A001: " + asistenciaDatos.eliminar("A001"));

        // 6. Estado final
        System.out.println("\n6. Estado final del archivo:");
        for (Asistencia a : asistenciaDatos.obtenerTodos()) {
            System.out.println(a.getIdAsistencia() + " | " + a.getCarnetEstudiante() + " | " + a.getEstado());
        }

        System.out.println("\n--- PRUEBAS FINALIZADAS ---");
    }
}
