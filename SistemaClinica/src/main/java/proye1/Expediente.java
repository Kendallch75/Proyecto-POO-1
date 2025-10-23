package proye1;

import java.util.ArrayList;

public class Expediente {
    private String nombrep;
    private String condicionActual;
    private ArrayList<Receta> historialRecetas;
    private int numeroExpediente;
    private ArrayList<String> diagnosticos;      

    public Expediente(String nombre) {
        this.nombrep = nombre;
        this.numeroExpediente = (int) (Math.random() * 9000) + 1000;
        this.diagnosticos = new ArrayList<>();
        this.historialRecetas = new ArrayList<>();
    }

    public void setCondicion(String condicionActual) {
        this.condicionActual = condicionActual;
    }

    public String getCondicion() {
        return condicionActual;
    }

    public int getNumeroExpediente() {
        return numeroExpediente;
    }

    public String getNombrePaciente(){
        return nombrep;
    }
    
    public void agregarReceta(Receta receta) {
        if (receta != null) {
            historialRecetas.add(receta);
        }
    }


    public ArrayList<Receta> getHistorialRecetas() {
        return historialRecetas;
    }

    public void agregarDiagnostico(String diagnostico) {
        if (diagnostico != null && !diagnostico.trim().isEmpty()) {
            diagnosticos.add(diagnostico);
        }
    }

    public ArrayList<String> getDiagnosticos() {
        return diagnosticos;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== EXPEDIENTE MÉDICO ===\n");
        sb.append("Paciente: ").append(nombrep).append("\n");
        sb.append("Número de expediente: ").append(numeroExpediente).append("\n");
        sb.append("Condición actual: ").append(condicionActual != null ? condicionActual : "No especificada").append("\n\n");

        sb.append("--- DIAGNÓSTICOS ---\n");
        if (diagnosticos.isEmpty()) {
            sb.append("No hay diagnósticos registrados.\n");
        } else {
            for (String diag : diagnosticos) {
                sb.append("• ").append(diag).append("\n");
            }
        }

        sb.append("\n--- HISTORIAL DE RECETAS ---\n");
        if (historialRecetas.isEmpty()) {
            sb.append("No hay recetas registradas.\n");
        } else {
            for (Receta receta : historialRecetas) {
                sb.append(receta).append("\n");
            }
        }

        return sb.toString();
    }
}

