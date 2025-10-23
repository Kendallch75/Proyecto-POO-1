package proye1;

import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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

    public void setColegiado(int col) {
        this.colegiado = col; 
    }

    public int getColegiado() {
        return colegiado; 
    }

    public void actualizarExpediente(Expediente expediente, String diagnostico,
                                     List<InsumoMedico> insumosUsados, Inventario inventario) {
        if (expediente == null)
            throw new IllegalArgumentException("Expediente no puede ser nulo");

        // Fecha actual formateada
        String fecha = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        // Crear nueva receta
        Receta receta = new Receta(fecha, diagnostico);

        // Agregar insumos médicos
        if (insumosUsados != null && !insumosUsados.isEmpty()) {
            for (InsumoMedico insumo : insumosUsados) {
                receta.agregarInsumo(insumo);
            }

            // Registrar receta en el expediente y descontar del inventario
            expediente.agregarReceta(receta);
            inventario.registrarInsumosEnExpediente(expediente, insumosUsados, fecha, diagnostico);
        }

        // Registrar diagnóstico y condición (si aplica)
        if (diagnostico != null && !diagnostico.trim().isEmpty()) {
            expediente.agregarDiagnostico(diagnostico);
            expediente.setCondicion(diagnostico);
        }

        System.out.println("✅ Expediente actualizado por el enfermero: " + getNombre());
    }
    
    public void actEstadoCita(Cita cit) {
        cit.setEstado("atendida");
    }

    @Override
    public String toString() {
        return getNombre() + " (Enfermero, Colegiado: " + colegiado + ")";
    }

}