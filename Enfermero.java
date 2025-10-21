package Modelo;


import java.io.*;
import java.util.*;

/**
 * 
 */
public class Enfermero extends Personal {

    private int colegiado;
    
    public Enfermero() {
        super(); 
        this.colegiado = 0; 
    }
     public Enfermero(String nombre, String cargo, String jornada, String cedula, 
            String usuario, String password, int colegiado) {
        super(nombre, cargo, jornada, cedula, usuario, password); 
        this.colegiado = colegiado;
    }


    /**
     * @param col
     */
    public void setColegiado(int col) {
        this.colegiado = col; 
    }

    /**
     * 
     */
    public int getColegiado() {
        return colegiado; 
    }

    /**
     * 
     
    public void actualizarExpediente(Expediente exp, String nuevoDiagnostico, ArrayList<String> recetaMedica) {
    exp.agregarDiagnostico(nuevoDiagnostico);
    exp.getHistorialDeRecetas().add(recetaMedica);
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
     */
    public void actEstadoCita(Cita cit) {
        cit.setEstado("atendida");
    }

}
