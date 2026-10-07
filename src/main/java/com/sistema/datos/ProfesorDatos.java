//Autor: Luis  
package com.sistema.datos;

import com.sistema.modelos.Profesor;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.util.List;
import java.util.ArrayList;
import java.io.InputStreamReader;

public class ProfesorDatos{

    private final String RUTA_ARCHIVO = "archivos/profesores.txt";
   
    //Metodo constructor
    public ProfesorDatos(){
        crearArchivoSiNoExiste();
    }
   
    //Se valida que haya un archivo creado, si no se crea uno en blanco para evitar errores
    private void crearArchivoSiNoExiste(){
        try {
            File carpeta = new File("archivos");
            if (!carpeta.exists()){
                carpeta.mkdirs();
            }
            File archivo = new File(RUTA_ARCHIVO);
            if (!archivo.exists()){
                archivo.createNewFile();
            }
        }catch (IOException e){
            System.err.println("Error al verificar o crear el archivo de profesores: " + e.getMessage());
        }
    }
    
    //Guardar
    public boolean guardar(Profesor profesor){
        if (profesor == null || profesor.getIdProfesor() == null || profesor.getIdProfesor().trim().isEmpty()){
            System.err.println("Error: el profesoro su Id no pueden ser nuloso vacios.");
            return false;
        }
        
        if (buscarPorIdProfesor(profesor.getIdProfesor()) !=null){
            System.err.println("Error: Ya existe un profesor con el Id" + profesor.getIdProfesor());
            return false;
        }
        
        try (BufferedWriter bw = new BufferedWriter(
                new OutputStreamWriter(
                    new FileOutputStream(RUTA_ARCHIVO, true),
                    StandardCharsets.UTF_8))){
            bw.write(profesor.toString());
            bw.newLine();
            return true;
        }catch (IOException e){
            System.err.println("Error al guardar profesor: " + e.getMessage());
            return false;
        }
    }

    //Obtener todos
    public List<Profesor> obtenerTodos(){
        List<Profesor> lista = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(RUTA_ARCHIVO),
                        StandardCharsets.UTF_8))){
            String linea;
            while ((linea = br.readLine()) !=null){
                if (!linea.trim().isEmpty()){
                    String[] datos = linea.split("\\|");
                    if (datos.length >=3){
                        Profesor est = new Profesor(datos[0].trim(), datos[1].trim(), datos[2].trim());
                        lista.add(est);
                    }
                }
            }
        }catch (IOException e){
            System.err.println("Error al obtener profesor: " + e.getMessage());
        }
        return lista;
    }
    
    //Buscar por Id Profesor
    public Profesor buscarPorIdProfesor(String idProfesor){
        if (idProfesor == null || idProfesor.trim().isEmpty()){
            return null;
        }
        
        List<Profesor> profesores = obtenerTodos();
        for  (Profesor est : profesores){
            if (est.getIdProfesor().equalsIgnoreCase(idProfesor.trim())){
                return est;
            }
        }
        return null;
    }
    
    //Actualizar
    public boolean actualizar(Profesor profesorModificado){
        if (profesorModificado == null || profesorModificado.getIdProfesor() == null || profesorModificado.getIdProfesor().trim().isEmpty()){
            System.err.println("Error: los datos del profesor a actualizar no son válidos.");
            return false;
        }
        
        List<Profesor> profesores = obtenerTodos();
        boolean encontrado = false;
        
        for (int i = 0; i < profesores.size(); i++){
            if (profesores.get(i).getIdProfesor().equalsIgnoreCase(profesorModificado.getIdProfesor().trim())){
                profesores.set(i, profesorModificado);
                encontrado = true;
                break;
            }
        }
        
        if (encontrado){
            return guardarTodos(profesores);
        }
        return false;
    }
    
    //Eliminar
    public boolean eliminar(String idProfesor){
        if (idProfesor == null || idProfesor.trim().isEmpty()){
            System.err.println("Error: el ID a eliminar no puede ser nulo o vacio.");
            return false;
        }
        
        List<Profesor> profesores = obtenerTodos();
        boolean removido = profesores.removeIf(est -> est.getIdProfesor().equalsIgnoreCase(idProfesor.trim()));
        
        if (removido){
            return guardarTodos(profesores);
        }
        return false;
    }
    
    //Sobreescribe todo el archivo .txt con la nueva lista que recibe
    private boolean guardarTodos(List<Profesor> lista){
        try (BufferedWriter bw = new BufferedWriter(
                new OutputStreamWriter(
                        new FileOutputStream(RUTA_ARCHIVO, false),
                        StandardCharsets.UTF_8))){
            for (Profesor est : lista){
                bw.write(est.toString());
                bw.newLine();
            }
            return true;
        }catch (IOException e){
            System.err.println("Error al escribir el arhivo de profesores " + e.getMessage());
            return false;
        }
    }

}   