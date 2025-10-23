package proye1;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Receta {
    private String fecha;
    private String diagnostico;
    private List<InsumoMedico> insumosRecetados;

    public Receta(String fecha, String diagnostico) {
        this.fecha = LocalDate.now().toString();
        this.diagnostico = diagnostico;
        this.insumosRecetados = new ArrayList<>();
    }

    public void agregarInsumo(InsumoMedico insumo) {
        if (insumo != null) {
            insumosRecetados.add(insumo);
        }
    }

    public List<InsumoMedico> getInsumosRecetados() {
        return insumosRecetados;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("📄 Receta del ").append(fecha)
          .append(" | Diagnóstico: ").append(diagnostico)
          .append("\nInsumos recetados:\n");

        if (insumosRecetados.isEmpty()) {
            sb.append("   (Sin insumos registrados)\n");
        } else {
            for (InsumoMedico insumo : insumosRecetados) {
                sb.append("   - ").append(insumo.getNombre())
                  .append(" | Dosis: ").append(insumo.getDosis())
                  .append(" | Cantidad: ").append(insumo.getCantidad())
                  .append("\n");
            }
        }

        return sb.toString();
    }
}
