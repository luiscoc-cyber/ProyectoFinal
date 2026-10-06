//Autor: Luis
package com.sistema.datos;

import com.sistema.modelos.Estudiante;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.nio.charset.StandardCharsets;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class EstudianteDatos{

    private final String RUTA_ARCHIVO = "archivos/estudiantes.txt";
    
    public EstudianteDatos(){
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
            System.err.println("Error al verificar o crear el archivo de estudiantes: " + e.getMessage());
        }
    }
    
    //Guardar 
    public boolean guardar(Estudiante estudiante){
        if (estudiante == null || estudiante.getCarnet() == null || estudiante.getCarnet().trim().isEmpty()){
            System.err.println("Error: el estudiante o su carnet no pueden ser nulos o vacios.");
            return false;
        }
        
        if (buscarPorCarnet(estudiante.getCarnet()) !=null){
            System.err.println("Error: Ya existe un etudiante con el carnet" + estudiante.getCarnet());
            return false;
        }
        
        try (BufferedWriter bw = new BufferedWriter(
                new OutputStreamWriter(
                        new FileOutputStream(RUTA_ARCHIVO, true),
                        StandardCharsets.UTF_8))){
            bw.write(estudiante.toString());
            bw.newLine();
            return true;
        }catch (IOException e){
            System.err.println("Error al guardar estudiante: " + e.getMessage());
            return false;
        }
    }
    
   //Obtener todos
    public List<Estudiante> obtenerTodos(){
        List<Estudiante> lista = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(RUTA_ARCHIVO),
                        StandardCharsets.UTF_8))){
            String linea;
            while ((linea = br.readLine()) !=null){
                if (!linea.trim().isEmpty()){
                    String[] datos = linea.split("\\|");
                    if (datos.length >=3){
                        Estudiante est = new Estudiante(datos[0].trim(), datos[1].trim(), datos[2].trim());
                        lista.add(est);
                    }
                }
            }
        }catch (IOException e){
            System.err.println("Error al obtener estudiate: " + e.getMessage());
        }
        return lista;
    }
    
    //Buscar por Carnet
    public Estudiante buscarPorCarnet(String carnet){ 
        if (carnet == null || carnet.trim().isEmpty()){
            return null;
        }
        
        List<Estudiante> estudiantes = obtenerTodos();
        for (Estudiante est : estudiantes){
            if (est.getCarnet().equalsIgnoreCase(carnet.trim())){
                return est;
            }
        }
        return null;
    }

    //Actualizar
    public boolean actualizar(Estudiante estudianteModificado){
        if (estudianteModificado == null || estudianteModificado.getCarnet() == null || estudianteModificado.getCarnet().trim().isEmpty()){
            System.err.println("Error: los datos del estudiante a actualizar no son validos.");
            return false;
        }
        
        List<Estudiante> estudiantes = obtenerTodos();
        boolean encontrado = false;
        
        for (int i = 0; i < estudiantes.size(); i++){
            if (estudiantes.get(i).getCarnet().equalsIgnoreCase(estudianteModificado.getCarnet().trim())){
                estudiantes.set(i, estudianteModificado);
                encontrado = true;
                break;
            }
        } 
        
        if (encontrado){
            return guardarTodos(estudiantes);
        }
        return false;
    }
    
    //Eliminar
    public boolean eliminar(String carnet){
        if (carnet == null || carnet.trim().isEmpty()){
            System.err.println("Error: el carnet a eliminar no puede ser nulo o vacio.");
            return false;
        }
        
        List<Estudiante> estudiantes = obtenerTodos();
        boolean removido = estudiantes.removeIf(est -> est.getCarnet().equalsIgnoreCase(carnet.trim()));
       
        if (removido){
            return guardarTodos(estudiantes);
        }
        return false;
    }
    
    //Sobreescribe todo el archivo .txt con la nueva lista que recibe
    private boolean guardarTodos(List<Estudiante> lista){
        try (BufferedWriter bw = new BufferedWriter(
                new OutputStreamWriter(
                        new FileOutputStream(RUTA_ARCHIVO, false),
                        StandardCharsets.UTF_8))){
            for (Estudiante est : lista) {
                bw.write(est.toString());
                bw.newLine();
            }
            return true;
        } catch (IOException e) {
            System.err.println("Error al reescribir el archivo de estudiantes: " + e.getMessage());
            return false;
        }
    }
    
}
