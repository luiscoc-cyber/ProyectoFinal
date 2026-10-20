//Autor: Luis
package com.sistema.controladores;

import com.sistema.datos.ProfesorDatos;
import com.sistema.modelos.Profesor;

public class ControladorAutenticacion {

    private final ProfesorDatos profesorDatos;
    private Profesor profesorLogueado; // Guarda el profesor que inició sesión

    public ControladorAutenticacion() {
        this.profesorDatos = new ProfesorDatos();
        this.profesorLogueado = null;
    }

    /**
     * Valida si las credenciales ingresadas en el Login son correctas.
     * @param idProfesor El ID o usuario del profesor.
     * @param password La contraseña ingresada.
     * @return Mensaje con el resultado de la autenticación.
     */
    public String autenticar(String idProfesor, String password) {
        // 1. Validar campos vacíos
        if (idProfesor == null || idProfesor.trim().isEmpty() ||
            password == null || password.trim().isEmpty()) {
            return "Error: Ingrese su usuario y contraseña.";
        }

        // 2. Buscar si el profesor existe por su ID
        Profesor prof = profesorDatos.buscarPorIdProfesor(idProfesor.trim());

        if (prof == null) {
            return "Error: El usuario no existe.";
        }

        // 3. Validar la contraseña 
        // (Si no agregaron campo de contraseña en el modelo, usamos el ID como contraseña por defecto, 
        // o llamamos a prof.getPassword() si ya se la agregaron).
        
        // OP CION A: Si el modelo Profesor YA tiene el atributo password:
        // if (prof.getPassword().equals(password.trim())) {
        
        // OPCION B: Si usan el ID como contraseña provisoria:
        if (prof.getPassword().equals(password.trim())) {
            this.profesorLogueado = prof; // Guardamos la sesión activa
            return "EXITO";
        } else {
            return "Error: Contraseña incorrecta.";
        }
    }

    // Retorna el profesor que acaba de iniciar sesión para saber sus cursos
    public Profesor getProfesorLogueado() {
        return profesorLogueado;
    }

    // Para cerrar sesión al salir
    public void cerrarSesion() {
        this.profesorLogueado = null;
    }
}