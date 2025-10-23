package proye1.vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import proye1.*;

public class PanelExpediente extends JPanel {

    private VentanaPrincipal ventana;
    private JComboBox<String> comboPacientes;
    private JTextArea areaInfo;
    private JButton btnVerExpediente, btnVolver;
    private ArrayList<Paciente> pacientes;

    public PanelExpediente(VentanaPrincipal ventana, ArrayList<Paciente> pacientes) {
        this.ventana = ventana;
        this.pacientes = pacientes;

        setLayout(new BorderLayout());
        setBackground(new Color(240, 250, 255));

        // ---- Título ----
        JLabel lblTitulo = new JLabel("Consulta de Expedientes", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitulo.setForeground(new Color(20, 60, 90));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        add(lblTitulo, BorderLayout.NORTH);

        // ---- Panel superior: selección de paciente ----
        JPanel panelSeleccion = new JPanel(new FlowLayout());
        panelSeleccion.setBackground(new Color(240, 250, 255));

        comboPacientes = new JComboBox<>();
        comboPacientes.setPreferredSize(new Dimension(300, 30));
        llenarPacientes();

        btnVerExpediente = new JButton("📋 Ver Expediente");
        btnVerExpediente.setBackground(new Color(100, 180, 255));
        btnVerExpediente.setForeground(Color.WHITE);
        btnVerExpediente.setFocusPainted(false);

        panelSeleccion.add(new JLabel("Seleccione un paciente:"));
        panelSeleccion.add(comboPacientes);
        panelSeleccion.add(btnVerExpediente);
        add(panelSeleccion, BorderLayout.NORTH);

        // ---- Panel central: información del expediente ----
        areaInfo = new JTextArea();
        areaInfo.setEditable(false);
        areaInfo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        JScrollPane scroll = new JScrollPane(areaInfo);
        scroll.setBorder(BorderFactory.createTitledBorder("Información del expediente"));
        add(scroll, BorderLayout.CENTER);

        // ---- Panel inferior: botón volver ----
        btnVolver = new JButton("⬅ Volver al Menú");
        btnVolver.setBackground(new Color(100, 180, 255));
        btnVolver.setForeground(Color.WHITE);
        btnVolver.setFocusPainted(false);
        btnVolver.setPreferredSize(new Dimension(160, 40));

        JPanel panelInferior = new JPanel();
        panelInferior.setBackground(new Color(240, 250, 255));
        panelInferior.add(btnVolver);
        add(panelInferior, BorderLayout.SOUTH);

        // ---- Acciones ----
        btnVerExpediente.addActionListener(e -> mostrarExpediente());
        btnVolver.addActionListener(e -> ventana.mostrarPanel("Menu"));
    }

    // ==========================
    // 📋 MÉTODOS
    // ==========================

    private void llenarPacientes() {
        comboPacientes.removeAllItems();
        if (pacientes == null || pacientes.isEmpty()) {
            comboPacientes.addItem("(No hay pacientes registrados)");
        } else {
            for (Paciente p : pacientes) {
                comboPacientes.addItem(p.getNombre() + " - Expediente Nº " + p.getExpediente().getNumeroExpediente());
            }
        }
    }

    private void mostrarExpediente() {
        int index = comboPacientes.getSelectedIndex();
        if (pacientes == null || pacientes.isEmpty() || index < 0) {
            JOptionPane.showMessageDialog(this, "No hay pacientes seleccionados o registrados.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Paciente p = pacientes.get(index);
        Expediente exp = p.getExpediente();

        StringBuilder sb = new StringBuilder();
        sb.append(exp.toString());

        areaInfo.setText(sb.toString());
    }

    // Método para actualizar la lista cuando se agregan pacientes nuevos
    public void actualizarPacientes(ArrayList<Paciente> nuevos) {
        this.pacientes = nuevos;
        llenarPacientes();
    }
}
