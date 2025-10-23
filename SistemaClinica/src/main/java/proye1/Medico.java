package proye1;

import java.time.LocalDate;
import java.util.List;
import java.time.format.DateTimeFormatter;

public class Medico extends Personal {

    private String especialidad;
    private int colegiado;

    public Medico() {
        super(); 
        this.especialidad = ""; 
        this.colegiado = 0;
    }
    
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

    
    public void actualizarExpediente(Expediente expediente, String diagnostico,
                                     List<InsumoMedico> insumosUsados, Inventario inventario) {
        if (expediente == null)
            throw new IllegalArgumentException("Expediente no puede ser nulo");

        // Fecha actual formateada
        String fecha = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        // Crear nueva receta
        Receta receta = new Receta(fecha, diagnostico);

        // Agregar insumos médicos a la receta
        if (insumosUsados != null && !insumosUsados.isEmpty()) {
            for (InsumoMedico insumo : insumosUsados) {
                receta.agregarInsumo(insumo);
            }

            // Registrar receta en el expediente y descontar insumos del inventario
            inventario.registrarInsumosEnExpediente(expediente, insumosUsados, fecha, diagnostico);
        }

        // Registrar diagnóstico y actualizar condición
        expediente.agregarDiagnostico(diagnostico);
        expediente.setCondicion(diagnostico);

        System.out.println("✅ Expediente actualizado por el médico: " + getNombre());
    }

    
    public void actEstadoCita(Cita cit) {
        cit.setEstado("atendida");
    }

    @Override
    public String toString() {
        return "Dr. " + getNombre() + " - " + especialidad + " (Colegiado: " + colegiado + ")";
    }
}