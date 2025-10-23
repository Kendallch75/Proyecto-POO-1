package proye1.vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import proye1.*;

public class PanelCitas extends JPanel {

    private VentanaPrincipal ventana;
    private ArrayList<Paciente> pacientes;
    private ArrayList<Medico> medicos;
    private ArrayList<Consultorio> consultorios;
    private ArrayList<Cita> citas;

    private JComboBox<String> comboPacientes;
    private JComboBox<String> comboMedicos;
    private JComboBox<String> comboConsultorios;
    private JTextField txtFecha;
    private JTextField txtHora;
    private DefaultListModel<String> modeloLista;
    private JList<String> listaCitas;
    private JButton btnAgendar, btnVolver;

    public PanelCitas(VentanaPrincipal ventana, ArrayList<Paciente> pacientes, ArrayList<Medico> medicos, ArrayList<Consultorio> consultorios) {
        this.ventana = ventana;
        this.pacientes = pacientes;
        this.medicos = medicos;
        this.consultorios = consultorios;
        this.citas = new ArrayList<>();

        setLayout(new BorderLayout());
        setBackground(new Color(240, 250, 255));

        // ---- Título ----
        JLabel lblTitulo = new JLabel("Gestión de Citas", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitulo.setForeground(new Color(20, 60, 90));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        add(lblTitulo, BorderLayout.NORTH);

        // ---- Panel central ----
        JPanel panelCentral = new JPanel(new GridLayout(6, 2, 10, 10));
        panelCentral.setBorder(BorderFactory.createEmptyBorder(20, 100, 20, 100));
        panelCentral.setBackground(new Color(240, 250, 255));

        panelCentral.add(new JLabel("Paciente:"));
        comboPacientes = new JComboBox<>();
        llenarPacientes();
        panelCentral.add(comboPacientes);

        panelCentral.add(new JLabel("Médico:"));
        comboMedicos = new JComboBox<>();
        llenarMedicos();
        panelCentral.add(comboMedicos);

        panelCentral.add(new JLabel("Consultorio:"));
        comboConsultorios = new JComboBox<>();
        llenarConsultorios();
        panelCentral.add(comboConsultorios);

        panelCentral.add(new JLabel("Fecha (dd/mm/aaaa):"));
        txtFecha = new JTextField();
        panelCentral.add(txtFecha);

        panelCentral.add(new JLabel("Hora (hh:mm):"));
        txtHora = new JTextField();
        panelCentral.add(txtHora);

        btnAgendar = new JButton("📅 Agendar Cita");
        btnAgendar.setBackground(new Color(100, 180, 255));
        btnAgendar.setForeground(Color.WHITE);
        btnAgendar.setFocusPainted(false);
        panelCentral.add(btnAgendar);

        btnVolver = new JButton("⬅ Volver al Menú");
        btnVolver.setBackground(new Color(100, 180, 255));
        btnVolver.setForeground(Color.WHITE);
        btnVolver.setFocusPainted(false);
        panelCentral.add(btnVolver);

        add(panelCentral, BorderLayout.NORTH);

        // ---- Lista de citas registradas ----
        modeloLista = new DefaultListModel<>();
        listaCitas = new JList<>(modeloLista);
        listaCitas.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        JScrollPane scroll = new JScrollPane(listaCitas);
        scroll.setBorder(BorderFactory.createTitledBorder("Citas registradas"));
        add(scroll, BorderLayout.CENTER);

        // ---- Acciones ----
        btnAgendar.addActionListener(e -> agendarCita());
        btnVolver.addActionListener(e -> ventana.mostrarPanel("Menu"));
        
        // ⭐⭐ ACTUALIZAR LISTAS AL INICIAR
        actualizarListas();
    }

    // ========================
    // 🔧 MÉTODOS DE APOYO
    // ========================
    private void llenarPacientes() {
        comboPacientes.removeAllItems();
        if (pacientes == null || pacientes.isEmpty()) {
            comboPacientes.addItem("(No hay pacientes registrados)");
        } else {
            for (Paciente p : pacientes) {
                comboPacientes.addItem(p.getNombre());
            }
        }
    }

    private void llenarMedicos() {
        comboMedicos.removeAllItems();
        if (medicos == null || medicos.isEmpty()) {
            comboMedicos.addItem("(No hay médicos registrados)");
        } else {
            for (Medico m : medicos) {
                comboMedicos.addItem(m.getNombre() + " - " + m.getEspecialidad());
            }
        }
    }

    private void llenarConsultorios() {
        comboConsultorios.removeAllItems();
        if (consultorios == null || consultorios.isEmpty()) {
            comboConsultorios.addItem("(No hay consultorios disponibles)");
        } else {
            for (Consultorio c : consultorios) {
                comboConsultorios.addItem("Consultorio " + c.numero);
            }
        }
    }
    
    // ⭐⭐ NUEVO MÉTODO: Actualizar todas las listas
    public void actualizarListas() {
        llenarMedicos();
        llenarPacientes();
        llenarConsultorios();
    }

    // ========================
    // 📅 MÉTODO PRINCIPAL
    // ========================
    private void agendarCita() {
        if (pacientes.isEmpty() || medicos.isEmpty() || consultorios.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe haber pacientes, médicos y consultorios registrados.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int indexP = comboPacientes.getSelectedIndex();
        int indexM = comboMedicos.getSelectedIndex();
        int indexC = comboConsultorios.getSelectedIndex();
        String fecha = txtFecha.getText().trim();
        String hora = txtHora.getText().trim();

        if (fecha.isEmpty() || hora.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe ingresar fecha y hora.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Paciente paciente = pacientes.get(indexP);
        Medico medico = medicos.get(indexM);
        Consultorio consultorio = consultorios.get(indexC);

        Cita nueva = new Cita();
        nueva.setPaciente(paciente);
        nueva.setProfesional(medico);
        nueva.setConsultorio(consultorio);
        nueva.setFecha(fecha);
        nueva.setHora(hora);
        nueva.setEstado("Pendiente");

        citas.add(nueva);
        modeloLista.addElement("📅 " + paciente.getNombre() + " - " + medico.getNombre() +
                " | " + fecha + " " + hora + " | Consultorio " + consultorio.numero);

        txtFecha.setText("");
        txtHora.setText("");
    }

    public ArrayList<Cita> getCitas() {
        return citas;
    }
}