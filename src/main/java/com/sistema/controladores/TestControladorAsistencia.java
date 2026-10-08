/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sistema.controladores;

import com.sistema.modelos.Asistencia;
import java.util.List;
/**
 *
 * @author Angel
 */
public class TestControladorAsistencia {
    
    public static void main(String[] args) {
        System.out.println("======= PRUEBA DE CONTROLADOR ASISTENCIA ========");

        ControladorAsistencia controlador = new ControladorAsistencia();

        // 1. Probar registro de una nueva asistencia
        System.out.println("\n--- 1. Registrando una asistencia ---");
        boolean registrada = controlador.registrarAsistencia("A003", "2023002", "CC103", "05/10/2026", "Presente");
        System.out.println("¿Asistencia registrada exitosamente?: " + registrada);

        // 2. Probar listar todas las asistencias
        System.out.println("\n--- 2. Listando todas las asistencias guardadas ---");
        List<Asistencia> lista = controlador.listarAsistencias();
        for (Asistencia a : lista) {
            System.out.println("Asistencia: " + a);
        }

        // 3. Probar búsqueda por ID
        System.out.println("\n--- 3. Buscando asistencia con ID A003 ---");
        Asistencia buscada = controlador.buscarAsistencia("A003");
        if (buscada != null) {
            System.out.println("Asistencia encontrada: " + buscada);
        } else {
            System.out.println("Asistencia no encontrada.");
        }

        System.out.println("\n=== FIN DE LA PRUEBA ===");
    }
}
