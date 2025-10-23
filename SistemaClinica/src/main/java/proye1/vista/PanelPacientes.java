package proye1.vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import proye1.Paciente;
import proye1.Expediente;

public class PanelPacientes extends JPanel {

    private VentanaPrincipal ventana;
    private DefaultListModel<String> modeloLista;
    private JList<String> listaPacientes;
    private ArrayList<Paciente> pacientes;
    private JTextField txtNombre;
    private JTextField txtEdad;

    public PanelPacientes(VentanaPrincipal ventana) {
        this.ventana = ventana;
        this.pacientes = new ArrayList<>();

        setLayout(new BorderLayout());
        setBackground(new Color(240, 250, 255));

        // ---- Título ----
        JLabel lblTitulo = new JLabel("Gestión de Pacientes", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitulo.setForeground(new Color(20, 60, 90));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        add(lblTitulo, BorderLayout.NORTH);

        // ---- Panel Central ----
        JPanel panelCentro = new JPanel(new BorderLayout());
        panelCentro.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        panelCentro.setBackground(new Color(240, 250, 255));

        modeloLista = new DefaultListModel<>();
        listaPacientes = new JList<>(modeloLista);
        listaPacientes.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        JScrollPane scroll = new JScrollPane(listaPacientes);
        panelCentro.add(scroll, BorderLayout.CENTER);

        // ---- Panel de entrada ----
        JPanel panelInferior = new JPanel(new GridLayout(3, 2, 10, 10));
        panelInferior.setBorder(BorderFactory.createEmptyBorder(20, 100, 20, 100));
        panelInferior.setBackground(new Color(240, 250, 255));

        panelInferior.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        panelInferior.add(txtNombre);

        panelInferior.add(new JLabel("Edad:"));
        txtEdad = new JTextField();
        panelInferior.add(txtEdad);

        JButton btnAgregar = new JButton("Agregar Paciente");
        JButton btnVolver = new JButton("⬅ Volver al Menú");
        panelInferior.add(btnAgregar);
        panelInferior.add(btnVolver);

        // ---- Añadir todo al panel principal ----
        add(panelCentro, BorderLayout.CENTER);
        add(panelInferior, BorderLayout.SOUTH);

        // ---- Acción de botones ----
        btnAgregar.addActionListener(e -> agregarPaciente());
        btnVolver.addActionListener(e -> ventana.mostrarPanel("Menu"));
    }

    // ========================
    // 👨‍⚕️ MÉTODOS LÓGICOS
    // ========================
    private void agregarPaciente() {
        String nombre = txtNombre.getText().trim();
        String edadTexto = txtEdad.getText().trim();

        if (nombre.isEmpty() || edadTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe ingresar nombre y edad.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int edad = Integer.parseInt(edadTexto);
            Paciente nuevo = new Paciente(nombre, edad);
            pacientes.add(nuevo);

            modeloLista.addElement("🩺 " + nuevo.getNombre() + " (Edad: " + edad + ") | Expediente Nº " + nuevo.getExpediente().getNumeroExpediente());
            txtNombre.setText("");
            txtEdad.setText("");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "La edad debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public ArrayList<Paciente> getPacientes() {
        return pacientes;
    }
}