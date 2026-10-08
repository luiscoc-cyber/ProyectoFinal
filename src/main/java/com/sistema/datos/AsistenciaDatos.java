/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sistema.datos;

import com.sistema.modelos.Asistencia;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Angel
 */
public class AsistenciaDatos {

    private static final String RUTA_ARCHIVO = "archivos/asistencias.txt";

    public AsistenciaDatos() {
        crearArchivoSiNoExiste();
    }

    // Se valida que haya un archivo creado, si no se crea uno en blanco para evitar errores
    private void crearArchivoSiNoExiste() {
        File archivo = new File(RUTA_ARCHIVO);
        File carpeta = archivo.getParentFile();

        if (carpeta != null && !carpeta.exists()) {
            carpeta.mkdirs();
        }

        if (!archivo.exists()) {
            try {
                archivo.createNewFile();
            } catch (IOException e) {
                System.err.println("[AsistenciaDatos] Error al crear el archivo de asistencias: " + e.getMessage());
            }
        }
    }

    // Guardar (Formato: idAsistencia|carnetEstudiante|codigoCurso|fecha|estado)
    public boolean guardar(Asistencia asistencia) {
        // Validación estricta de nulos para evitar NullPointerException al hacer .trim()
        if (asistencia == null
                || asistencia.getIdAsistencia() == null
                || asistencia.getCarnetEstudiante() == null
                || asistencia.getCodigoCurso() == null
                || asistencia.getFecha() == null
                || asistencia.getEstado() == null) {
            System.err.println("[AsistenciaDatos] Todos los campos de la asistencia son obligatorios para poder guardar.");
            return false;
        }

        if (buscarPorId(asistencia.getIdAsistencia()) != null) {
            System.err.println("[AsistenciaDatos] Ya existe una asistencia con el ID " + asistencia.getIdAsistencia());
            return false;
        }

        // Escritura forzando codificación UTF-8
        try (BufferedWriter bw = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(RUTA_ARCHIVO, true), StandardCharsets.UTF_8))) {

            String linea = asistencia.getIdAsistencia().trim() + "|"
                    + asistencia.getCarnetEstudiante().trim() + "|"
                    + asistencia.getCodigoCurso().trim() + "|"
                    + asistencia.getFecha().trim() + "|"
                    + asistencia.getEstado().trim();
            bw.write(linea);
            bw.newLine();
            return true;
        } catch (IOException e) {
            System.err.println("[AsistenciaDatos] Error al guardar asistencia: " + e.getMessage());
            return false;
        }
    }

    // Obtener todos
    public List<Asistencia> obtenerTodos() {
        List<Asistencia> asistencias = new ArrayList<>();
        File archivo = new File(RUTA_ARCHIVO);

        if (!archivo.exists()) {
            return asistencias;
        }

        // Lectura forzando codificación UTF-8
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(RUTA_ARCHIVO), StandardCharsets.UTF_8))) {

            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) {
                    continue;
                }

                String[] datos = linea.split("\\|");
                if (datos.length >= 5) {
                    String id = datos[0].trim();
                    String carnet = datos[1].trim();
                    String codigoCurso = datos[2].trim();
                    String fecha = datos[3].trim();
                    String estado = datos[4].trim();

                    Asistencia asistencia = new Asistencia(id, carnet, codigoCurso, fecha, estado);
                    asistencias.add(asistencia);
                }
            }
        } catch (IOException e) {
            System.err.println("[AsistenciaDatos] Error al leer asistencias: " + e.getMessage());
        }

        return asistencias;
    }

    // Buscar por ID de Asistencia
    public Asistencia buscarPorId(String idAsistencia) {
        if (idAsistencia == null || idAsistencia.trim().isEmpty()) {
            return null;
        }
        List<Asistencia> asistencias = obtenerTodos();
        for (Asistencia a : asistencias) {
            if (a.getIdAsistencia().trim().equalsIgnoreCase(idAsistencia.trim())) {
                return a;
            }
        }
        return null;
    }

    // Actualizar
    public boolean actualizar(Asistencia asistenciaModificada) {
        if (asistenciaModificada == null || asistenciaModificada.getIdAsistencia() == null) {
            return false;
        }

        List<Asistencia> asistencias = obtenerTodos();
        boolean encontrado = false;

        for (int i = 0; i < asistencias.size(); i++) {
            if (asistencias.get(i).getIdAsistencia().trim().equalsIgnoreCase(asistenciaModificada.getIdAsistencia().trim())) {
                asistencias.set(i, asistenciaModificada);
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            return false;
        }

        return guardarTodos(asistencias);
    }

    // Eliminar
    public boolean eliminar(String idAsistencia) {
        if (idAsistencia == null || idAsistencia.trim().isEmpty()) {
            return false;
        }
        List<Asistencia> asistencias = obtenerTodos();
        boolean eliminado = asistencias.removeIf(a -> a.getIdAsistencia().trim().equalsIgnoreCase(idAsistencia.trim()));

        if (!eliminado) {
            return false;
        }

        return guardarTodos(asistencias);
    }

    // Método auxiliar para reescribir el archivo completo con UTF-8
    private boolean guardarTodos(List<Asistencia> asistencias) {
        try (BufferedWriter bw = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(RUTA_ARCHIVO, false), StandardCharsets.UTF_8))) {

            for (Asistencia a : asistencias) {
                String linea = a.getIdAsistencia().trim() + "|"
                        + a.getCarnetEstudiante().trim() + "|"
                        + a.getCodigoCurso().trim() + "|"
                        + a.getFecha().trim() + "|"
                        + a.getEstado().trim();
                bw.write(linea);
                bw.newLine();
            }
            return true;
        } catch (IOException e) {
            System.err.println("[AsistenciaDatos] Error al reescribir archivo de asistencias: " + e.getMessage());
            return false;
        }
    }
}
