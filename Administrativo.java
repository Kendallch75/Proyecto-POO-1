package Modelo;

import java.io.*;
import java.util.*;

/**
 * 
 */
public class Administrativo extends Personal {

    /**
     * Default constructor
     */
    public Administrativo() {
        super(); 
    }
    public Administrativo(String nombre, String cargo, String jornada, String cedula, 
            String usuario, String password) {
        super(nombre, cargo, jornada, cedula, usuario, password); 
    }

    /**
     * 
     */
    public void crearUsuario(String nombre, String cargo, String jornada, String cedula, 
            String usuario, String password) {
        Personal profesional = new Personal(nombre, cargo, jornada, cedula, usuario, password); 
    }

    /**
     * 
     */
    public void asignarCita(Expediente expe, ArrayList<Personal> personal, ArrayList<Consultorio> consultorios,
                        String hora, String fecha, int medicoOenfermero) {

        String nomPaciente = expe.getNombrePaciente(); 
        int edadPaciente = expe.getEdadPaciente(); 
        int nExp = expe.getNexpediente(); 
        Paciente enfermo = new Paciente(nomPaciente, edadPaciente, nExp);

        for (Personal p : personal) {
            boolean tipoPersonal = 
                (medicoOenfermero == 1 && p instanceof Medico) ||  
                (medicoOenfermero == 0 && p instanceof Enfermero);

            if (tipoPersonal) {
                String jornada = p.getJornada();

                boolean horarioValido = false;
                if (jornada.equals("8am-8pm") && hora.compareTo("08:00") >= 0 && hora.compareTo("20:00") < 0)
                    horarioValido = true;
                else if (jornada.equals("8pm-8am") && (hora.compareTo("20:00") >= 0 || hora.compareTo("08:00") < 0))
                    horarioValido = true;

                if (!horarioValido) {
                    System.out.println(p.getNombre() + " no trabaja a esa hora (" + hora + ")");
                    continue;
                }

                boolean ocupado = false;
                for (Cita c : p.getlistaCitas()) {
                    if (c.getHora().equals(hora) && c.getFecha().equals(fecha)) {
                        ocupado = true;
                        break;
                    }
                }

                if (ocupado) {
                    System.out.println(p.getNombre() + " ya tiene una cita en ese horario (" + hora + ")");
                    continue;
                }

                for (Consultorio consu : consultorios) {
                    if (!consu.getOcupado()) {
                        Cita nuevaCita = new Cita(enfermo, p, "pendiente", consu, fecha, hora);
                        consu.setOcupado(true);
                        p.agregarlistaCitas(nuevaCita);
                        System.out.println("Cita asignada con " + p.getNombre() + 
                                           " en consultorio " + consu.getNombre() + 
                                           " a las " + hora);
                        return;
                    }
                }

                System.out.println("No hay consultorios disponibles.");
                return;
            }
        }
        System.out.println("No se encontró profesional disponible del tipo solicitado.");
    }


    /**
     * 
     * @param nombre
     * @param edad
     * @param condAct
     * @param nExp
     * @param profesional
     * @param med
     */
    public void registarPaciente(String nombre, int edad, String condAct, Personal profesional,
            InsumoMedico med, int nExp) {
        
            Expediente exp = new Expediente(nombre, edad, condAct, profesional, med, nExp); 
    }

    /**
     * 
     */
    public int realizarCobro(Cita citaPaciente, Expediente exp) {
        int cobro = 0; 
        if (citaPaciente.getProfesional() instanceof Medico) {
            cobro += 70000;
        } 
        else if (citaPaciente.getProfesional() instanceof Enfermero) {
            cobro += 30000; 
        }
        
        ArrayList<InsumoMedico> listInsum = exp.gethistorialDeRecetas();  
        
        for (int i = 0; i < listInsum.size(); i+=1){
            InsumoMedico insumo = listInsum.get(i); 
            cobro += insumo.getPrecio(); 
        }
      
        return cobro; 
    }

    /**
     * 
     */
    public void estadoDeCita(String nuevoEstado, Cita cita) {
        cita.setEstado(nuevoEstado);
    }
    
   
    public void generarFactura(Expediente exp, Cita citaPaciente) {
        int cobroTotal = realizarCobro(citaPaciente, exp); 
        Personal profesional = citaPaciente.getProfesional(); 
        int cobroProfesional = 0;
        if (citaPaciente.getProfesional() instanceof Medico) {
            cobroProfesional = 70000;
        } 
        else if (citaPaciente.getProfesional() instanceof Enfermero) {
            cobroProfesional = 30000; 
        }
     
        Factura factura = new Factura(exp.getNombrePaciente(), cobroTotal, cobroProfesional, exp.gethistorialDeRecetas()); 
        citaPaciente.setEstado("pagada");

    }
    
}
