package proye1;


import java.io.*;
import java.util.*;

/**
 * 
 */
public class Factura {

    private String paciente;
    private ArrayList<InsumoMedico> insumos;
    private int precioTotal;
    public int servicioProfesional;
    
    public Factura(String paciente, int precioTotal, int servicioProfesional, ArrayList<InsumoMedico> insumos) {
        this.paciente = paciente; 
        this.precioTotal = precioTotal; 
        this.servicioProfesional = servicioProfesional; 
        this.insumos = insumos; 
    }

  
    /**
     * @param nombre
     */
    public void setPaciente(String nombre) {
        this.paciente = nombre; 
    }

    /**
     * 
     */
    public String getPaciente() {
        return paciente; 
    }

    /**
     * @param lista
     */
    public void setInsumos(ArrayList<InsumoMedico> insumos) {
        this.insumos = insumos;
    }

    /**
     * 
     */
    public ArrayList<InsumoMedico> getInsumos() {
        return insumos; 
    }

    /**
     * 
     */
    public void setPrecioTotal(int precioTotal) {
        this.precioTotal = precioTotal; 
    }

    /**
     * 
     */
    public int getPrecioTotal() {
        return precioTotal; 
    }
    
    /**
     * 
     */
    public void setservicioProfesional(int servprof) {
        this.servicioProfesional = servprof; 
    }

    /**
     * 
     */
    public int getservicioProfesional() {
        return servicioProfesional; 
    }
    
    public void imprimirFactura() {
        System.out.println("=====================================");
        System.out.println("         HOSPITAL JAVA        ");
        System.out.println("=====================================");
        System.out.println("-------------------------------------");
        System.out.println("Paciente: " + paciente);
        System.out.println("Cobro profesional: ₡" + servicioProfesional);
        System.out.println("-------------------------------------");
        System.out.println("Insumos y Medicamentos:");
        
        if (insumos.isEmpty()) {
            System.out.println("  - No se registraron insumos médicos.");
        } else {
            for (InsumoMedico insumo : insumos) {
                System.out.println("  • " + insumo.getNombre() + " - ₡" + insumo.getPrecio());
            }
        }

        System.out.println("-------------------------------------");
        System.out.println("Total a pagar: ₡" + precioTotal);
        System.out.println("=====================================");
        System.out.println("   ¡Gracias por su visita! ️        ");
        System.out.println("=====================================");
    }

}
