package proye1.vista;

import javax.swing.*;
import java.awt.*;
import proye1.*;
import java.util.ArrayList;
import java.util.List;

public class VentanaPrincipal extends JFrame {
    
    private CardLayout layout;
    private JPanel panelContenedor;
    
    // Paneles principales
    private PanelLogin panelLogin;
    private PanelMenuPrincipal panelMenu;
    private PanelPacientes panelPacientes;
    private PanelExpediente panelExpediente;
    private PanelInventario panelInventario;
    private PanelCitas panelCitas;
    private PanelRegistroPersonal panelRegistroPersonal;
    
    // Datos compartidos entre módulos
    private ArrayList<Paciente> listaPacientes;
    private ArrayList<Medico> listaMedicos;
    private ArrayList<Consultorio> listaConsultorios;
    private Administrativo adminPrincipal;
    
    public VentanaPrincipal() {
        setTitle("Sistema Clínico - Gestión Hospitalaria");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 650);
        setLocationRelativeTo(null);
        setResizable(false);
        
        layout = new CardLayout();
        panelContenedor = new JPanel(layout);
        getContentPane().add(panelContenedor);
        
        // ======================================
        // 🔹 Inicialización de datos base
        // ======================================
        listaPacientes = new ArrayList<>();
        listaMedicos = new ArrayList<>();
        listaConsultorios = new ArrayList<>();
        
        // ⭐ Crear administrativo principal
        adminPrincipal = new Administrativo("Laura Soto", "Administrativo", "Diurna", 
                                           "5555", "lsoto", "admin123");
        
        // Médicos de prueba
        Medico medico1 = new Medico("Dr. Mora", "Médico", "Diurna", "1234", "drmora", 
                                    "pass123", "Cardiología", 7890);
        Medico medico2 = new Medico("Dra. Vega", "Médico", "Matutina", "5678", "dvega", 
                                    "doc123", "Pediatría", 7845);
        
        listaMedicos.add(medico1);
        listaMedicos.add(medico2);
        
        // ⭐ Registrar médicos en el administrativo
        adminPrincipal.registrarPersonal(medico1);
        adminPrincipal.registrarPersonal(medico2);
        
        // Consultorios disponibles
        Consultorio c1 = new Consultorio(1);
        Consultorio c2 = new Consultorio(2);
        Consultorio c3 = new Consultorio(3);
        
        listaConsultorios.add(c1);
        listaConsultorios.add(c2);
        listaConsultorios.add(c3);
        
        // ⭐ Registrar consultorios en el administrativo
        adminPrincipal.registrarConsultorio(c1);
        adminPrincipal.registrarConsultorio(c2);
        adminPrincipal.registrarConsultorio(c3);
        
        // ======================================
        // 🔹 Inicialización de paneles
        // ======================================
        panelLogin = new PanelLogin(this);
        panelMenu = new PanelMenuPrincipal(this);
        panelPacientes = new PanelPacientes(this);
        panelExpediente = new PanelExpediente(this, panelPacientes.getPacientes());
        panelInventario = new PanelInventario(this);
        panelCitas = new PanelCitas(this, panelPacientes.getPacientes(), listaMedicos, listaConsultorios);
        panelRegistroPersonal = new PanelRegistroPersonal(this, adminPrincipal);
        
        // ======================================
        // 🔹 Registro en el contenedor
        // ======================================
        panelContenedor.add(panelLogin, "Login");
        panelContenedor.add(panelMenu, "Menu");
        panelContenedor.add(panelPacientes, "Pacientes");
        panelContenedor.add(panelExpediente, "Expediente");
        panelContenedor.add(panelInventario, "Inventario");
        panelContenedor.add(panelCitas, "Citas");
        panelContenedor.add(panelRegistroPersonal, "RegistroPersonal");
        
        // Mostrar pantalla inicial
        mostrarPanel("Login");
        setVisible(true);
    }
    
    // ======================================
    // 🔁 Cambiar entre paneles
    // ======================================
    public void mostrarPanel(String nombre) {
        // Actualizar datos entre paneles antes de cambiar
        if (nombre.equals("Expediente")) {
            panelExpediente.actualizarPacientes(panelPacientes.getPacientes());
        } else if (nombre.equals("Citas")) {
            panelCitas = new PanelCitas(this, panelPacientes.getPacientes(), listaMedicos, listaConsultorios);
            panelContenedor.add(panelCitas, "Citas");
        }
        
        layout.show(panelContenedor, nombre);
    }
    
    // ⭐⭐ NUEVO MÉTODO: Agregar médico a la lista
    public void agregarMedico(Medico medico) {
        if (medico != null && !listaMedicos.contains(medico)) {
            listaMedicos.add(medico);
            // También actualizar el administrativo (ya se hace en el panel)
            adminPrincipal.registrarPersonal(medico);
        }
    }
    
    // ⭐ Método para obtener el administrativo (si otros paneles lo necesitan)
    public Administrativo getAdministrativo() {
        return adminPrincipal;
    }
    
    // ======================================
    // 🚀 Método principal
    // ======================================
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaPrincipal());
    }
}