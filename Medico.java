package Modelo;

import java.io.*;
import java.util.*;

/**
 * 
 */
public class Medico extends Personal {

    private String especialidad;
    private int colegiado;

    
    //Constructor vacio
    public Medico() {
        super(); 
        this.especialidad = ""; 
        this.colegiado = 0;
    }
    //Constructor parametrico
    public Medico(String nombre, String cargo, String jornada, String cedula, 
            String usuario, String password, String especialidad, int colegiado) {
        super(nombre, cargo, jornada, cedula, usuario, password); 
        this.especialidad = especialidad; 
        this.colegiado = colegiado;
    }

    //Setters y Getters
    public void setEspecialidad(String esp) {
        this.especialidad = esp; 
    }
    public String getEspecialidad() {
        return especialidad; 
    }
    public void setColegiado(int col) {
        this.colegiado = col; 
    }
    public int getColegiado() {
        return colegiado; 
    }

    /**
     * Esta sería la version adaptada a la clase "Expediente" que hizo Nahun, solo que 
     * con esa version de "Expediente" queda un poco complicado hacer algunos metodos de la clase "Administrativo"
     * por eso lo puse así
    
    public void actualizarExpediente(Expediente exp, String nuevoDiagnostico, ArrayList<String> recetaMedica) {
    // 1. Agregar el nuevo diagnóstico textual
    exp.agregarDiagnostico(nuevoDiagnostico);

    // 2. Guardar la receta completa (lista de medicamentos usados o prescritos)
    exp.getHistorialDeRecetas().add(recetaMedica);

    // 3. Actualizar la condición actual del paciente
    exp.setCondicion(nuevoDiagnostico);
    }
    */
    public void actualizarExpediente(Expediente exp, String nuevoDiagnostico, ArrayList<InsumoMedico> medRecetada) { 
        for(int i = 0; i < medRecetada.size(); i += 1){ 
            exp.agregarhistorialDeRecetas(medRecetada.get(i)); 
        } 
        exp.setDiagnostico(nuevoDiagnostico); 
    }
    /**
     * 
     * @param cit
     */
    public void actEstadoCita(Cita cit) {
        cit.setEstado("atendida");
    }


}
