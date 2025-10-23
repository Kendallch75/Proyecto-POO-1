package proye1;

import java.util.ArrayList;
import java.util.List;

public class Administrativo extends Personal {

    // Listas de gestión general
    private List<Paciente> pacientes;
    private List<Cita> citas;
    private List<Personal> personal;
    private List<Consultorio> consultorios;

    // Constructor
    public Administrativo(String nombre, String cargo, String jornada, String cedula,
                          String usuario, String password) {
        super(nombre, cargo, jornada, cedula, usuario, password);
        this.pacientes = new ArrayList<>();
        this.citas = new ArrayList<>();
        this.personal = new ArrayList<>();
        this.consultorios = new ArrayList<>();
    }

    // =========================
    // 📋 GESTIÓN DE PACIENTES
    // =========================
    public void registrarPaciente(Paciente p) {
        if (p != null && !pacientes.contains(p)) {
            pacientes.add(p);
            System.out.println("✅ Paciente registrado: " + p.getNombre());
        }
    }

    public List<Paciente> getPacientes() {
        return pacientes;
    }

    public Paciente buscarPacientePorNombre(String nombre) {
        for (Paciente p : pacientes) {
            if (p.getNombre().equalsIgnoreCase(nombre)) {
                return p;
            }
        }
        return null;
    }

    // =========================
    // 👥 GESTIÓN DE PERSONAL
    // =========================
    public void registrarPersonal(Personal p) {
        if (p != null && !personal.contains(p)) {
            personal.add(p);
            System.out.println("✅ Personal agregado: " + p.getNombre());
        }
    }

    public List<Personal> getPersonal() {
        return personal;
    }

    public Personal buscarPersonalPorUsuario(String usuario) {
        for (Personal p : personal) {
            if (p.getUsuario().equalsIgnoreCase(usuario)) {
                return p;
            }
        }
        return null;
    }

    // =========================
    // 🏥 GESTIÓN DE CONSULTORIOS
    // =========================
    public void registrarConsultorio(Consultorio c) {
        if (c != null && !consultorios.contains(c)) {
            consultorios.add(c);
            System.out.println("🏥 Consultorio agregado: " + c);
        }
    }

    public List<Consultorio> getConsultorios() {
        return consultorios;
    }

    public Consultorio buscarConsultorioPorNumero(int num) {
        for (Consultorio c : consultorios) {
            if (c.numero == num) {
                return c;
            }
        }
        return null;
    }

    // =========================
    // 📅 GESTIÓN DE CITAS
    // =========================
    public void agendarCita(Paciente paciente, Personal profesional, 
                            Consultorio consultorio, String fecha, String hora) {
        if (paciente == null || profesional == null || consultorio == null) {
            System.out.println("❌ Error: faltan datos para agendar la cita.");
            return;
        }

        Cita nueva = new Cita();
        nueva.setPaciente(paciente);
        nueva.setProfesional(profesional);
        nueva.setConsultorio(consultorio);
        nueva.setFecha(fecha);
        nueva.setHora(hora);
        nueva.setEstado("Pendiente");

        citas.add(nueva);
        consultorio.asignarConsultorio();

        System.out.println("📅 Cita agendada correctamente:");
        System.out.println("   Paciente: " + paciente.getNombre());
        System.out.println("   Profesional: " + profesional.getNombre());
        System.out.println("   Consultorio: " + consultorio.numero);
        System.out.println("   Fecha: " + fecha + " - Hora: " + hora);
    }

    public List<Cita> getCitas() {
        return citas;
    }

    // =========================
    // 🔎 UTILITARIOS
    // =========================
    public void mostrarPacientes() {
        System.out.println("=== Lista de Pacientes ===");
        for (Paciente p : pacientes) {
            System.out.println("- " + p);
        }
    }

    public void mostrarPersonal() {
        System.out.println("=== Lista de Personal ===");
        for (Personal p : personal) {
            System.out.println("- " + p);
        }
    }

    public void mostrarCitas() {
        System.out.println("=== Lista de Citas ===");
        for (Cita c : citas) {
            System.out.println("- " + c);
        }
    }

    public void mostrarConsultorios() {
        System.out.println("=== Consultorios ===");
        for (Consultorio c : consultorios) {
            System.out.println("- " + c);
        }
    }
    // Agregar este método en la clase Administrativo
// ⭐ AGREGAR ESTE MÉTODO EN LA SECCIÓN "💥 GESTIÓN DE PERSONAL"
public Personal crearPersonal(String tipo, String nombre, String cargo, String jornada, 
                              String cedula, String usuario, String password, 
                              String especialidad, int colegiado) {
    Personal nuevoPersonal = null;
    
    switch (tipo.toLowerCase()) {
        case "medico":
        case "médico":
            nuevoPersonal = new Medico(nombre, cargo, jornada, cedula, usuario, 
                                       password, especialidad, colegiado);
            System.out.println("👨‍⚕️ Médico creado: " + nombre + " - " + especialidad);
            break;
            
        case "enfermero":
            nuevoPersonal = new Enfermero(nombre, cargo, jornada, cedula, usuario, 
                                          password, colegiado);
            System.out.println("👩‍⚕️ Enfermero creado: " + nombre);
            break;
            
        case "administrativo":
            nuevoPersonal = new Administrativo(nombre, cargo, jornada, cedula, 
                                               usuario, password);
            System.out.println("👔 Administrativo creado: " + nombre);
            break;
            
        default:
            System.out.println("❌ Tipo de personal no válido: " + tipo);
            return null;
    }
    
    // Registrar el personal en la lista
    if (nuevoPersonal != null) {
        registrarPersonal(nuevoPersonal);
    }
    
    return nuevoPersonal;
}

    @Override
    public String toString() {
        return "Administrativo: " + getNombre() + " (" + getCargo() + ")";
    }
}