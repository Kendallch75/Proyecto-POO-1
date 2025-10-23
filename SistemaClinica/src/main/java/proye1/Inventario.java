package proye1;

import java.util.ArrayList;
import java.util.List;

public class Inventario {
    private final List<InsumoMedico> insumos;

    public Inventario() {
        insumos = new ArrayList<>();
        inicializarInsumosDisponibles();
    }

    private void inicializarInsumosDisponibles() {
        insumos.add(new InsumoMedico("Suero fisiológico", "500 ml", 800, 0, false));
        insumos.add(new InsumoMedico("Suero glucosado", "500 ml", 900, 0, false));
        insumos.add(new InsumoMedico("Jeringa desechable", "10 ml", 90, 0, false));
        insumos.add(new InsumoMedico("Aguja hipodérmica", "21G", 60, 0, false));
        insumos.add(new InsumoMedico("Gasas estériles", "5x5 cm", 30, 0, false));
        insumos.add(new InsumoMedico("Algodón", "100 g", 250, 0, false));
        insumos.add(new InsumoMedico("Cinta adhesiva médica", "1 rollo", 400, 0, false));
        insumos.add(new InsumoMedico("Guantes desechables", "par", 200, 0, false));
        insumos.add(new InsumoMedico("Mascarilla quirúrgica", "unidad", 150, 0, false));
        insumos.add(new InsumoMedico("Cubrebocas N95", "unidad", 800, 0, false));
        insumos.add(new InsumoMedico("Termómetro digital", "unidad", 8000, 0, false));
        insumos.add(new InsumoMedico("Tensiómetro", "unidad", 25000, 0, false));
        insumos.add(new InsumoMedico("Oxímetro de pulso", "unidad", 15000, 0, false));
        insumos.add(new InsumoMedico("Alcohol 70%", "500 ml", 700, 0, false));
        insumos.add(new InsumoMedico("Clorhexidina", "500 ml", 2500, 0, false));
        insumos.add(new InsumoMedico("Compresas frías/calientes", "unidad", 600, 0, false));
        insumos.add(new InsumoMedico("Catéter venoso periférico", "14G", 450, 0, false));
        insumos.add(new InsumoMedico("Vendas elásticas", "5 cm x 4 m", 1000, 0, false));
        insumos.add(new InsumoMedico("Amoxicilina", "500mg", 1500, 0, true));
        insumos.add(new InsumoMedico("Cefalexina", "500mg", 1800, 0, true));
        insumos.add(new InsumoMedico("Ciprofloxacino", "500mg", 2500, 0, true));
    }

    public List<InsumoMedico> getInsumos() {
        return insumos;
    }

    public int verificarCantidadDisponibilidad(String insumo) {
        for (InsumoMedico i : insumos) {
            if (i.getNombre().equalsIgnoreCase(insumo)) {
                return i.getCantidad();
            }
        }
        return -1; 
    }

    public void mostrarInventario() {
        System.out.println("===== Inventario de Insumos Médicos =====");
        for (InsumoMedico i : insumos) {
            System.out.println("Nombre: " + i.getNombre() +
                               ", Dosis: " + i.getDosis() +
                               ", Precio: ₡" + i.getPrecio() +
                               ", Cantidad: " + i.getCantidad() +
                               ", Solo Médicos: " + (i.isSoloMedico() ? "Sí" : "No"));
        }
    }

    public void descontarInsumo(String insumo, int cantidad) {
        for (InsumoMedico i : insumos) {
            if (i.getNombre().equalsIgnoreCase(insumo)) {
                if (cantidad > i.getCantidad()) {
                    throw new IllegalArgumentException("Cantidad solicitada (" + cantidad +
                        ") excede la disponible (" + i.getCantidad() + ") para el insumo: " + i.getNombre());
                }
                i.setCantidad(i.getCantidad() - cantidad);
                return;
            }
        }
        throw new IllegalArgumentException("Insumo no encontrado: " + insumo);
    }
    
    public InsumoMedico obtenerInsumoPorNombre(String nombreInsumo) {
    for (InsumoMedico i : insumos) {
        if (i.getNombre().equalsIgnoreCase(nombreInsumo)) {
            return i;
        }
    }
    return null;
}


    public void agregarInsumo(String insumo, int cantidad) {
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad a agregar debe ser mayor que cero.");
        }

        for (InsumoMedico i : insumos) {
            if (i.getNombre().equalsIgnoreCase(insumo)) {
                i.setCantidad(i.getCantidad() + cantidad);
                return;
            }
        }

        throw new IllegalArgumentException("Insumo no encontrado: " + insumo);
    }
    
    public void registrarInsumosEnExpediente(Expediente expediente, List<InsumoMedico> insumosUsados,
                                             String fecha, String diagnostico) {
        // Crear una nueva receta con la fecha y diagnóstico
        Receta receta = new Receta(fecha, diagnostico);

        for (InsumoMedico insumo : insumosUsados) {
            boolean encontrado = false;

            for (InsumoMedico i : insumos) {
                if (i.getNombre().equalsIgnoreCase(insumo.getNombre())) {
                    if (i.getCantidad() < insumo.getCantidad()) {
                        throw new IllegalArgumentException("No hay suficiente cantidad de " + i.getNombre());
                    }
                    // Descontar cantidad usada
                    i.setCantidad(i.getCantidad() - insumo.getCantidad());
                    encontrado = true;
                    break;
                }
            }

            if (!encontrado) {
                throw new IllegalArgumentException("Insumo no encontrado en inventario: " + insumo.getNombre());
            }

            // Agregar insumo a la receta
            receta.agregarInsumo(insumo);
        }

        // Guardar receta completa en el expediente
        expediente.agregarReceta(receta);

        System.out.println("💾 Receta registrada en el expediente del paciente " +
                           expediente.getNombrePaciente() + " (" + fecha + ")");
    }



}