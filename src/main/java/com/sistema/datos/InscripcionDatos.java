//Autor: Luis
package com.sistema.datos;

import com.sistema.modelos.Inscripcion;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class InscripcionDatos {

    private static final String RUTA_ARCHIVO = "archivos/inscripciones.txt";
    
    public InscripcionDatos(){
        crearArchivoSiNoExiste();   
    }
    
    //Se valida que haya un archivo creado, si no se crea uno en blanco para evitar errores
    private void crearArchivoSiNoExiste(){
        File archivo = new File(RUTA_ARCHIVO);
        File carpeta = archivo.getParentFile();
        
        if (carpeta !=null && !carpeta.exists()){
            carpeta.mkdirs();
        }
        
        if (!archivo.exists()){
            try {
                archivo.createNewFile();
            } catch (IOException e) {
                System.err.println("[InscripcionDatos] Error al crear el archivo de isncripciones: " + e.getMessage());
            }
        }    
    }
    
        //Guardar
    public boolean guardar(Inscripcion inscripcion){
        // 1. Corrección del error de sintaxis aquí
        if (inscripcion == null || inscripcion.getIdInscripcion() == null || 
            inscripcion.getCarnetEstudiante() == null || inscripcion.getCodigoCurso() == null){
            System.err.println("[InscripcionDatos] La inscripcion y sus atributos no pueden ser nulos.");
            return false;
        }
        
        // 2. Validar que no exista el ID
        if (buscarPorId(inscripcion.getIdInscripcion()) != null) {
            System.err.println("[InscripcionDatos] Ya existe una inscripcion con el ID " + inscripcion.getIdInscripcion());
            return false;
        }
        
        // 3. VALIDACIÓN EXTRA IMPORTANTE: Evitar que el mismo estudiante se inscriba 2 veces al mismo curso
        List<Inscripcion> todas = obtenerTodos();
        for (Inscripcion i : todas) {
            if (i.getCarnetEstudiante().equalsIgnoreCase(inscripcion.getCarnetEstudiante().trim()) &&
                i.getCodigoCurso().equalsIgnoreCase(inscripcion.getCodigoCurso().trim())) {
                System.err.println("[InscripcionDatos] El estudiante ya está inscrito en este curso.");
                return false;
            }
        }

        // 4. Escritura forzando codificación UTF-8
        try (BufferedWriter bw = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(RUTA_ARCHIVO, true), StandardCharsets.UTF_8))) {

            String linea = inscripcion.getIdInscripcion().trim() + "|" + inscripcion.getCarnetEstudiante().trim() + "|" + inscripcion.getCodigoCurso().trim();
            bw.write(linea);
            bw.newLine();
            return true;
        } catch (IOException e) {
            System.err.println("[InscripcionDatos] Error al guardar Inscripcion: " + e.getMessage());
            return false;
        }
    }
    
     // Obtener todos
    public List<Inscripcion> obtenerTodos() {
        List<Inscripcion> inscripciones = new ArrayList<>();
        File archivo = new File(RUTA_ARCHIVO);

        if (!archivo.exists()) {
            return inscripciones;
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
                if (datos.length >= 3) {
                    String idInscripcion = datos[0].trim();
                    String carnetEstudiante = datos[1].trim();
                    String codigoCurso = datos[2].trim();
                    Inscripcion inscripcion = new Inscripcion(idInscripcion, carnetEstudiante, codigoCurso);
                    inscripciones.add(inscripcion);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer inscripcion: " + e.getMessage());
        }

        return inscripciones;      
    }
    
    // Buscar por Id
    public Inscripcion buscarPorId(String idInscripcion) {
        if (idInscripcion == null || idInscripcion.trim().isEmpty()) {
            return null;
        }
        List<Inscripcion> inscripciones = obtenerTodos();
        for (Inscripcion inscripcion : inscripciones) {
            if (inscripcion.getIdInscripcion().trim().equalsIgnoreCase(idInscripcion.trim())) {
                return inscripcion;
            }
        }
        return null;
    }
    
    // Actualizar curso 
    public boolean actualizar(Inscripcion inscripcionModificada) {
        if (inscripcionModificada == null || inscripcionModificada.getIdInscripcion() == null || inscripcionModificada.getCarnetEstudiante() == null) {
            return false;
        }

        List<Inscripcion> inscripciones = obtenerTodos();
        boolean encontrado = false;

        for (int i = 0; i < inscripciones.size(); i++) {
            if (inscripciones.get(i).getIdInscripcion().trim().equalsIgnoreCase(inscripcionModificada.getIdInscripcion().trim())) {
                inscripciones.set(i, inscripcionModificada);
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            return false;
        }

        return guardarTodos(inscripciones);
    }
    
    // Método auxiliar para sobreescribir el archivo con UTF-8
    private boolean guardarTodos(List<Inscripcion> inscripciones) {
        try (BufferedWriter bw = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(RUTA_ARCHIVO, false), StandardCharsets.UTF_8))) {

            for (Inscripcion inscripcion : inscripciones) {
                String linea = inscripcion.getIdInscripcion().trim() + "|" + inscripcion.getCarnetEstudiante().trim() + "|" + inscripcion.getCodigoCurso().trim();
                bw.write(linea);
                bw.newLine();
            }
            return true;
        } catch (IOException e) {
            System.err.println("[InscripcionDatos] Error al reescribir archivo de inscripciones: " + e.getMessage());
            return false;
        }
    }
    
    // metodo para asignar cursos
    public List<Inscripcion> buscarPorCurso(String codigoCurso) {
        List<Inscripcion> resultado = new ArrayList<>();
        if (codigoCurso == null || codigoCurso.trim().isEmpty()) {
            return resultado;
        }
        
        List<Inscripcion> lista = obtenerTodos();
        for (Inscripcion ins : lista) {
            if (ins.getCodigoCurso().equalsIgnoreCase(codigoCurso.trim())) {
                resultado.add(ins);
            }
        }
        return resultado;
    }
    
    // Eliminar
    public boolean eliminar(String idInscripcion) {
        if (idInscripcion == null || idInscripcion.trim().isEmpty()) {
            return false;
        }
        List<Inscripcion> lista = obtenerTodos();
        boolean removido = lista.removeIf(ins -> ins.getIdInscripcion().equalsIgnoreCase(idInscripcion.trim()));
        
        if (removido) {
            return guardarTodos(lista);
        }
        return false;
    }
}
