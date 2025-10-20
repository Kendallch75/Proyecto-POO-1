package proye1;
import java.util.ArrayList;

public class Expediente {
    private String nombrep;
    private String condicionActual;
    private ArrayList<ArrayList<String>> historialrc;    
    private int numeroExpediente;
    private ArrayList<String> diagnosticos;      

    public Expediente(String nombre) {
        this.nombrep = nombre;
        this.numeroExpediente = (int) (Math.random() * 9000) + 1000;
        this.diagnosticos = new ArrayList<>();
        this.historialrc = new ArrayList<>();
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

    //public void agregarReceta(ArrayList<String> receta) {
     //   for (var c: receta){
       //     historialrc.add(c);
        //}
    //}

    public ArrayList<ArrayList<String>> getHistorialDeRecetas() {
        return historialrc;
    }

    public void agregarDiagnostico(String diagnostico) {
         diagnosticos.add(diagnostico);
    }

    public ArrayList<String> getDiagnosticos() {
        return diagnosticos;
    }
}
