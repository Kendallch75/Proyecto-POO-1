package proye1;

import java.util.Arrays;

public class Consultorio {

    public int numero;
    protected boolean[] reservaciones = new boolean[6]; // true = ocupado, false = desocupado

    public Consultorio(int num) {
        this.numero = num;
    }
    
    public void getNombre() {
        System.out.println("Consultorio Numero: " + numero);
    }
    
    public void setNombre(int num) {
        this.numero = num;
    }

    public String getReservaciones() {
        return Arrays.toString(reservaciones);
    }

    public void asignarConsultorio() {
        for (int i = 0; i < reservaciones.length; i++) {
            if (!reservaciones[i]) { // Si está desocupado (false)
                reservaciones[i] = true; // Asignar cita (ocupar)
                System.out.println("Consultorio reservado: " + numero);
                return;
            }
        }
        System.out.println("No hay consultorios disponibles");
    }
    
    public void finUso() {
        reservaciones[numero] = false;
    }
    
    @Override
    public String toString() {
        int disponibles = 0;
        for (boolean r : reservaciones) {
            if (!r) disponibles++;
        }
        return "Consultorio " + numero + " (" + disponibles + "/6 disponibles)";
    }
}