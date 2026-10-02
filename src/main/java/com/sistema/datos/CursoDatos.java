/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sistema.datos;

import com.sistema.modelos.Curso;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CursoDatos {

    private final String RUTA_ARCHIVO = "archivos/cursos.txt";

    public CursoDatos() {
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
                System.out.println("Error al crear el archivo de cursos: " + e.getMessage());
            }
        }
    }

    // Guardar (Formato estandarizado: codigo|nombre)
    public boolean guardar(Curso curso) {
        if (curso == null || curso.getCodigoCurso() == null) {
            return false;
        }

        if (buscarPorCodigo(curso.getCodigoCurso()) != null) {
            System.out.println("Error: Ya existe un curso con el código " + curso.getCodigoCurso());
            return false;
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(RUTA_ARCHIVO, true))) {
            // Guardamos directamente en formato limpio separador pipe sin depender de toString()
            String linea = curso.getCodigoCurso().trim() + "|" + curso.getNombreCurso().trim();
            bw.write(linea);
            bw.newLine();
            return true;
        } catch (IOException e) {
            System.out.println("Error al guardar curso: " + e.getMessage());
            return false;
        }
    }

    // Obtener todos
    public List<Curso> obtenerTodos() {
        List<Curso> cursos = new ArrayList<>();
        File archivo = new File(RUTA_ARCHIVO);

        if (!archivo.exists()) {
            return cursos;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(RUTA_ARCHIVO))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) {
                    continue;
                }

                String[] datos = linea.split("\\|");
                if (datos.length >= 2) {
                    // Limpiamos espacios con .trim() para evitar errores al comparar
                    String codigo = datos[0].trim();
                    String nombre = datos[1].trim();
                    Curso curso = new Curso(codigo, nombre);
                    cursos.add(curso);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer cursos: " + e.getMessage());
        }

        return cursos;
    }

    // Buscar por Código de curso
    public Curso buscarPorCodigo(String codigoCurso) {
        if (codigoCurso == null) return null;
        List<Curso> cursos = obtenerTodos();
        for (Curso curso : cursos) {
            if (curso.getCodigoCurso().trim().equalsIgnoreCase(codigoCurso.trim())) {
                return curso;
            }
        }
        return null;
    }

    // Actualizar curso 
    public boolean actualizar(Curso cursoModificado) {
        if (cursoModificado == null || cursoModificado.getCodigoCurso() == null) {
            return false;
        }

        List<Curso> cursos = obtenerTodos();
        boolean encontrado = false;

        for (int i = 0; i < cursos.size(); i++) {
            if (cursos.get(i).getCodigoCurso().trim().equalsIgnoreCase(cursoModificado.getCodigoCurso().trim())) {
                cursos.set(i, cursoModificado);
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            return false;
        }

        return guardarListaCompleta(cursos);
    }

    // Eliminar curso
    public boolean eliminar(String codigoCurso) {
        if (codigoCurso == null) return null != null;
        List<Curso> cursos = obtenerTodos();
        boolean eliminado = cursos.removeIf(c -> c.getCodigoCurso().trim().equalsIgnoreCase(codigoCurso.trim()));

        if (!eliminado) {
            return false;
        }

        return guardarListaCompleta(cursos);
    }

    // Método auxiliar para sobreescribir el archivo
    private boolean guardarListaCompleta(List<Curso> cursos) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(RUTA_ARCHIVO, false))) {
            for (Curso curso : cursos) {
                String linea = curso.getCodigoCurso().trim() + "|" + curso.getNombreCurso().trim();
                bw.write(linea);
                bw.newLine();
            }
            return true;
        } catch (IOException e) {
            System.out.println("Error al reescribir archivo de cursos: " + e.getMessage());
            return false;
        }
    }
}